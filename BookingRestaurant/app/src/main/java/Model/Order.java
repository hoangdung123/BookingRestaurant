package Model;

public class Order {
    private String productId;
    private String productName;
    private String Quantity;
    private String price;

    public Order() {
    }

    public Order(String productId, String productName, String quantity, String price) {
        this.productId = productId;
        this.productName = productName;
        Quantity = quantity;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
