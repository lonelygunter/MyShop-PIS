package Model;


public class UserResponce {

    public enum UserResult {USER_NOT_EXISTS, USER_DISABLED, USER_DELETED}

    private String message;
    private User user;
    private UserResult result;


    public UserResponce() {
        this.message = "";
        this.user = new User();
        this.result = null;
    }

    public UserResponce(String message, User user, UserResult result) {
        this.message = message;
        this.user = user;
        this.result = result;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserResult getResult() {
        return result;
    }

    public void setResult(UserResult result) {
        this.result = result;
    }
}
