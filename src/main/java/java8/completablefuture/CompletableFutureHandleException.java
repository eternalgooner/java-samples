package java8.completablefuture;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

public class CompletableFutureHandleException {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(logCurrentThread() + " -- starting app...");

        // here we call a completable future that throws an exception
        // we can handle both success & failure scenarios in different ways

        System.out.println(logCurrentThread() + " -- starting futures: " + LocalDateTime.now());

        // 1. Calling 'handle' gives access to both success & failure outputs.
        // We can recover from failures using this API
        longRunningProcess()
                .handle((success, failure) -> {
                    if (success != null) {
                        System.out.println("we've received a str result so completable future succeeded");
                    }
                    System.out.println("we've received an exception so completable future failed " + failure.getMessage());
                    return "recovered from failure";
                });

        // 2. Calling 'whenComplete' gives access to both success & failure outputs.
        // We cannot recover from failures using this API
        longRunningProcess()
                .whenComplete((success, failure) -> {
                    if (success != null) {
                        System.out.println("we've received a str result so completable future succeeded");
                    }
                    System.out.println("we've received an exception so completable future failed " + failure.getMessage());
                });

        // 3. Calling 'exceptionally' gives access to failure scenario only.
        // We cannot recover from failures using this API
        longRunningProcess()
                .exceptionally((failure) -> {
                    System.out.println("we've received an exception so completable future failed " + failure.getMessage());
                    return null;
                });

        Thread.sleep(10000);
        System.out.println(logCurrentThread() + " -- MAIN finished");
    }

    public static CompletableFuture<String> longRunningProcess() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println(logCurrentThread() + " -- starting longRunningProcess...throw exception");
            throw new RuntimeException("exception in completable future");
        });
    }

    private static String logCurrentThread() {
        return Thread.currentThread().getName();
    }
}
