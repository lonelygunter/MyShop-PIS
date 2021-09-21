package Model;

public class LoginResponse {

    public enum LoginResult {LOGIN_OK, WRONG_PASSWORD, USER_NOT_EXISTS, USER_DISABLED}

    private String message;
    private User user;
    private LoginResult result;


    public LoginResponse() {
        this.message = "";
        this.user = new User();
        this.result = null;
    }

    public LoginResponse(String message, User user, LoginResult result) {
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

    public LoginResult getResult() {
        return result;
    }

    public void setResult(LoginResult result) {
        this.result = result;
    }


}
