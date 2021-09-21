package Model;

import java.util.Date;

public class Feedback {

    private int id;
    private String comment;
    private int rating;
    private Date date;
    private Item item;
    private int purchase_has_item;
    private int answering;


    public Feedback() {
        this.comment = "";
        this.rating = 0;
        this.date = new Date();
        this.item = new Item();
        this.purchase_has_item = 0;
        this.answering = 0;
    }

    public Feedback(String comment, int rating, Date date, Item item, int purchase_has_item, int answering) {
        this.comment = comment;
        this.rating = rating;
        this.date = date;
        this.item = item;
        this.purchase_has_item = purchase_has_item;
        this.answering = answering;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Item getItem(){
        return item;
    }

    public void setItem(Item item){
        this.item = item;
    }

    public int getItemId(){
        return item.getId();
    }

    public int getPurchaseHasItem(){
        return purchase_has_item;
    }

    public void setPurchaseHasItem(int purchase_has_item){
        this.purchase_has_item = purchase_has_item;
    }

    public int getAnswering() {
        return answering;
    }

    public void setAnswering(int answering) {
        this.answering = answering;
    }
}
