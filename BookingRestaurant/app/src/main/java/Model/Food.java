package Model;

public class Food {
    private String name;
    private String imageUrl;
    private long price;
    private float rating;
    private int categoryId;

    public Food(String name, String imageUrl, long price, float rating, int categoryId) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
        this.rating = rating;
        this.categoryId = categoryId;
    }

    public Food() {
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
