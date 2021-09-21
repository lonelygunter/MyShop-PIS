package Model;

public class ManagerResponce {

    public enum ManagerResult {M_NOT_EXISTS, M_DELETED, M_EXISTS, M_CREATED, M_MODIFIED}

    private String message;
    private Manager manager;
    private ManagerResult result;


    public ManagerResponce() {
        this.message = "";
        this.manager = new Manager();
        this.result = null;
    }

    public ManagerResponce(String message, Manager manager, ManagerResult result) {
        this.message = message;
        this.manager = manager;
        this.result = result;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public ManagerResult getResult() {
        return result;
    }

    public void setResult(ManagerResult result) {
        this.result = result;
    }
}
