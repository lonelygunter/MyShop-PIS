package Test;


import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import DAO.StockistDAO;
import DbInterface.DbConnection;
import DbInterface.DbOperationExecutor;
import DbInterface.IDbConnection;
import DbInterface.IDbOperation;
import DbInterface.ReadOperation;
import DAO.IStockistDAO;
import Model.Stockist;
import Model.Wholesaler;



public class StockistDAOTest {

    public int getLastStockistId() {
        IDbConnection conn;
        ResultSet rs;
        Stockist Stockist;
        conn = DbConnection.getInstance();
        String sql = "SELECT `Wholesaler_Id` FROM `myshopmf`.`Stockist` ORDER BY `Wholesaler_Id` DESC LIMIT 1;";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                Stockist = new Stockist();
                Stockist.setId(rs.getInt("Wholesaler_Id"));
                return Stockist.getId();
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
    
    @Before
    public void setUp() throws Exception {
        IStockistDAO stockistDAO = StockistDAO.getInstance();
        Wholesaler wholesaler = findWholesalerFromId(2);
        stockistDAO.add(wholesaler);
    }
   
    @After
    public void tearDown() throws Exception {
        StockistDAO stockistDAO = StockistDAO.getInstance();
        stockistDAO.removeById(findWholesalerFromId(2).getId());
    }
    
    @Test
    public void findByIdTest() {
        StockistDAO stockistDAO = StockistDAO.getInstance();
        Stockist stockist = stockistDAO.findById(findWholesalerFromId(2).getId());
        Assert.assertEquals(findWholesalerFromId(2).getId(), stockist.getId());
    }   

    @Test 
    public void findAll(){
        StockistDAO stockistDAO = StockistDAO.getInstance();
        ArrayList<Stockist> stockists = stockistDAO.findAll();
        Assert.assertEquals(3, stockists.size());
    }
}
