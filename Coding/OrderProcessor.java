import java.math.BigDecimal;

public interface OrderProcessor {
    // Default method
    default BigDecimal calculateTotal(Order order) {
        if (order == null || order.getItems() == null) {
            return BigDecimal.ZERO;
        }
        return order.getItems().stream()
        .map(Product::getPrice)
        .filter(price -> price != null)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // Static method
    static String formatPrice(BigDecimal price) {
        if (price == null) {
            return "$0.00";
        }
        return "$" + price;
    }

    // Abstract method
    void processOrder(Order order);
}
