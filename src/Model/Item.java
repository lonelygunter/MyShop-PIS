package Model;

import java.util.List;

public class Item {

    private int id;
    private String name;
    private String description;
    private Float price;
    private String type;
    private int available;
    private Wholesaler wholesaler;
    private int wholesalerId;
    private Category category;
    private int categoryId;
    private List<Feedback> feedbacks; //notache che non viene implementata
    


    public Item() {
        this.name = "";
        this.description = "";
        this.price = 0F;
        this.type = "";
        this.available = 0;
        this.wholesaler = new Wholesaler();
        this.wholesalerId = 0;
        this.category = new Category();
        this.categoryId = 0;    
    }

    public Item(int id, String name, String description, Float price, String type, int available, Wholesaler wholesaler, Category category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.type = type;
        this.available = available;
        this.wholesaler = wholesaler;
        setWholesalerId(wholesaler.getId());
        this.category = category;
        setCategoryId(category.getId());
    }

    public Item(String name, String description, Float price, String type, int available, Wholesaler wholesaler, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.type = type;
        this.available = available;
        this.wholesaler = wholesaler;
        setWholesalerId(wholesaler.getId());
        this.category = category;
        setCategoryId(category.getId());
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public int isAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public Wholesaler getWholesaler() {
        return wholesaler;
    }

    public void setWholesaler(Wholesaler wholesaler){
        this.wholesaler = wholesaler;
    }

    public int getWholesalerId() {
        return wholesalerId;
    }

    public void setWholesalerId(int wholesalerId) {
        this.wholesalerId = wholesalerId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }
}
