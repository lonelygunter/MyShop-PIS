package Model;

import java.util.Date;

public class Manager extends User{

    public Manager (int id, String username, String password, String name, String surname, Date age, String email, String telephone, String cap, String street, String role, int disable) {
        super(username, password, name, surname, age, email, telephone, cap, street, role, disable);
        setId(id);
    }

    public Manager (String username, String password, String name, String surname, Date age, String email, String telephone, String cap, String street, String role, int disable) {
        super(username, password, name, surname, age, email, telephone, cap, street, role, disable);
    }

    public Manager() {
        super();
    }
}
