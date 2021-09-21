package Model;

public class Product extends Item implements IProduct {

    private Position position;
    private int positionId;
    private int quantity;


    public Product() {
        super();
        this.position = new Position();
        this.positionId = 0;
        this.quantity = 0;
    }

    public Product(String name, String description, Float price, String type, int available, Wholesaler wholesaler, Category category, Position position, int quantity) {
        super(name, description, price, type, available, wholesaler, category);
        this.position = position;
        setPositionId(position.getId());
        this.quantity = quantity;
    }


    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * per avere la giusta formattazione sul documento pdf in LaTex
     */
    public String stringFormattingRow() {
        return getName() + " & " + getDescription() + " & " + getPrice() + " & " + getQuantity() + " \\\\\\\\ [0.5ex]";
    }
}
