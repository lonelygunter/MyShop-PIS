package Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import DAO.CategoryDAO;
import DbInterface.DbConnection;
import DbInterface.DbOperationExecutor;
import DbInterface.IDbConnection;
import DbInterface.IDbOperation;
import DbInterface.ReadOperation;
import Model.Category;


public class CategoryDAOTest {

    public int getLastCategoryId() {
        IDbConnection conn;
        ResultSet rs;
        Category category;
        conn = DbConnection.getInstance();
        String sql = "SELECT `Id` FROM `myshopmf`.`category` ORDER BY `Id` DESC LIMIT 1;";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                category = new Category();
                category.setId(rs.getInt("Id"));
                return category.getId();
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

    public Category findCategoryFromId(int id) {
        IDbConnection conn;
        ResultSet rs;
        Category category;
        conn = DbConnection.getInstance();
        String sql = "SELECT Id FROM myshopmf.Category WHERE Id = " + id + "";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);
        
        try {
            while (rs.next()) {
                category = new Category();
                category.setId(rs.getInt("Id"));
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

    @Before
    public void setUp() throws Exception {
        Category category = new Category("tavoli", 38, null);
        CategoryDAO categoryDAO = CategoryDAO.getInstance();
        categoryDAO.add(category);
    }
    
    @After
    public void tearDown() throws Exception {
        CategoryDAO categoryDAO = CategoryDAO.getInstance();
        categoryDAO.removeById(getLastCategoryId());
    }
    
    
    @Test
    public void findByIdTest() {
        CategoryDAO categoryDAO = CategoryDAO.getInstance();
        Category category = categoryDAO.findById(getLastCategoryId());
        Assert.assertEquals(getLastCategoryId(), category.getId());
    }   
    
    @Test
    public void update() {
        CategoryDAO categoryDAO = CategoryDAO.getInstance();
        Category category = findCategoryFromId(1);
        categoryDAO.update(category);
        Assert.assertEquals(1, category.getId());
    }
    
    @Test 
    public void findAll(){
        CategoryDAO categoryDAO = CategoryDAO.getInstance();
        ArrayList<Category> categorys = categoryDAO.findAll();
        Assert.assertEquals(18, categorys.size());
    }

}
