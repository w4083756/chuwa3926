package HW3;

interface IStatusCode {
    int getCode();
    String getDescription();
}   
public enum OrderStatus implements IStatusCode{
    PENDING(0, "Order is pending"),
    PAID(1, "Payment received"),
    SHIPPED(2, "Order has been shipped"),
    DELIVERED(3, "Order delivered"),
    CANCELLED(-1, "Order cancelled");

    private final int code;
    private final String description;

    OrderStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    public static void main(String[] args) {
        for (OrderStatus o : OrderStatus.values()) {
            System.out.println(o + "->" + o.getCode() + "->" + o.getDescription());
        }

        OrderStatus paid = OrderStatus.valueOf("PAID");
        System.out.println("Selected status: " + paid);
    }

}
