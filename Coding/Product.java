import java.math.BigDecimal;

public class Product {
    private String id;
    private String name;
    private BigDecimal price;
    private String category;
    private boolean available;
    
    //Constructor
    public Product(String id, String name, BigDecimal price, String category, boolean available) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.available = available;
    }

    //Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public boolean getAvailable() {
        return available;
    }

    //Setter
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    // toString()
    @Override
    public String toString() {
        return "Product{" + "id='" + id + "'" + ", name='" + name + "'" + ", price='" + price + "'" + 
        ", category='" + category + "'" + ", available'" + available + "'" + '}';
    }
}