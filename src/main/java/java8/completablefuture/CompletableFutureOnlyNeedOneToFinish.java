package java8.completablefuture;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

public class CompletableFutureOnlyNeedOneToFinish {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(logCurrentThread() + " -- starting app...");

        // Here we have 3 completable futures all taking different lengths of time to completed
        // if we have a scenario where we only care when any of them complete successfully
        // we can group them together and them initiate some processing when the 1st has completed
        // the rest of the threads will still complete in the background

        System.out.println(logCurrentThread() + " -- starting futures: " + LocalDateTime.now());

        CompletableFuture.anyOf(
                longRunningProcess1(),
                longRunningProcess2(),
                longRunningProcess3())
                .thenAccept(obj -> System.out.println("successful completable future completed " + obj.toString()));

        Thread.sleep(30000);
        System.out.println(logCurrentThread() + " -- MAIN finished");
    }

    public static CompletableFuture<String> longRunningProcess1() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println(logCurrentThread() + " -- starting computation_1_compFuture...will take 10 seconds");
            try {
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(logCurrentThread() + " -- finished computation_1_compFuture");
            return "dbString";
        });
    }

    public static CompletableFuture<String> longRunningProcess2() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println(logCurrentThread() + " -- starting computation_2_compFuture...will take 5 seconds");
            try {
                Thread.sleep(5_000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(logCurrentThread() + " -- finished computation_2_compFuture");
            return "10";
        });
    }

    public static CompletableFuture<Void> longRunningProcess3() {
        return CompletableFuture.runAsync(() -> {
            System.out.println(logCurrentThread() + " -- starting computation_3_compFuture...will take 7 seconds");
            try {
                Thread.sleep(7_000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(logCurrentThread() + " -- finished computation_3_compFuture");
        });
    }

    private static String logCurrentThread() {
        return Thread.currentThread().getName();
    }

}
