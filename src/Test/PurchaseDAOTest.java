package Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import DAO.PurchaseDAO;
import DbInterface.DbConnection;
import DbInterface.DbOperationExecutor;
import DbInterface.IDbConnection;
import DbInterface.IDbOperation;
import DbInterface.ReadOperation;
import Model.Buyer;
import Model.Purchase;


public class PurchaseDAOTest {

    public int getLastPurchaseId() {
        IDbConnection conn;
        ResultSet rs;
        Purchase Purchase;
        conn = DbConnection.getInstance();
        String sql = "SELECT `Id` FROM `myshopmf`.`Purchase` ORDER BY `Id` DESC LIMIT 1;";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                Purchase = new Purchase();
                Purchase.setId(rs.getInt("Id"));
                return Purchase.getId();
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

    public Buyer findBuyerFromId(int id) {
        IDbConnection conn;
        ResultSet rs;
        Buyer buyer;
        conn = DbConnection.getInstance();
        String sql = "SELECT User_Id FROM myshopmf.Buyer WHERE User_Id = " + id + "";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);
        
        try {
            while (rs.next()) {
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
     
    @Before
    public void setUp() throws Exception {
        Purchase purchase = new Purchase(findBuyerFromId(6));
        PurchaseDAO purchaseDAO = PurchaseDAO.getInstance();
        purchaseDAO.add(purchase);
    }
    
    
    @Test
    public void findByIdTest() {
        PurchaseDAO purchaseDAO = PurchaseDAO.getInstance();
        Purchase purchase = purchaseDAO.findById(getLastPurchaseId());
        Assert.assertEquals(getLastPurchaseId(), purchase.getId());
    }   
    
    
    @Test 
    public void findAll(){
        PurchaseDAO purchaseDAO = PurchaseDAO.getInstance();
        ArrayList<Purchase> purchases = purchaseDAO.findAll();
        Assert.assertEquals(6, purchases.size());
    }
}
