package HW5;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureExample {
    public static void main(String[] args) {

        int a = 3;
        int b = 5;

        // async task for sum
        CompletableFuture<Integer> sumFuture = CompletableFuture.supplyAsync(() -> a + b);

        // async task for product
        CompletableFuture<Integer> productFuture = CompletableFuture.supplyAsync(() -> a * b);

        // wait for both and print result
        sumFuture.thenAccept(sum -> System.out.println("Sum: " + sum));

        productFuture.thenAccept(product -> System.out.println("Product: " + product));

        // block main thread to ensure output print
        CompletableFuture.allOf(sumFuture, productFuture).join();
    }
}
