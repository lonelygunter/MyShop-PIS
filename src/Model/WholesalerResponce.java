package Model;

public class WholesalerResponce {

    public enum WholesalerResult {W_NOT_EXISTS, W_DELETED, W_EXISTS, W_CREATED, W_MODIFIED}

    private String message;
    private Wholesaler wholesaler;
    private WholesalerResult result;


    public WholesalerResponce() {
        this.message = "";
        this.wholesaler = new Wholesaler();
        this.result = null;
    }

    public WholesalerResponce(String message, Wholesaler wholesaler, WholesalerResult result) {
        this.message = message;
        this.wholesaler = wholesaler;
        this.result = result;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Wholesaler getWholesaler() {
        return wholesaler;
    }

    public void setWholesaler(Wholesaler wholesaler) {
        this.wholesaler = wholesaler;
    }

    public WholesalerResult getResult() {
        return result;
    }

    public void setResult(WholesalerResult result) {
        this.result = result;
    }
}
