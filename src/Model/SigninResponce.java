package Model;

public class SigninResponce {

    public enum SigninResult {SIGNIN_OK, USERNAME_EXISTS, EMAIL_EXISTS}

    private String message;
    private User user;
    private SigninResult result;


    public SigninResponce() {
        this.message = "";
        this.user = new User();
        this.result = null;
    }

    public SigninResponce(String message, User user, SigninResult result) {
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

    public SigninResult getResult() {
        return result;
    }

    public void setResult(SigninResult result) {
        this.result = result;
    }
}
