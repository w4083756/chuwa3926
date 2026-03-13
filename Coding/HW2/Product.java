package HW2;
import java.util.Objects;

public class Product {
    private String id;
    private String name;
    private double price;

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{id='" + id + "'', name='" + name + "'', price='" + price + "''}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Product other = (Product) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static void main(String[] args) {
        Product p1 = new Product("123", "iphone13", 500);
        Product p2 = new Product("123", "iphone14", 1000);
        
        System.out.println("Same product: " + p1.equals(p2));
        System.out.println("Same hashcode: " + (p1.hashCode() == p2.hashCode()));
        System.out.println(p1.toString());
        System.out.println(p2.toString());
    }
}
