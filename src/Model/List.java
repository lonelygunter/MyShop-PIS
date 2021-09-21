package Model;

import java.util.ArrayList;
import java.util.Date;

public class List{
    private int id;
    private String name;
    private Date date;
    private Float price;
    private User buyer;
    private int buyerId;
    private String state;
    private ArrayList<Product> products;


    public List() {
        this.id = 0;
        this.name = "";
        this.date = new Date();
        this.price = 0F;
        this.buyer = new User();
        this.buyerId = 0;
        this.state = "";
        this.products = new ArrayList<>();
    }

    public List(String name, Date date, float price, User buyer, String state, ArrayList<Product> products) {
        this.name = name;
        this.date = date;
        this.price = price;
        this.buyer = buyer;
        setBuyerId(buyer.getId());
        this.state = state;
        this.products = products;
    }

    public List(int id, String name, Date date, float price, Buyer buyer, String state, ArrayList<Product> products) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.price = price;
        this.buyer = buyer;
        setBuyerId(buyer.getId());
        this.state = state;
        this.products = products;
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

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    /**
     * per avere la formattazione giusta per LaTex
     */
    public String stringFormattingTable() {
        String table = "";
        for (int i = 0; i < products.size(); i++) {
            table = table + "        " + products.get(i).stringFormattingRow() + " \n";
        }

        return table;
    }
}
