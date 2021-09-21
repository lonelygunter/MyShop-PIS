package Model;

import java.util.ArrayList;
import java.util.List;

public class Category {

    private int id;
    private String name;
    private int fatherId;
    private List<Category> subcategory;


    public Category() {
        this.name = "";
        this.fatherId = 0;
        this.subcategory = new ArrayList<>();
    }

    public Category(String name, int fatherId, List<Category> subcategory) {
        this.name = name;
        this.fatherId = fatherId;
        this.subcategory = subcategory;
    }

    public Category(String name, int fatherId) {
        this.name = name;
        this.fatherId = fatherId;
    }

    public Category(int id, String name, int fatherId) {
        this.id = id;
        this.name = name;
        this.fatherId = fatherId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFatherId() {
        return fatherId;
    }

    public void setFatherId(int fatherId) {
        this.fatherId = fatherId;
    }

    public void addSubCategory(Category c) {
        subcategory.add(c);
    }

    public List<Category> getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(List<Category> subcategory) {
        this.subcategory = subcategory;
    }
}

