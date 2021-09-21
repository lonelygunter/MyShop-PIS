package Test;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import DAO.FeedbackDAO;
import DbInterface.DbConnection;
import DbInterface.DbOperationExecutor;
import DbInterface.IDbConnection;
import DbInterface.IDbOperation;
import DbInterface.ReadOperation;
import Model.Feedback;
import Model.Item;
import Model.Purchase;


public class FeedbackDAOTest {

    public int getLastFeedbackId() {
        IDbConnection conn;
        ResultSet rs;
        Feedback feedback;
        conn = DbConnection.getInstance();
        String sql = "SELECT `Id` FROM `myshopmf`.`feedback` ORDER BY `Id` DESC LIMIT 1;";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                feedback = new Feedback();
                feedback.setId(rs.getInt("Id"));
                return feedback.getId();
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

    public Purchase findPurchaseFromId(int id) {
        IDbConnection conn;
        ResultSet rs;
        Purchase purchase;
        conn = DbConnection.getInstance();
        String sql = "SELECT Id, Buyer_User_Id FROM myshopmf.Purchase WHERE Id = ('" + id + "');";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);
        
        try {
            while (rs.next()) {
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

    @Before
    public void setUp() throws Exception {
        Feedback feedback = new Feedback("mi piace molto", 4, new Date(10102021), new Item(),  3, 0);
        FeedbackDAO feedbackDAO = FeedbackDAO.getInstance();
        feedbackDAO.write(feedback);
    }
    
    @After
    public void tearDown() throws Exception {
        FeedbackDAO feedbackDAO = FeedbackDAO.getInstance();
        feedbackDAO.answer(new Feedback("grazie", 4, new Date(10102021), new Item(), 3, 0), false);
    }
    
    
    @Test
    public void findByIdTest() {
        FeedbackDAO feedbackDAO = FeedbackDAO.getInstance();
        Feedback feedback = feedbackDAO.findById(getLastFeedbackId());
        Assert.assertEquals(getLastFeedbackId(), feedback.getId());
    }   
    
    
    @Test 
    public void findAll(){
        FeedbackDAO feedbackDAO = FeedbackDAO.getInstance();
        ArrayList<Feedback> feedbacks = feedbackDAO.findAll();
        Assert.assertEquals(2, feedbacks.size());
    }

}
