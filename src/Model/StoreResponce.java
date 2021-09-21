package Model;

public class StoreResponce {

    public enum StoreResult {S_NOT_EXISTS, S_DELETED, S_EXISTS, S_CREATED, S_MODIFIED}

    private String message;
    private Store store;
    private StoreResult result;


    public StoreResponce() {
        this.message = "";
        this.store = new Store();
        this.result = null;
    }

    public StoreResponce(String message, Store store, StoreResult result) {
        this.message = message;
        this.store = store;
        this.result = result;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public StoreResult getResult() {
        return result;
    }

    public void setResult(StoreResult result) {
        this.result = result;
    }
}
