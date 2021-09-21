package Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import DAO.ItemDAO;
import DbInterface.DbConnection;
import DbInterface.DbOperationExecutor;
import DbInterface.IDbConnection;
import DbInterface.IDbOperation;
import DbInterface.ReadOperation;
import Model.Category;
import Model.Item;
import Model.Wholesaler;

public class ItemDAOTest {

    public int getLastItemId() {
        IDbConnection conn;
        ResultSet rs;
        Item Item;
        conn = DbConnection.getInstance();
        String sql = "SELECT `Id` FROM `myshopmf`.`Item` ORDER BY `Id` DESC LIMIT 1;";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                Item = new Item();
                Item.setId(rs.getInt("Id"));
                return Item.getId();
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

    public Wholesaler findWholeSalerFromId(int id) {
        IDbConnection conn;
        ResultSet rs;
        Wholesaler wholesaler;
        conn = DbConnection.getInstance();
        String sql = "SELECT Id, Name, Email, Telephone, Website, City, Nation FROM myshopmf.Wholesaler WHERE Id = ('" + id + "');";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);
        
        try {
            while (rs.next()) {
                wholesaler = new Wholesaler();
                wholesaler.setId(rs.getInt("Id"));
                wholesaler.setName(rs.getString("Name"));
                wholesaler.setEmail(rs.getString("Email"));
                wholesaler.setTelephone(rs.getString("Telephone"));
                wholesaler.setWebsite(rs.getString("Website"));
                wholesaler.setCity(rs.getString("City"));
                wholesaler.setNation(rs.getString("Nation"));
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
    
    public Category findCategoryFromId(int id) {
        IDbConnection conn;
        ResultSet rs;
        Category category;
        conn = DbConnection.getInstance();
        String sql = "SELECT Id, Name, Category_father_Id FROM myshopmf.category WHERE Id = ('" + id + "');";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);
        
        try {
            while (rs.next()) {
                category = new Category();
                category.setId(rs.getInt("Id"));
                category.setName(rs.getString("Name"));
                category.setFatherId(rs.getInt("Category_father_Id"));
                return category;            
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
    
    public Item findItemFromId(int id) {
        IDbConnection conn;
        ResultSet rs;
        Item item;
        conn = DbConnection.getInstance();
        String sql = "SELECT Id, Name,Description,Price,Type,Available,Wholesaler_Id,Category_Id FROM myshopmf.Item WHERE Id = ('" + id + "');";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);
        
        try {
            while (rs.next()) {
                item = new Item();
                item.setId(rs.getInt("Id"));
                item.setName(rs.getString("Name"));
                item.setDescription(rs.getString("Description"));
                item.setPrice(rs.getFloat("Price"));
                item.setType(rs.getString("Type"));
                item.setAvailable(rs.getInt("Available"));
                item.setWholesalerId(rs.getInt("Wholesaler_Id"));
                item.setCategoryId(rs.getInt("Category_Id"));
                return item;            
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
        Item item = new Item("Sedia", "", 30.54F, "P", 0, findWholeSalerFromId(5), findCategoryFromId(22));
        ItemDAO itemDAO = ItemDAO.getInstance();
        itemDAO.add(item);
    }
    
    @After
    public void tearDown() throws Exception {
        ItemDAO itemDAO = ItemDAO.getInstance();
        itemDAO.removeById(getLastItemId());
    }
    
    
    @Test
    public void findByIdTest() {
        ItemDAO itemDAO = ItemDAO.getInstance();
        Item item = itemDAO.findById(getLastItemId());
        Assert.assertEquals(getLastItemId(), item.getId());
    }   
    
    @Test
    public void update() {
        ItemDAO itemDAO = ItemDAO.getInstance();
        Item item = findItemFromId(getLastItemId());
        itemDAO.update(item);
        Assert.assertEquals(getLastItemId(), item.getId());
    }
    
    @Test 
    public void findAll(){
        ItemDAO itemDAO = ItemDAO.getInstance();
        ArrayList<Item> items = itemDAO.findAll();
        Assert.assertEquals(42, items.size());
    }

}
