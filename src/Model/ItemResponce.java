package Model;

public class ItemResponce {

    public enum ItemResult {ITEM_NOT_EXISTS, ITEM_DELETED, ITEM_EXISTS, ITEM_NOT_IN_ORDER, ITEM_CREATED, ITEM_MODIFIED}

    private String message;
    private Item item;
    private ItemResult result;


    public ItemResponce() {
        this.message = "";
        this.item = new Item();
        this.result = null;
    }

    public ItemResponce(String message, Item item, ItemResult result) {
        this.message = message;
        this.item = item;
        this.result = result;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public ItemResult getResult() {
        return result;
    }

    public void setResult(ItemResult result) {
        this.result = result;
    }
}
