package Test;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import DAO.IManagerDAO;
import DAO.ManagerDAO;
import DAO.UserDAO;
import DbInterface.DbConnection;
import DbInterface.DbOperationExecutor;
import DbInterface.IDbConnection;
import DbInterface.IDbOperation;
import DbInterface.ReadOperation;
import Model.Manager;
import Model.User;



public class ManagerDAOTest {
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
        IManagerDAO managerDAO = ManagerDAO.getInstance();
        managerDAO.add(findUserFromId(1));
    }
    
    @After
    public void tearDown() throws Exception {
        ManagerDAO managerDAO = ManagerDAO.getInstance();
        managerDAO.removeById(findUserFromId(1).getId());
    }
    
    @Test
    public void findByIdTest() {
        UserDAO managerDAO = UserDAO.getInstance();
        User manager = managerDAO.findById(findUserFromId(1).getId());
        Assert.assertEquals(findUserFromId(1).getId(), manager.getId());
    }   

    @Test
    public void update() {
        ManagerDAO managerDAO = ManagerDAO.getInstance();
        User manager = findUserFromId(1);
        managerDAO.update(manager);
        Assert.assertEquals(findUserFromId(1).getId(), manager.getId());
    }

    @Test 
    public void findAll(){
        ManagerDAO managerDAO = ManagerDAO.getInstance();
        ArrayList<Manager> managers = managerDAO.findAll();
        Assert.assertEquals(5, managers.size());
    }
}
