import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TestOrderService {
    public static void main(String[] args) {
        Product p1 = new Product("P1", "Laptop", new BigDecimal("1200.00"), "Electonic", true);
        Product p2 = new Product("P2", "Mouse", new BigDecimal("25.50"), "Electonic", true);
        Product p3 = new Product("P3", "Desk", new BigDecimal("200.00"), "Furniture", true);
        Product p4 = new Product("P4", "Pen", new BigDecimal("5.00"), "Stationary", true);
        Product p5 = new Product("P5", "Notebook", new BigDecimal("15.00"), "Stationary", true);
        
        Order o1 = new Order("O1", LocalDateTime.now(), Arrays.asList(p1, p2), "a@test.com");
        Order o2 = new Order("O2", LocalDateTime.now(), Arrays.asList(p3), "b@test.com");
        Order o3 = new Order("O3", LocalDateTime.now(), Arrays.asList(p4, p5), "c@test.com");

        List<Order> orders = Arrays.asList(o1, o2, o3);
        OrderService service = new OrderService();

        // filter order > $100
        System.out.println("Order with total > $100");
        List<Order> filteredOrders = service.filterOrders(orders, 
            order -> service.calculateTotal(order).compareTo(new BigDecimal("100")) > 0
        );
        filteredOrders.forEach(System.out::println);

        // Grouping order by category
        System.out.println("\nOrders grouped by category:");
        Map<String, List<Order>> groupedOrders = service.groupOrdersByCategory(orders);
        groupedOrders.forEach((categoty, orderList) -> {
            System.out.println(categoty + ":");
            orderList.forEach(System.out::println);
        });

        // Finding the most expensive order
        Optional<Order> mostExpensiveOrder = service.findMostExpensiveOrder(orders);
        mostExpensiveOrder.ifPresentOrElse(
            order -> {
                System.out.println(order);
                System.out.println("Total: " + 
                        OrderProcessor.formatPrice(service.calculateTotal(order))
                );
        },
        () -> System.out.println("No orders found."));

        // process all orders using method reference
        System.out.println("\nProcessing all orders:");
        orders.forEach(service::processOrder);
        
    }
}
