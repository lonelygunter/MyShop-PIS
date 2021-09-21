package Test;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import DAO.IProducerDAO;
import DAO.ProducerDAO;
import DbInterface.DbConnection;
import DbInterface.DbOperationExecutor;
import DbInterface.IDbConnection;
import DbInterface.IDbOperation;
import DbInterface.ReadOperation;
import Model.Producer;
import Model.Wholesaler;



public class ProducerDAOTest {

    public int getLastProducerId() {
        IDbConnection conn;
        ResultSet rs;
        Producer Producer;
        conn = DbConnection.getInstance();
        String sql = "SELECT `Id` FROM `myshopmf`.`Producer` ORDER BY `Id` DESC LIMIT 1;";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                Producer = new Producer();
                Producer.setId(rs.getInt("Id"));
                return Producer.getId();
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

    public Wholesaler findWholesalerFromId(int id) {
        IDbConnection conn;
        ResultSet rs;
        Wholesaler wholesaler;
        conn = DbConnection.getInstance();
        String sql = "SELECT Id FROM myshopmf.Wholesaler WHERE Id = " + id + "";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);
        
        try {
            while (rs.next()) {
                wholesaler = new Wholesaler();
                wholesaler.setId(rs.getInt("Id"));
                return wholesaler;            
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
        IProducerDAO producerDAO = ProducerDAO.getInstance();
        producerDAO.add((Producer) findWholesalerFromId(4));
    }
    
    @After
    public void tearDown() throws Exception {
        ProducerDAO producerDAO = ProducerDAO.getInstance();
        producerDAO.removeById(4);
    }
    
    @Test
    public void findByIdTest() {
        ProducerDAO producerDAO = ProducerDAO.getInstance();
        Producer producer = producerDAO.findById(4);
        Assert.assertEquals(4, producer.getId());
    }   

    @Test
    public void update() {
        ProducerDAO producerDAO = ProducerDAO.getInstance();
        Wholesaler wholesaler = findWholesalerFromId(4);
        producerDAO.update((Producer) wholesaler, 4);
        Assert.assertEquals(4, wholesaler.getId());
    }

    @Test 
    public void findAll(){
        ProducerDAO producerDAO = ProducerDAO.getInstance();
        ArrayList<Producer> producers = producerDAO.findAll();
        Assert.assertEquals(4, producers.size());
    }
}
