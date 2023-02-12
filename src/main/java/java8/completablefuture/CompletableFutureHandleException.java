package java8.completablefuture;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

public class CompletableFutureHandleException {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(logCurrentThread() + " -- starting app...");

        // here we call a completable future that throws an exception
        // we can handle both success & failure scenarios this way

        System.out.println(logCurrentThread() + " -- starting futures: " + LocalDateTime.now());

        longRunningProcess()
                .handle((success, failure) -> {
            if (success != null) {
                System.out.println("we've received a str result so completable future succeeded");
            }
            System.out.println("we've received an exception so completable future failed " + failure.getMessage());
            return null;
        });

        Thread.sleep(10000);
        System.out.println(logCurrentThread() + " -- MAIN finished");
    }

    public static CompletableFuture<String> longRunningProcess() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println(logCurrentThread() + " -- starting longRunningProcess...will take 2 seconds");
            try {
                Thread.sleep(2_000);
                throw new RuntimeException("exception in completable future");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private static String logCurrentThread() {
        return Thread.currentThread().getName();
    }
}
