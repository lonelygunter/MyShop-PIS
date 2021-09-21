package Business;

import java.util.Date;

import DAO.IUserDAO;
import DAO.UserDAO;
import Model.LoginResponse;
import Model.SigninResponce;
import Model.User;
import Model.UserResponce;

public class UserBusiness {

    private static UserBusiness instance;

    IUserDAO uDao = UserDAO.getInstance();

    public enum Privilege {MANAGE_SHOP, ADMIN_SYSTEM}

    /**
     * promette di sinconzzare l'accesso
     * (cioe' mette in fila i processi che vogliono usare il metodo)
     */
    public static synchronized UserBusiness getInstance() {
        if (instance == null) {
            instance = new UserBusiness();
        }
        return instance;
    }

    private UserBusiness() {
    }

    public void setuDao(IUserDAO uDao) {
        this.uDao = uDao;
    }

    public LoginResponse login(String username, String password) {
        LoginResponse res = new LoginResponse();
        res.setMessage("Errore non definito");

        // 1. Username non esistente
        if (!uDao.userExists(username)) {
            res.setMessage("Username non esistente");
            res.setResult(LoginResponse.LoginResult.USER_NOT_EXISTS);
            res.setUser(null);
            return res;
        }

        // 2. Username corretto, ma password sbagliata
        if (!uDao.credentialsOk(username, password)) {
            res.setMessage("Password sbagliata");
            res.setResult(LoginResponse.LoginResult.WRONG_PASSWORD);
            res.setUser(null);
            return res;
        }

        // 3. l'utente è disablitato
        if (uDao.getByUsername(username).isDisable() == 1) {
            res.setMessage("Utente disabilitato");
            res.setResult(LoginResponse.LoginResult.USER_DISABLED);
            res.setUser(null);
            return res;
        }

        // 4. Ottenere i dati dell'utente
        User u = uDao.getByUsername(username);
        // alternativa: restituire istanza specifica di Cliente, Manager o Amministratore
        if (u != null) {
            res.setMessage("Benvenuto " + u.getName() + "!");
            res.setUser(u);
            res.setResult(LoginResponse.LoginResult.LOGIN_OK);
        }

        return res;
    }

    public boolean userCan(User u, Privilege p) {

        IUserDAO uDao = UserDAO.getInstance();

        if (Privilege.MANAGE_SHOP.equals(p)) {
            // vediamo se u e' un manager
            return uDao.managerExists(u);
        }

        if (Privilege.ADMIN_SYSTEM.equals(p)) {
            // vediamo se u e' un amministratore
            return uDao.administratorExists(u);
        }

        return false;
    }

    public SigninResponce signin( String email, String password, String username, String name, String surname, Date age,  String telephone, String street, String cap){
        SigninResponce res = new SigninResponce();
        res.setMessage("Errore non definito");

        // 1. Username già registrato
        if (uDao.userExists(username)) {
            res.setMessage("Username già registrato, inserirne un altro.");
            res.setResult(SigninResponce.SigninResult.USERNAME_EXISTS);
            res.setUser(null);
            return res;
        }

        // 2. Email già registrata
        if (uDao.emailExists(email)) {
            res.setMessage("Email già registrata, inserirne un'altra.");
            res.setResult(SigninResponce.SigninResult.EMAIL_EXISTS);
            res.setUser(null);
            return res;
        }

        // 3. Ottenere i dati dell'utente
        User u = new User(username, password, name, surname, age, email, telephone, cap, street, "U", 0);
        if (u != null) {
            res.setMessage("Benvenuto " + u.getName() + "!");
            res.setUser(u);
            res.setResult(SigninResponce.SigninResult.SIGNIN_OK);
        }

        return res;
    }

    public UserResponce disable(User user){
        UserResponce res = new UserResponce();
        res.setMessage("Errore non definito");

        // 1. utente non esistente
        if (!uDao.userExists(user.getUsername())) {
            res.setMessage("Utente non esitente.");
            res.setResult(UserResponce.UserResult.USER_NOT_EXISTS);
            res.setUser(null);
            return res;
        }

        // 2. Ottenere i dati dell'utente
        User u = user;
        if (u != null) {
            res.setMessage("Utente disabilitato");
            res.setUser(u);
            res.setResult(UserResponce.UserResult.USER_DISABLED);
        }

        return res;
    }

    public UserResponce delete(int managerId){
        UserResponce res = new UserResponce();
        res.setMessage("Errore non definito");

        // 1. controllare se il grossista esiste
        if (!uDao.userExists(UserDAO.getInstance().findById(managerId).getUsername())) {
            res.setMessage("Manager non esistente.");
            res.setResult(UserResponce.UserResult.USER_NOT_EXISTS);
            res.setUser(null);
            return res;
        }

        // 2. Ottenere i dati dell'manager
        User i = UserDAO.getInstance().findById(managerId);
        if (i != null) {
            res.setMessage("Manager rimosso con successo");
            res.setUser(i);
            res.setResult(UserResponce.UserResult.USER_DELETED);
        }

        return res;
    }
}
