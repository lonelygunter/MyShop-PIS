package Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import DAO.StoreDAO;
import DbInterface.DbConnection;
import DbInterface.DbOperationExecutor;
import DbInterface.IDbConnection;
import DbInterface.IDbOperation;
import DbInterface.ReadOperation;
import Model.Manager;
import Model.Store;


public class StoreDAOTest {

    public int getLastStoreId() {
        IDbConnection conn;
        ResultSet rs;
        Store Store;
        conn = DbConnection.getInstance();
        String sql = "SELECT `Id` FROM `myshopmf`.`Store` ORDER BY `Id` DESC LIMIT 1;";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                Store = new Store();
                Store.setId(rs.getInt("Id"));
                return Store.getId();
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
        String sql = "SELECT User_Id FROM myshopmf.manager WHERE User_Id = " + id + "";
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

    @Before
    public void setUp() throws Exception {
        Store store = new Store("3921098319", "Via Delle Lacrime 6", "73010", "Italia", findManagerFromId(3), null, null, null);
        StoreDAO storeDAO = StoreDAO.getInstance();
        storeDAO.add(store);
    }
    
    @After
    public void tearDown() throws Exception {
        StoreDAO storeDAO = StoreDAO.getInstance();
        storeDAO.removeById(getLastStoreId());
    }
    
    
    @Test
    public void findByIdTest() {
        StoreDAO storeDAO = StoreDAO.getInstance();
        Store store = storeDAO.findById(getLastStoreId());
        Assert.assertEquals(getLastStoreId(), store.getId());
    }   
    
    @Test
    public void update() {
        StoreDAO storeDAO = StoreDAO.getInstance();
        Store store = new Store("3921098319", "Via Delle Lacrime 6", "73010", "Italia", findManagerFromId(3), null, null, null);
        storeDAO.update(store);
        //Assert.assertEquals(getLastStoreId(), Store.getId());
    }
    
    @Test 
    public void findAll(){
        StoreDAO storeDAO = StoreDAO.getInstance();
        ArrayList<Store> stores = storeDAO.findAll();
        Assert.assertEquals(5, stores.size());
    }

}
