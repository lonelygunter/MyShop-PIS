package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import DbInterface.DbConnection;
import DbInterface.DbOperationExecutor;
import DbInterface.IDbConnection;
import DbInterface.IDbOperation;
import DbInterface.ReadOperation;
import Model.Manager;
import Model.User;

public class ManagerDAO implements IManagerDAO{

    private static ManagerDAO instance = new ManagerDAO();
    private Manager manager;
    private static IDbConnection conn;
    private static ResultSet rs;

    
    private ManagerDAO(){
        manager = null;
        conn = null;
        rs = null;
    }

    public static ManagerDAO getInstance() {
        return instance;
    }

    /**
     * per cercare Manager tramite id
     */
    @Override
    public User findById(int id) {
        conn = DbConnection.getInstance();
        String sql = "SELECT `User_Id` FROM `myshopmf`.`Manager` WHERE (`myshopmf`.`Manager`.`User_Id` = '" + id + "');";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                return UserDAO.getInstance().findById(rs.getInt("User_Id"));
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
     * per avere tutti i dati presenti nella tabella Manager
     */
    @Override
    public ArrayList<Manager> findAll() {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT `User_Id` FROM `myshopmf`.`Manager`;");
        ArrayList<Manager> managers = new ArrayList<>();
        try {
            while (rs.next()) {
                manager = new Manager();
                manager.setId(rs.getInt("User_Id"));
                managers.add(manager);
            }
            return managers;
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
     * per aggiungere un nuovo Manager nel db
     */
    @Override
    public int add(User manager) {
        UserDAO.getInstance().add(manager);
        String sql = "INSERT INTO `myshopmf`.`Manager` (`User_Id`) VALUES ('" + UserDAO.getInstance().getByUsername(manager.getUsername()).getId() + "');";

        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate(sql);
        conn.close();
        return rowCount;
    }

    /**
     * per modificare un Manager esistente
     */
    @Override
    public int update(User manager) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String sql = "UPDATE `myshopmf`.`User` SET `Username` = '" + manager.getUsername() + "', `Password` = '" + manager.getPassword() + "', `Name` = '" + manager.getName() + "', `Surname` = '" + manager.getSurname() + "', `Age` = '" + formatter.format(manager.getAge()) + "', `Email` = '" + manager.getEmail() + "', `Telephone` = '" + manager.getTelephone() + "', `Street` = '" + manager.getStreet() + "', `Cap` = '" + manager.getCap() + "', `Role` = '" + manager.getRole() + "', `Disable` = '" + manager.isDisable() + "' WHERE (`Id` = '" + manager.getId() + "');";

        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate(sql);
        conn.close();
        return rowCount;
    }

    /**
     * per eliminare un Manager in base al suo Id
     */
    @Override
    public int removeById(int id) {
        UserDAO.getInstance().removeById(id);
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM `myshopmf`.`Manager` WHERE (`User_Id` = '" + id + "');");
        conn.close();
        return rowCount;
    }
}
