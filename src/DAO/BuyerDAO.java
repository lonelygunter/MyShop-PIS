package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DbInterface.DbConnection;
import DbInterface.DbOperationExecutor;
import DbInterface.IDbConnection;
import DbInterface.IDbOperation;
import DbInterface.ReadOperation;
import Model.Buyer;

public class BuyerDAO implements IBuyerDAO{

    private static BuyerDAO instance = new BuyerDAO();
    private Buyer buyer;
    private static IDbConnection conn;
    private static ResultSet rs;

    
    private BuyerDAO(){
        buyer = null;
        conn = null;
        rs = null;
    }

    public static BuyerDAO getInstance() {
        return instance;
    }

    /**
     * per cercare Buyer tramite id
     */
    @Override
    public Buyer findById(int id) {
        conn = DbConnection.getInstance();
        String sql = "SELECT `User_Id` FROM `myshopmf`.`Buyer` WHERE (`myshopmf`.`Buyer`.`User_Id` = '" + id + "');";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                buyer = new Buyer();
                buyer.setId(rs.getInt("User_Id"));
                return buyer;
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
     * per avere tutti i dati presenti nella tabella Buyer
     */
    @Override
    public ArrayList<Buyer> findAll() {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT `User_Id` FROM `myshopmf`.`Buyer`;");
        ArrayList<Buyer> buyers = new ArrayList<>();
        try {
            while (rs.next()) {
                buyer = new Buyer();
                buyer.setId(rs.getInt("User_Id"));
                buyers.add(buyer);
            }
            return buyers;
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
     * per aggiungere un nuovo Buyer nel db
     */
    @Override
    public int add(Buyer b) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO `myshopmf`.`Buyer` (`User_Id`) VALUES ('" + b.getId() + "');");
        conn.close();
        return rowCount;
    }

    /**
     * per modificare un Buyer esistente
     */
    @Override
    public int update(Buyer b, int oldId) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE `myshopmf`.`Buyer` SET `User_Id` = '" + b.getId() + "' WHERE (`User_Id` = '" + oldId + "');");
        conn.close();
        return rowCount;
    }

    /**
     * per eliminare un Buyer in base al suo Id
     */
    @Override
    public int removeById(int id) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM `myshopmf`.`Buyer` WHERE (`User_Id` = '" + id + "');");
        conn.close();
        return rowCount;
    }
}
