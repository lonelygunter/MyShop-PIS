package Test;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import DbInterface.DbConnection;
import DbInterface.DbOperationExecutor;
import DbInterface.IDbConnection;
import DbInterface.IDbOperation;
import DbInterface.ReadOperation;
import DAO.AdministratorDAO;
import DAO.IAdministratorDAO;
import Model.Administrator;
import Model.User;



public class AdministratorDAOTest {
    
    public User findUserFromId(int id) {
        IDbConnection conn;
        ResultSet rs;
        User User;
        conn = DbConnection.getInstance();
        String sql = "SELECT Id FROM myshopmf.User WHERE Id = " + id + "";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);
        
        try {
            while (rs.next()) {
                User = new User();
                User.setId(rs.getInt("Id"));
                return User;            
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
        IAdministratorDAO administratorDAO = AdministratorDAO.getInstance();
        administratorDAO.add(findUserFromId(2));
    }
    
    @After
    public void tearDown() throws Exception {
        AdministratorDAO administratorDAO = AdministratorDAO.getInstance();
        administratorDAO.removeById(findUserFromId(2).getId());
    }
    
    @Test
    public void findByIdTest() {
        AdministratorDAO administratorDAO = AdministratorDAO.getInstance();
        Administrator administrator = administratorDAO.findById(findUserFromId(2).getId());
        Assert.assertEquals(findUserFromId(2).getId(), administrator.getId());
    }   

    @Test
    public void update() {
        AdministratorDAO administratorDAO = AdministratorDAO.getInstance();
        User administrator = findUserFromId(2);
        administratorDAO.update(administrator, findUserFromId(2).getId());
        Assert.assertEquals(findUserFromId(2).getId(), administrator.getId());
    }

    @Test 
    public void findAll(){
        AdministratorDAO administratorDAO = AdministratorDAO.getInstance();
        ArrayList<Administrator> administrators = administratorDAO.findAll();
        Assert.assertEquals(2, administrators.size());
    }
}
