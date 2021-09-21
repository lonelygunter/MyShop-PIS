package Model;

import java.util.List;

public class Producer extends Wholesaler{

    public Producer (int id, String name, String email, String telephone, String website, String city, String nation, List<Item> items) {
        super(name, email, telephone, website, city, nation, items);
    }

    public Producer() {
        super();
    }
}
