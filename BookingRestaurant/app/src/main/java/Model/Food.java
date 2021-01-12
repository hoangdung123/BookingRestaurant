package Model;

public class Food {
    private String name;
    private long price;
    public void setPrice(long price) {
        this.price = price;
    }

    public long getPrice() {
        return price;
    }

    private float rating;

    public Food() {
    }

    public Food(String name, float rating) {

        this.name = name;
        this.rating = rating;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
