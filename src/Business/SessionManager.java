package Business;

import java.util.HashMap;

public class SessionManager {

    private static SessionManager instance;

    private HashMap<String, Object> session = new HashMap<>(); 

    public SessionManager() {}

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public HashMap<String, Object> getSession() {
        return session;
    }


}
