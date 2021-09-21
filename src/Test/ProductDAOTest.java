package Test;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import DAO.ProductDAO;
import DbInterface.DbConnection;
import DbInterface.DbOperationExecutor;
import DbInterface.IDbConnection;
import DbInterface.IDbOperation;
import DbInterface.ReadOperation;
import DAO.IProductDAO;
import Model.Category;
import Model.Item;
import Model.Position;
import Model.Product;



public class ProductDAOTest {

    public int getLastProductId() {
        IDbConnection conn;
        ResultSet rs;
        Category category;
        conn = DbConnection.getInstance();
        String sql = "SELECT `Item_Id` FROM `myshopmf`.`product` ORDER BY `Item_Id` DESC LIMIT 1;";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                category = new Category();
                category.setId(rs.getInt("Item_Id"));
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
    
    public Item findItemFromId(int id) {
        IDbConnection conn;
        ResultSet rs;
        Item item;
        conn = DbConnection.getInstance();
        String sql = "SELECT Id, Name, Description, Price, Type, Wholesaler_Id, Category_Id FROM myshopmf.Item WHERE Id = ('" + id + "');";
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
    
    public Position findPositionFromId(int id) {
        IDbConnection conn;
        ResultSet rs;
        Position position;
        conn = DbConnection.getInstance();
        String sql = "SELECT Id, Lane_Number, Shelf_Number FROM myshopmf.Position WHERE Id = ('" + id + "');";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);
        
        try {
            while (rs.next()) {
                position = new Position();
                position.setId(rs.getInt("Id"));
                position.setLane(rs.getInt("Lane_Number"));
                position.setShelf(rs.getInt("Shelf_Number"));
                return position;            
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
    
    public Product findProductFromId(int id) {
        IDbConnection conn;
        ResultSet rs;
        Product product;
        conn = DbConnection.getInstance();
        String sql = "SELECT Item_Id, Quantity, Position_Id FROM myshopmf.Product WHERE Item_Id = ('" + id + "');";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);
        
        try {
            while (rs.next()) {
                product = new Product();
                product.setId(rs.getInt("Item_Id"));
                product.setQuantity(rs.getInt("Quantity"));
                product.setPositionId(rs.getInt("Position_Id"));
                return product;            
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
        IProductDAO productDAO = ProductDAO.getInstance();
        productDAO.add(findItemFromId(152),20,findPositionFromId(1));
    }
    
    @After
    public void tearDown() throws Exception {
        ProductDAO productDAO = ProductDAO.getInstance();
        productDAO.removeById(findItemFromId(152).getId());
    }
    
    @Test
    public void findByIdTest() {
        ProductDAO productDAO = ProductDAO.getInstance();
        Product product = productDAO.findById(findItemFromId(152).getId());
        Assert.assertEquals(findItemFromId(152).getId(), product.getId());
    }   

    @Test
    public void update() {
        ProductDAO productDAO = ProductDAO.getInstance();
        productDAO.update(findItemFromId(152), 20, findPositionFromId(1));
        Assert.assertEquals(getLastProductId(), 152);
    }

    @Test
    public void reservation() {
        ProductDAO productDAO = ProductDAO.getInstance();
        productDAO.reservation(findProductFromId(152), findItemFromId(153));
    }
    
}
