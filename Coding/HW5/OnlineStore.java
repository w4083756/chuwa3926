package HW5;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class OnlineStore {

    private static final HttpClient client = HttpClient.newHttpClient();
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int productId = 1;

        CompletableFuture<Product> productFuture = fetchProduct(productId).exceptionally (ex ->
                {
                    log("Product API failed", ex);
                    return defaultProduct(productId);
                });

        CompletableFuture<Review[]> reviewFuture = fetchReviews(productId).exceptionally (ex ->
        {
            log("Review API failed", ex);
            return defaultReviews();
        });

        CompletableFuture<Inventory> inventoryFuture = fetchInventory(productId).exceptionally (ex ->
        {
            log("Inventory API failed", ex);
            return defaultInventory(productId);
        });

        CompletableFuture<StoreData> mergedFuture =
                productFuture.thenCombine(reviewFuture, (product, reviews) -> new TempStoreData(product, reviews))
                        .thenCombine(inventoryFuture, (temp, inventory) ->
                                new StoreData(temp.product, temp.reviews, inventory));

        StoreData result = mergedFuture.get();

        System.out.println("=== Merged Store Data With Fallbacks ===");
        System.out.println(result);
    }

    public static CompletableFuture<Product> fetchProduct(int id) {
        String url = "https://jsonplaceholder.typicode.com/posts" + id;
        return sendGet(url, Product.class);
    }

    public static CompletableFuture<Review[]> fetchReviews(int productId) {
        String url = "https://jsonplaceholder.typicode.com/posts" + productId + "/comments";
        return sendGet(url, Review[].class);
    }

    public static CompletableFuture<Inventory> fetchInventory(int id) {
        String url = "https://jsonplaceholder.typicode.com/posts" + id;
        return sendGet(url, Inventory.class);
    }

    public static <T> CompletableFuture<T> sendGet(String url, Class<T> clazz) {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(response -> {
                    if (response.statusCode() != 200) {
                        throw new RuntimeException("HTTP error: " + response.statusCode() + "for " + url);
                    }
                    return response.body();
                })
                .thenApply(body -> {
                    try {
                        return mapper.readValue(body, clazz);
                    } catch (IOException e) {
                        throw new RuntimeException("JSON parse failed for " + url, e);
                    }
                });

    }

    public static void log(String message, Throwable ex) {
        System.err.println("[ERROR] " + message);
        System.err.println("Reason: " + ex.getMessage());
    }

    public static Product defaultProduct(int id) {
        Product p = new Product();
        p.id = id;
        p.title = "Default Product";
        p.body = "No product data available";
        return p;
    }

    public static Review[] defaultReviews() {
        Review r = new Review();
        r.id = -1;
        r.name = "Default Review";
        r.body = "No Review data available";
        return new Review[]{r};
    }

    public static Inventory defaultInventory(int id) {
        Inventory i = new Inventory();
        i.id = id;
        i.title = "Default Inventory";
        i.completed = false;
        return i;
    }

    static class TempStoreData {
        Product product;
        Review[] reviews;

        TempStoreData(Product product, Review[] reviews) {
            this.product = product;
            this.reviews = reviews;
        }
    }

    static class StoreData {
        Product product;
        Review[] reviews;
        Inventory inventory;

        StoreData(Product product, Review[] reviews, Inventory inventory) {
            this.product = product;
            this.reviews = reviews;
            this.inventory = inventory;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Product: ").append(product).append("\n");
            sb.append("Inventory: ").append(inventory).append("\n");
            sb.append("Reviews:\n");
            if (reviews != null) {
                for (Review r : reviews) {
                    sb.append("  - ").append(r).append("\n");
                }
            }
            return sb.toString();
        }
    }

    static class Product {
        public int userId;
        public int id;
        public String title;
        public String body;

        @Override
        public String toString() {
            return "Product{id =" + id + ", title='" + title + "'}";
        }
    }

    static class Review {
        public int postId;
        public int id;
        public String name;
        public String email;
        public String body;

        @Override
        public String toString() {
            return "Review{id =" + id + ", name='" + name + "'}";
        }
    }

    static class Inventory {
        public int userId;
        public int id;
        public String title;
        public boolean completed;

        @Override
        public String toString() {
            return "Inventory{id =" + id + ", inStock='" + completed + "'}";
        }
    }


}
