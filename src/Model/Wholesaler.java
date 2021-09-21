package Model;

import java.util.ArrayList;
import java.util.List;

public class Wholesaler {

    protected int id;
    protected String name;
    protected String email;
    protected String telephone;
    protected String website;
    protected String city;
    protected String nation;
    protected List<Item> items;


    public Wholesaler() {
        this.name = "";
        this.email = "";
        this.telephone = "";
        this.website = "";
        this.city = "";
        this.nation = "";
        this.items = new ArrayList<>();
    }

    public Wholesaler(String name, String email, String telephone, String website, String city, String nation, List<Item> items) {
        this.name = name;
        this.email = email;
        this.telephone = telephone;
        this.website = website;
        this.city = city;
        this.nation = nation;
        this.items = items;
    }

    public Wholesaler(String name, String email, String telephone, String website, String city, String nation) {
        this.name = name;
        this.email = email;
        this.telephone = telephone;
        this.website = website;
        this.city = city;
        this.nation = nation;
    }

    public Wholesaler(int id, String name, String email, String telephone, String website, String city, String nation) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.telephone = telephone;
        this.website = website;
        this.city = city;
        this.nation = nation;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }
}
