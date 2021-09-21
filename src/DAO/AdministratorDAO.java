package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DbInterface.DbConnection;
import DbInterface.DbOperationExecutor;
import DbInterface.IDbConnection;
import DbInterface.IDbOperation;
import DbInterface.ReadOperation;
import Model.Administrator;
import Model.User;

public class AdministratorDAO implements IAdministratorDAO{

    private static AdministratorDAO instance = new AdministratorDAO();
    private Administrator administrator;
    private static IDbConnection conn;
    private static ResultSet rs;

    
    private AdministratorDAO(){
        administrator = null;
        conn = null;
        rs = null;
    }

    public static AdministratorDAO getInstance() {
        return instance;
    }

    /**
     * per cercare Administrator tramite id
     */
    @Override
    public Administrator findById(int id) {
        conn = DbConnection.getInstance();
        String sql = "SELECT `User_Id` FROM `myshopmf`.`Administrator` WHERE (`myshopmf`.`Administrator`.`User_Id` = '" + id + "');";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                administrator = new Administrator();
                administrator.setId(rs.getInt("User_Id"));
                return administrator;
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
     * per avere tutti i dati presenti nella tabella Administrator
     */
    @Override
    public ArrayList<Administrator> findAll() {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT `User_Id` FROM `myshopmf`.`Administrator`;");
        ArrayList<Administrator> administrators = new ArrayList<>();
        try {
            while (rs.next()) {
                administrator = new Administrator();
                administrator.setId(rs.getInt("User_Id"));
                administrators.add(administrator);
            }
            return administrators;
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
     * per aggiungere un nuovo Administrator nel db
     */
    @Override
    public int add(User administrator) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO `myshopmf`.`Administrator` (`User_Id`) VALUES ('" + administrator.getId() + "');");
        conn.close();
        return rowCount;
    }

    /**
     * per modificare un Administrator esistente
     */
    @Override
    public int update(User administrator, int oldId) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE `myshopmf`.`Administrator` SET `User_Id` = '" + administrator.getId() + "' WHERE (`User_Id` = '" + oldId + "');");
        conn.close();
        return rowCount;
    }

    /**
     * per eliminare un Administrator in base al suo Id
     */
    @Override
    public int removeById(int id) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM `myshopmf`.`Administrator` WHERE (`User_Id` = '" + id + "');");
        conn.close();
        return rowCount;
    }
}
