package Test;

import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import DAO.IWholesalerDAO;
import DAO.WholesalerDAO;
import DbInterface.DbConnection;
import DbInterface.DbOperationExecutor;
import DbInterface.IDbConnection;
import DbInterface.IDbOperation;
import DbInterface.ReadOperation;
import Model.Wholesaler;



public class WholesalerDAOTest {
    
    public int getLastWholesalerId() {
        IDbConnection conn;
        ResultSet rs;
        Wholesaler Wholesaler;
        conn = DbConnection.getInstance();
        String sql = "SELECT `Id` FROM `myshopmf`.`Wholesaler` ORDER BY `Id` DESC LIMIT 1;";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                Wholesaler = new Wholesaler();
                Wholesaler.setId(rs.getInt("Id"));
                return Wholesaler.getId();
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
        IWholesalerDAO wholesalerDAO = WholesalerDAO.getInstance();
        wholesalerDAO.add(new Wholesaler("IKEA", "ikea@gmail.com", "3921092348" , "www.ikea.com", "Milano", "Italia", null));
    }
    
    @After
    public void tearDown() throws Exception {
        WholesalerDAO wholesalerDAO = WholesalerDAO.getInstance();
        wholesalerDAO.removeById(getLastWholesalerId());
    }
    
    @Test
    public void findByIdTest() {
        WholesalerDAO wholesalerDAO = WholesalerDAO.getInstance();
        Wholesaler wholesaler = wholesalerDAO.findById(2);
        Assert.assertEquals(2, wholesaler.getId());
    }   

    @Test 
    public void findAll(){
        WholesalerDAO wholesalerDAO = WholesalerDAO.getInstance();
        ArrayList<Wholesaler> wholesalers = wholesalerDAO.findAll();
        Assert.assertEquals(6, wholesalers.size());
    }
}
