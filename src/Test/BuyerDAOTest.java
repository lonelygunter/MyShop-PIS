package Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import DAO.BuyerDAO;
import DbInterface.DbConnection;
import DbInterface.DbOperationExecutor;
import DbInterface.IDbConnection;
import DbInterface.IDbOperation;
import DbInterface.ReadOperation;
import Model.Buyer;
import Model.User;


public class BuyerDAOTest {
    public User findUserFromId(int id) {
        IDbConnection conn;
        ResultSet rs;
        User User;
        conn = DbConnection.getInstance();
        String sql = "SELECT Id FROM myshopmf.User WHERE Id = " + id + "";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);
        
        try {
            while (rs.next()) {
                User = new User();
                User.setId(rs.getInt("Id"));
                return User;            
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
        BuyerDAO buyerDAO = BuyerDAO.getInstance();
        buyerDAO.add((Buyer) findUserFromId(1));
    }
    
    
    @After
    public void tearDown() throws Exception {
        BuyerDAO buyerDAO = BuyerDAO.getInstance();
        buyerDAO.removeById(findUserFromId(1).getId());
    }
    
    
    @Test
    public void findByIdTest() {
        BuyerDAO buyerDAO = BuyerDAO.getInstance();
        User user = buyerDAO.findById(1);
        Assert.assertEquals(1, user.getId());
    }   
    
    @Test
    public void update() {
        BuyerDAO buyerDAO = BuyerDAO.getInstance();
        User buyer = findUserFromId(1);
        buyerDAO.update((Buyer) buyer, 2);
        Assert.assertEquals(1, buyer.getId());
    }

    @Test 
    public void findAll(){
        BuyerDAO buyerDAO = BuyerDAO.getInstance();
        ArrayList<Buyer> buyers = buyerDAO.findAll();
        Assert.assertEquals(3, buyers.size());
    }
}
