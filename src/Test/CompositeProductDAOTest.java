package Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import DAO.CompositeProductDAO;
import DbInterface.DbConnection;
import DbInterface.DbOperationExecutor;
import DbInterface.IDbConnection;
import DbInterface.IDbOperation;
import DbInterface.ReadOperation;
import Model.CompositeProduct;
import Model.Product;

public class CompositeProductDAOTest {


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
        CompositeProductDAO compositeProductDAO = CompositeProductDAO.getInstance();
        compositeProductDAO.add(new CompositeProduct(151,null), findProductFromId(113));
    }
    
    
    @After
    public void tearDown() throws Exception {
        CompositeProductDAO compositeProductDAO = CompositeProductDAO.getInstance();
        compositeProductDAO.removeById(151);
    }
    
    @Test
    public void findByIdTest() {
        CompositeProductDAO compositeProductDAO = CompositeProductDAO.getInstance();
        CompositeProduct compositeProduct = compositeProductDAO.findById(151);
        Assert.assertEquals(151, compositeProduct.getId());
    }   


    @Test 
    public void findAll(){
        CompositeProductDAO compositeProductDAO = CompositeProductDAO.getInstance();
        ArrayList<CompositeProduct> compositeProducts = compositeProductDAO.findAll();
        Assert.assertEquals(4, compositeProducts.size());
    }
}
