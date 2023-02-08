package java8.completablefuture;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureInSequenceApp {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(logCurrentThread() + " -- starting app...");

        /** we can pass our own thread pool into the completable future
         * if we don't, the ForkJoin common pool is used, this has pros & cons
         * it depends on weather the tasks are computational or IO blocking
         * the number of cores should also be taken into consideration
         */
        ExecutorService executor = Executors.newFixedThreadPool(4);

        System.out.println(logCurrentThread() + " -- starting futures: " + LocalDateTime.now());

        /**
         *
         * in this example we have 3 long-running, interdependent processes which have to happen sequentially
         * (i) DB call to get data (10 seconds)
         * (ii) REST call to get data using the DB data needed from the DB call
         * (iii) update DB with results of REST call
         * we can wrap them in a CompletableFuture (and use our own optional executor)
         * and chain them together into a nice readable format
         * this will help keep the main thread free and responsive
         */
        CompletableFuture.supplyAsync(CompletableFutureInSequenceApp::getDataFromDbTakes10Seconds, executor)
                .thenApply(CompletableFutureInSequenceApp::getDataFromRestCallTakes5Seconds)
                .thenAccept(CompletableFutureInSequenceApp::updateDbWithRestDataTakes7Seconds);

        // sleep to let the completable future finish, simulating a live app that's 'up'
        Thread.sleep(30000);
        System.out.println(logCurrentThread() + " -- MAIN finished");
        executor.shutdown();
    }

    // simulate some long-running processes below
    private static String getDataFromDbTakes10Seconds() {
        System.out.println(logCurrentThread() + " -- starting getDataFromDbTakes10Seconds...will take 10 seconds");
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(logCurrentThread() + " -- finished getDataFromDbTakes10Seconds");
        return "dbData";
    }

    private static int getDataFromRestCallTakes5Seconds(String dbString) {
        System.out.println(logCurrentThread() + " -- starting getDataFromRestCallTakes5Seconds after getting DB data " + dbString + "...will take 5 seconds");
        try {
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(logCurrentThread() + " -- finished getDataFromRestCallTakes5Seconds");
        return 10;
    }

    private static void updateDbWithRestDataTakes7Seconds(int numberToInsertToDb) {
        System.out.println(logCurrentThread() + " -- starting updateDbWithRestDataTakes7Seconds after getting REST data " + numberToInsertToDb + "...will take 7 seconds");
        try {
            Thread.sleep(7_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(logCurrentThread() + " -- finished updateDbWithRestDataTakes7Seconds");
    }

    private static String logCurrentThread() {
        return Thread.currentThread().getName();
    }

}
