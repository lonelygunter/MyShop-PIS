package DAO;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import Business.PswEncryption;
import DbInterface.DbConnection;
import DbInterface.DbOperationExecutor;
import DbInterface.IDbConnection;
import DbInterface.IDbOperation;
import DbInterface.ReadOperation;
import Model.EmailNotification;
import Model.User;
import View.AppFrame;

public class UserDAO implements IUserDAO {

    private static UserDAO instance = new UserDAO();
    private User user;
    private static IDbConnection conn;
    private static ResultSet rs;


    private UserDAO() {
        user = null;
        conn = null;
        rs = null;
    }

    public static UserDAO getInstance() {
        return instance;
    }

    /**
     * per cercare User tramite id
     */
    @Override
    public User findById(int id) {
        conn = DbConnection.getInstance();
        String sql = "SELECT Id, Username, Name, Surname, Age, Email, Telephone, Street, Cap, Role, Disable FROM myshopmf.User WHERE myshopmf.User.Id = '" + id + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                user = new User();
                user.setId(rs.getInt("Id"));
                user.setUsername(rs.getString("Username"));
                user.setName(rs.getString("Name"));
                user.setSurname(rs.getString("Surname"));
                user.setAge(rs.getDate("Age"));
                user.setEmail(rs.getString("Email"));
                user.setTelephone(rs.getString("Telephone"));
                user.setStreet(rs.getString("Street"));
                user.setCap(rs.getString("Cap"));
                user.setRole(rs.getString("Role"));
                user.setDisable(rs.getInt("Disable"));
                return user;
            }
        } catch (SQLException e) {

            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {

            System.out.println("Resultset: " + e.getMessage());
        } finally {
            conn.close();
        }
        return null;
    }

    /**
     * per avere tutti i dati presenti nella tabella User
     */
    @Override
    public ArrayList<User> findAll() {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT Id, Name, Surname, Age, Email, Telephone, Street, Cap, Role, Disable FROM myshopmf.User;");
        ArrayList<User> users = new ArrayList<>();
        try {
            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt("Id"));
                user.setName(rs.getString("Name"));
                user.setSurname(rs.getString("Surname"));
                user.setAge(rs.getDate("Age"));
                user.setEmail(rs.getString("Email"));
                user.setTelephone(rs.getString("Telephone"));
                user.setStreet(rs.getString("Street"));
                user.setCap(rs.getString("Cap"));
                user.setRole(rs.getString("Role"));
                user.setDisable(rs.getInt("Disable"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            System.out.println("Resultset: " + e.getMessage());
        } finally {
            conn.close();
        }
        return null;
    }

    /**
     * per aggiungere un nuovo User nel db
     */
    @Override
    public int add(User u) {
        conn = DbConnection.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        String sql = "INSERT INTO `myshopmf`.`User` (`Username`, `Password`, `Name`, `Surname`, `Age`, `Email`, `Telephone`, `Street`, `Cap`, `Role`, `Disable`) VALUES ('" + u.getUsername() + "', '" + u.getPassword() + "', '" + u.getName() + "', '" + u.getSurname() + "', '" + formatter.format(u.getAge()) + "', '" + u.getEmail() + "', '" + u.getTelephone() + "', '" + u.getStreet() + "', '" + u.getCap() + "', '" + u.getRole() + "', '" + u.isDisable() + "');";
        int rowCount = conn.executeUpdate(sql);
        conn.close();
        return rowCount;
    }

    /**
     * per modificare un User esistente
     */
    @Override
    public int update(User u) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE `myshopmf`.`User` SET `Username` = '" + u.getUsername() + "', `Password` = '" + u.getPassword() + "', `Name` = '" + u.getName() + "', `Surname` = '" + u.getSurname() + "', `Age` = '" + u.getAge() + "', `Email` = '" + u.getEmail() + "', `Telephone` = '" + u.getTelephone() + "', `Street` = '" + u.getStreet() + "', `Cap` = '" + u.getCap() + "', `Role` = '" + u.getRole() + "', `Disable` = '" + u.isDisable() + "' WHERE (`Id` = '" + u.getId() + "');");
        conn.close();
        return rowCount;
    }

    /**
     * per eliminare un User in base al suo Id
     */
    @Override
    public int removeById(int id) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM `myshopmf`.`User` WHERE (`Id` = '" + id + "');");
        conn.close();
        return rowCount;
    }

    /**
     * per verificare che un Username esiste nel db
     */
    @Override
    public boolean userExists(String username) {
        conn = DbConnection.getInstance();
        boolean userExists = false;

        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT count(*) AS `C` FROM `myshopmf`.`User` WHERE (`User`.`Username` = '" + username + "');";
        ReadOperation op = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(op);

        try {
            rs.next();
            if(rs.getRow()==1 && rs.getInt("C")==1) userExists = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            conn.close();
        }

        return userExists;
    }

    /**
     * per verificare che le credenziali inserite siamo valide
     */
    @Override
    public boolean credentialsOk(String username, String password) {
        boolean pswCheck = false;
        boolean userExists = false;

        PswEncryption pe = new PswEncryption();
        pe.setOnlyHashed(getHashedPassword(username));
        pswCheck = pe.check(password);

        userExists = userExists(username);

        if (pswCheck && userExists) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * per verificare che la password inserita sia valida
     */
    @Override
    public String getHashedPassword(String username) {
        conn = DbConnection.getInstance();
        String pswHached = "";

        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT `User`.`Password` FROM `myshopmf`.`User` WHERE (`User`.`Username` = '" + username + "');";
        ReadOperation op = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(op);

        try {
            rs.next();
            pswHached = rs.getString(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            conn.close();
        }

        return pswHached;
    }

    /**
     * per recuperare un User tramite il suo Username
     */
    @Override
    public User getByUsername(String username) {
        conn = DbConnection.getInstance();
        String sql = "SELECT `Id`, `Username`, `Name`, `Surname`, `Age`, `Email`, `Telephone`, `Street`, `Cap`, `Role`, `Disable` FROM `myshopmf`.`User` WHERE (`User`.`Username` = '" + username + "');";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                user = new User();
                user.setId(rs.getInt("Id"));
                user.setUsername(rs.getString("Username"));
                user.setName(rs.getString("Name"));
                user.setSurname(rs.getString("Surname"));
                user.setAge(rs.getDate("Age"));
                user.setEmail(rs.getString("Email"));
                user.setTelephone(rs.getString("Telephone"));
                user.setStreet(rs.getString("Street"));
                user.setCap(rs.getString("Cap"));
                user.setRole(rs.getString("Role"));
                user.setDisable(rs.getInt("Disable"));
                return user;
            }
        } catch (SQLException e) {
            // handle any errors
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            // handle any errors
            System.out.println("Resultset: " + e.getMessage());
        } finally {
            conn.close();
        }
        return null;
    }

    /**
     * per verificare che l'User inserito sia un Manager
     */
    @Override
    public boolean managerExists(User u) {
        conn = DbConnection.getInstance();
        boolean managerExists = false;

        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT count(*) AS `C` FROM `myshopmf`.`User` AS `U` INNER JOIN `myshopmf`.`Manager` AS `M` ON `U`.`Id` = `M`.`User_Id` WHERE (`U`.`Id` = '" + u.getId() + "');";
        ReadOperation op = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(op);

        try {
            rs.next();
            if(rs.getRow()==1 && rs.getInt("C")==1) managerExists = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            conn.close();
        }

        return managerExists;
    }

    /**
     * per verificare che l'User inserito sia un Administrator
     */
    @Override
    public boolean administratorExists(User u) {
        conn = DbConnection.getInstance();
        boolean administratorExists = false;

        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT count(*) AS `C` FROM `myshopmf`.`User` AS `U` INNER JOIN `myshopmf`.`Administrator` AS `A` ON `U`.`Id` = `A`.`User_Id` WHERE (`U`.`Id` = '" + u.getId() + "');";
        ReadOperation op = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(op);

        try {
            rs.next();
            if(rs.getRow()==1 && rs.getInt("C")==1) administratorExists = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            conn.close();
        }

        return administratorExists;
    }

    /**
     * per disabilitare un utente dalle sue funzioni
     */
    @Override
    public int disable(AppFrame appFrame, User u) {
        u.setDisable(1);
        UserDAO.getInstance().update(u);
        return 0;
    }

    /**
     * per inviare email di notifica
     */
    @Override
    public int sendEmail(String recipient, String object, String body, String path) throws AddressException, MessagingException, IOException {
        EmailNotification mail = new EmailNotification();
        mail.setupServerProperties();
        mail.sendEmail(recipient, object, body, path);
            
        return 0;
    }

    /**
     * per verificare che un'email esista nel db
     */
    @Override
    public boolean emailExists(String email) {
        conn = DbConnection.getInstance();
        boolean userExists = false;

        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT count(*) AS `C` FROM `myshopmf`.`User` WHERE (`User`.`Email` = '" + email + "');";
        ReadOperation op = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(op);

        try {
            rs.next();
            if(rs.getRow()==1 && rs.getInt("C")==1) userExists = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            conn.close();
        }

        return userExists;
    }

    /**
     * per avere tutti i dati presenti nella tabella User
     */
    @Override
    public ArrayList<User> findAllOrderBy(String sql) {
        if (sql.equals("")) {
            sql = "SELECT Id, Username, Name, Surname, Age, Email, Telephone, Street, Cap, Role, Disable FROM myshopmf.User AS u WHERE (u.Role = 'U');";
        }

        conn = DbConnection.getInstance();
        rs = conn.executeQuery(sql);
        ArrayList<User> users = new ArrayList<>();
        try {
            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt("Id"));
                user.setUsername(rs.getString("Username"));
                user.setName(rs.getString("Name"));
                user.setSurname(rs.getString("Surname"));
                user.setAge(rs.getDate("Age"));
                user.setEmail(rs.getString("Email"));
                user.setTelephone(rs.getString("Telephone"));
                user.setStreet(rs.getString("Street"));
                user.setCap(rs.getString("Cap"));
                user.setRole(rs.getString("Role"));
                user.setDisable(rs.getInt("Disable"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            System.out.println("Resultset: " + e.getMessage());
        } finally {
            conn.close();
        }
        return null;
    }
}