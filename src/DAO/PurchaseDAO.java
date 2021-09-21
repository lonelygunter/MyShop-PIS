package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DbInterface.DbConnection;
import DbInterface.DbOperationExecutor;
import DbInterface.IDbConnection;
import DbInterface.IDbOperation;
import DbInterface.ReadOperation;
import Model.Purchase;

public class PurchaseDAO implements IPurchaseDAO{

    private static PurchaseDAO instance = new PurchaseDAO();
    private Purchase purchase;
    private static IDbConnection conn;
    private static ResultSet rs;

    private PurchaseDAO(){
        purchase = null;
        conn = null;
        rs = null;
    }

    public static PurchaseDAO getInstance() {
        return instance;
    }

    /**
     * per cercare Purchase tramite id
     */
    @Override
    public Purchase findById(int id) {
        conn = DbConnection.getInstance();
        String sql = "SELECT Id, Buyer_User_Id FROM myshopmf.Purchase WHERE myshopmf.Purchase.Id = '" + id + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                purchase = new Purchase();
                purchase.setId(rs.getInt("Id"));
                purchase.setBuyerId(rs.getInt("Buyer_User_Id"));
                return purchase;
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
     * per avere tutti i dati presenti nella tabella Purchase
     */
    @Override
    public ArrayList<Purchase> findAll() {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT Id, Buyer_User_Id FROM myshopmf.Purchase;");
        ArrayList<Purchase> purchases = new ArrayList<>();
        try {
            while (rs.next()) {
                purchase = new Purchase();
                purchase.setId(rs.getInt("Id"));
                purchase.setBuyerId(rs.getInt("Buyer_User_Id"));
                purchases.add(purchase);
            }
            return purchases;
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
     * per aggiungere un nuovo Purchase nel db
     */
    @Override
    public int add(Purchase p) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO `myshopmf`.`Purchase` (`Buyer_User_Id`) VALUES ('" + p.getBuyerId() + "');");
        conn.close();
        return rowCount;
    }

    /**
     * per cercare Purchase tramite lo user id
     */
    @Override
    public Purchase findByUserId(int userId) {
        conn = DbConnection.getInstance();
        String sql = "SELECT `Id`, `Buyer_User_Id` FROM `myshopmf`.`Purchase` AS `p` WHERE `p`.`Buyer_User_Id` = '" + userId + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                purchase = new Purchase();
                purchase.setId(rs.getInt("Id"));
                purchase.setBuyerId(rs.getInt("Buyer_User_Id"));
                return purchase;
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
     * per avere Purchase_has_Item_Id dall'itemd id ed il purchase id
     */
    @Override
    public int findByItemIdAndPurchaseId(int itemId, int purchaseId) {
        conn = DbConnection.getInstance();
        String sql = "SELECT `Purchase_has_Item_Id` FROM `myshopmf`.`Purchase_has_Item` AS `p` WHERE `p`.`Item_Id` = '" + itemId + "' AND `p`.`Purchase_Id` = '" + purchaseId + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                return rs.getInt("Purchase_has_Item_Id");
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
        return 0;
    }

    /**
     * per avere Purchase_has_Item_Id dall'itemd id ed il purchase id
     */
    @Override
    public int getPurchaseIdById(int id) {
        conn = DbConnection.getInstance();
        String sql = "SELECT `Purchase_Id` FROM `myshopmf`.`Purchase_has_Item` AS `p` WHERE (`p`.`Purchase_has_Item_Id` = '" + id +"');";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                return rs.getInt("Purchase_Id");
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
        return 0;
    }

    /**
     * per avere Buyer Id dal Purchase_has_Item_Id
     */
    @Override
    public int getBuyerIdByPurchaseId(int id) {
        conn = DbConnection.getInstance();
        String sql = "SELECT `Buyer_User_Id` FROM `myshopmf`.`Purchase` AS `p` WHERE (`p`.`Id` = '" + id + "');";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                return rs.getInt("Buyer_User_Id");
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
        return 0;
    }
}
