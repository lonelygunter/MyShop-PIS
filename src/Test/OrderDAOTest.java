package Test;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import DAO.OrderDAO;
import DbInterface.DbConnection;
import DbInterface.DbOperationExecutor;
import DbInterface.IDbConnection;
import DbInterface.IDbOperation;
import DbInterface.ReadOperation;
import Model.Item;
import Model.Manager;
import Model.Order;
import Model.User;


public class OrderDAOTest {

    public int getLastOrderId() {
        IDbConnection conn;
        ResultSet rs;
        Order order;
        conn = DbConnection.getInstance();
        String sql = "SELECT `Id` FROM `myshopmf`.`Order` ORDER BY `Id` DESC LIMIT 1;";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                order = new Order();
                order.setId(rs.getInt("Id"));
                return order.getId();
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

    public Manager findManagerFromId(int id) {
        IDbConnection conn;
        ResultSet rs;
        Manager manager;
        conn = DbConnection.getInstance();
        String sql = "SELECT User_Id FROM myshopmf.Manager WHERE User_Id = " + id + "";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);
        
        try {
            while (rs.next()) {
                manager = new Manager();
                manager.setId(rs.getInt("User_Id"));
                return manager;            
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
    @Before
    public void setUp() throws Exception {
        Order order = new Order(new Date(202020), 100F, new User(), new Item());
        OrderDAO orderDAO = OrderDAO.getInstance();
        orderDAO.add(order);
    }
    
    @After
    public void tearDown() throws Exception {
        OrderDAO orderDAO = OrderDAO.getInstance();
        orderDAO.removeById(getLastOrderId());
    }
    
    
    @Test
    public void findByIdTest() {
        OrderDAO orderDAO = OrderDAO.getInstance();
        Order order = orderDAO.findById(getLastOrderId());
        Assert.assertEquals(getLastOrderId(), order.getId());
    }   
    
    @Test
    public void update() {
        OrderDAO orderDAO = OrderDAO.getInstance();
        Order order = findOrderFromId(getLastOrderId());
        orderDAO.update(order);
        Assert.assertEquals(getLastOrderId(), order.getId());
    }
    
    @Test 
    public void findAll(){
        OrderDAO orderDAO = OrderDAO.getInstance();
        ArrayList<Order> orders = orderDAO.findAll();
        Assert.assertEquals(3, orders.size());
    }

}
