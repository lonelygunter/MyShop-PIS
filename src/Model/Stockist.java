package Model;

import java.util.List;

public class Stockist extends Wholesaler {

    public Stockist (String name, String email, String telephone, String website, String city, String nation, List<Item> items) {
        super(name, email, telephone, website, city, nation, items);
    }

    public Stockist() {
        super();
    }
}
