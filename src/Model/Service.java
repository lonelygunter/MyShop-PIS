package Model;

public class Service extends Item {

    public Service (String name, String description, Float price, String type, int available, Wholesaler wholesaler, Category category, Position position, int quantity) {
        super(name, description, price, type, available, wholesaler, category);
    }

    public Service() {
        super();
    }
}