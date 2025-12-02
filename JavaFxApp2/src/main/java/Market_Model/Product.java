package Market_Model;

public abstract class Product implements HasBasicInfo {
    protected int id;
    protected String name;
    protected double price;
    protected String description;
    protected String imageURL;


    public Product(int id, String name, double price, String description, String imageURL) {
        if (price > 1_000_000) {
            throw new IllegalArgumentException("Баға 1 000 000 теңгеден жоғары болмайтын товарлар ");
        }
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageURL = imageURL;

    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public String getDescription() {
        return description;
    }
    public String getImageURL() {
        return imageURL;
    }



    // Abstract method
    public abstract String getCategoryName();


}
