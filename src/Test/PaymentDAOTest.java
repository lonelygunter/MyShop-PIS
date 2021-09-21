package Test;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import DAO.PaymentDAO;
import DbInterface.DbConnection;
import DbInterface.DbOperationExecutor;
import DbInterface.IDbConnection;
import DbInterface.IDbOperation;
import DbInterface.ReadOperation;
import DAO.IPaymentDAO;
import Model.Order;
import Model.Payment;



public class PaymentDAOTest {

    public Order findOrderFromId(int id) {
        IDbConnection conn;
        ResultSet rs;
        Order order;
        conn = DbConnection.getInstance();
        String sql = "SELECT Id FROM myshopmf.Order WHERE Id = " + id + "";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);
        
        try {
            while (rs.next()) {
                order = new Order();
                order.setId(rs.getInt("Id"));
                return order;            
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

    public Payment findPaymentFromId(int id) {
        IDbConnection conn;
        ResultSet rs;
        Payment payment;
        conn = DbConnection.getInstance();
        String sql = "SELECT Order_id FROM myshopmf.Payment WHERE Order_Id = " + id + "";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);
        
        try {
            while (rs.next()) {
                payment = new Payment();
                payment.setId(rs.getInt("Order_id"));
                return payment;            
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
    
    public int getLastPaymentId() {
        IDbConnection conn;
        ResultSet rs;
        Payment payment;
        conn = DbConnection.getInstance();
        String sql = "SELECT `Order_Id` FROM `myshopmf`.`Payment` ORDER BY `Order_Id` DESC LIMIT 1;";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                payment = new Payment();
                payment.setId(rs.getInt("Order_Id"));
                return payment.getId();
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
    @Before
    public void setUp() throws Exception {
        IPaymentDAO paymentDAO = PaymentDAO.getInstance();
        paymentDAO.add(new Payment(534203910374L, new Date(10102020),723,findOrderFromId(10)));
    }

    @Test 
    public void findAll(){
        PaymentDAO paymentDAO = PaymentDAO.getInstance();
        ArrayList<Payment> payments = paymentDAO.findAll();
        Assert.assertEquals(3, payments.size());
    }
}
