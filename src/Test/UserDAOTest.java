package Test;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import DAO.IUserDAO;
import DAO.UserDAO;
import DbInterface.DbConnection;
import DbInterface.DbOperationExecutor;
import DbInterface.IDbConnection;
import DbInterface.IDbOperation;
import DbInterface.ReadOperation;
import Model.User;



public class UserDAOTest {
    
    public int getLastUserId() {
        IDbConnection conn;
        ResultSet rs;
        User User;
        conn = DbConnection.getInstance();
        String sql = "SELECT `Id` FROM `myshopmf`.`User` ORDER BY `Id` DESC LIMIT 1;";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                User = new User();
                User.setId(rs.getInt("Id"));
                return User.getId();
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
        IUserDAO SserDAO = UserDAO.getInstance();
        SserDAO.add(new User("filip", "cqewnads", "filippo", "carano", new Date(22092000),"filippo.carano@gmail.com", "12983712", "73100","via carano gay", "A", 0));
    }
    
    @After
    public void tearDown() throws Exception {
        UserDAO userDAO = UserDAO.getInstance();
        userDAO.removeById(getLastUserId());
    }
    
    @Test
    public void findByIdTest() {
        UserDAO userDAO = UserDAO.getInstance();
        User user = userDAO.findById(2);
        Assert.assertEquals(2, user.getId());
    }   

    @Test 
    public void findAll(){
        UserDAO userDAO = UserDAO.getInstance();
        ArrayList<User> users = userDAO.findAll();
        Assert.assertEquals(8, users.size());
    }
}
