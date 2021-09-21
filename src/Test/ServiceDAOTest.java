package Test;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import DAO.ServiceDAO;
import DbInterface.DbConnection;
import DbInterface.DbOperationExecutor;
import DbInterface.IDbConnection;
import DbInterface.IDbOperation;
import DbInterface.ReadOperation;
import DAO.IServiceDAO;
import Model.Service;
import Model.Item;



public class ServiceDAOTest {

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

    @Before
    public void setUp() throws Exception {
        IServiceDAO serviceDAO = ServiceDAO.getInstance();
        serviceDAO.add(findItemFromId(112));
    }
    
    @After
    public void tearDown() throws Exception {
        ServiceDAO serviceDAO = ServiceDAO.getInstance();
        serviceDAO.removeById(112);
    }
    
    @Test
    public void findByIdTest() {
        ServiceDAO serviceDAO = ServiceDAO.getInstance();
        Service service = serviceDAO.findById(112);
        Assert.assertEquals(112, service.getId());
    }   

    @Test 
    public void findAll(){
        ServiceDAO serviceDAO = ServiceDAO.getInstance();
        ArrayList<Service> services = serviceDAO.findAll();
        Assert.assertEquals(3, services.size());
    }
}
