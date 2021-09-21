package Model;

public class ListResponce {

    public enum ListResult {LIST_CREATED, LISTNAME_EXISTS, LISTNAME_NOT_EXISTS, ITEM_EXISTS, ITEM_NOT_EXISTS, LIST_NOT_EXISTS}

    private String message;
    private List list;
    private ListResult result;


    public ListResponce() {
        this.message = "";
        this.list = new List();
        this.result = null;
    }

    public ListResponce(String message, List list, ListResult result) {
        this.message = message;
        this.list = list;
        this.result = result;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public ListResult getResult() {
        return result;
    }

    public void setResult(ListResult result) {
        this.result = result;
    }
}
