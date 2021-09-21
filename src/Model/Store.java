package Model;

import java.util.ArrayList;
import java.util.List;

public class Store {

    private int id;
    private String telephone;
    private String street;
    private String cap;
    private String nation;
    private User manager;
    private int managerId;
    private List<IProduct> products;
    private ArrayList<Category> categories;
    private List<Buyer> buyers;


    public Store() {
        this.telephone = "";
        this.street = "";
        this.cap = "";
        this.nation = "";
        this.manager = new User();
        this.managerId = 0;
        this.products = new ArrayList<>();
        this.categories = new ArrayList<>();
        this.buyers = new ArrayList<>();
    }

    public Store(String telephone, String street, String cap, String nation, User manager, List<IProduct> products, ArrayList<Category> categories, List<Buyer> buyers) {
        this.telephone = telephone;
        this.street = street;
        this.cap = cap;
        this.nation = nation;
        this.manager = manager;
        setUserId(manager.getId());
        this.products = products;
        this.categories = categories;
        this.buyers = buyers;
    }

    public Store(String telephone, String street, String cap, String nation, User manager) {
        this.telephone = telephone;
        this.street = street;
        this.cap = cap;
        this.nation = nation;
        this.manager = manager;
        setUserId(manager.getId());
    }

    public Store(int id, String telephone, String street, String cap, String nation, User manager) {
        this.id = id;
        this.telephone = telephone;
        this.street = street;
        this.cap = cap;
        this.nation = nation;
        this.manager = manager;
        setUserId(manager.getId());
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public User getUser() {
        return manager;
    }

    public void setUser(User manager) {
        this.manager = manager;
    }

    public int getUserId() {
        return manager.getId();
    }

    public void setUserId(int id) {
        manager.setId(id);
    }
}
