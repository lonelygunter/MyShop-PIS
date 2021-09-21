package Test;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import DAO.ListDAO;
import DbInterface.DbConnection;
import DbInterface.DbOperationExecutor;
import DbInterface.IDbConnection;
import DbInterface.IDbOperation;
import DbInterface.ReadOperation;
import Model.Buyer;
import Model.List;



public class ListDAOTest {

    public int getLastListId() {
        IDbConnection conn;
        ResultSet rs;
        List list;
        conn = DbConnection.getInstance();
        String sql = "SELECT `Id` FROM `myshopmf`.`List` ORDER BY `Id` DESC LIMIT 1;";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                list = new List();
                list.setId(rs.getInt("Id"));
                return list.getId();
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

    public List findListFromId(int id) {
        IDbConnection conn;
        ResultSet rs;
        List list;
        conn = DbConnection.getInstance();
        String sql = "SELECT Id FROM myshopmf.List WHERE Id = " + id + "";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);
        
        try {
            while (rs.next()) {
                list = new List();
                list.setId(rs.getInt("Id"));
                return list;            
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
        List list = new List("Mobili per il salotto", new Date(10102020), 400.5F , findBuyerFromId(6), "N" , null);
        ListDAO listDAO = ListDAO.getInstance();
        listDAO.add(list);
    }
    
    @After
    public void tearDown() throws Exception {
        ListDAO listDAO = ListDAO.getInstance();
        listDAO.removeById(getLastListId());
    }
    
    
    @Test
    public void findByIdTest() {
        ListDAO listDAO = ListDAO.getInstance();
        List List = listDAO.findById(getLastListId());
        Assert.assertEquals(getLastListId(), List.getId());
    }   
    
    @Test
    public void update() {
        ListDAO listDAO = ListDAO.getInstance();
        List list = findListFromId(getLastListId());
        listDAO.update(list);
        Assert.assertEquals(getLastListId(), list.getId());
    }
    
    @Test 
    public void findAll(){
        ListDAO listDAO = ListDAO.getInstance();
        ArrayList<List> lists = listDAO.findAll();
        Assert.assertEquals(3, lists.size());
    }

}
