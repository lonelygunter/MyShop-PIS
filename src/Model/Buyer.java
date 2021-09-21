package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Buyer extends User {

    private List<Model.List> lists;


    public Buyer() {
        super();
        this.lists = new ArrayList<>();
    }

    public Buyer(int id, String username, String password, String name, String surname, Date age, String email, String telephone, String cap, String street, String role, int disable, List<Model.List> lists) {
        super(username, password, name, surname, age, email, telephone, cap, street, role, disable);
        this.lists = lists;
    }


    public List<Model.List> getLists() {
        return lists;
    }

    public void setLists(List<Model.List> lists) {
        this.lists = lists;
    }

}
