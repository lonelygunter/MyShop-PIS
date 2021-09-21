package Model;

import java.util.Date;

public class Order {

    private int id;
    private Date date;
    private Float price;
    private User user;
    private int userId;
    private Item item;
    private int itemId;


    public Order() {
        this.date = new Date();
        this.price = 0F;
        this.user = new User();
        this.itemId = 0;
        this.item = new Item();
        this.itemId = 0;
    }

    public Order(Date date, float price, User user, Item item) {
        this.date = date;
        this.price = price;
        this.user = user;
        setUserId(user.getId());
        this.item = item;
        setUserId(item.getId());
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}
