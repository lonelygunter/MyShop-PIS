package Model;

public class Purchase {

    private int id;
    private Buyer buyer;
    private int buyerId;


    public Purchase() {
        this.buyer = new Buyer();
        this.buyerId = 0;
    }

    public Purchase(Buyer buyer) {
        this.buyer = buyer;
        setBuyerId(buyer.getId());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }
}
