package Model;

import java.util.Date;

public class Administrator extends User{

    public Administrator (String username, String password, String name, String surname, Date age, String email, String telephone, String cap, String street, String role, int disable) {
        super(username, password, name, surname, age, email, telephone, cap, street, role, disable);
    }

    public Administrator() {
        super();
    }
}
