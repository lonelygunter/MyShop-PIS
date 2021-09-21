package Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import DAO.PositionDAO;
import DbInterface.DbConnection;
import DbInterface.DbOperationExecutor;
import DbInterface.IDbConnection;
import DbInterface.IDbOperation;
import DbInterface.ReadOperation;
import Model.Position;


public class PositionDAOTest {

    public int getLastPositionId() {
        IDbConnection conn;
        ResultSet rs;
        Position Position;
        conn = DbConnection.getInstance();
        String sql = "SELECT `Id` FROM `myshopmf`.`Position` ORDER BY `Id` DESC LIMIT 1;";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                Position = new Position();
                Position.setId(rs.getInt("Id"));
                return Position.getId();
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

    public Position findPositionFromId(int id) {
        IDbConnection conn;
        ResultSet rs;
        Position position;
        conn = DbConnection.getInstance();
        String sql = "SELECT Id FROM myshopmf.Position WHERE Id = " + id + "";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);
        
        try {
            while (rs.next()) {
                position = new Position();
                position.setId(rs.getInt("Id"));
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
    
    @Before
    public void setUp() throws Exception {
        Position position = new Position(5,5);
        PositionDAO positionDAO = PositionDAO.getInstance();
        positionDAO.add(position);
    }
    
    @After
    public void tearDown() throws Exception {
        PositionDAO positionDAO = PositionDAO.getInstance();
        positionDAO.removeById(getLastPositionId());
    }
    
    @Test
    public void findByIdTest() {
        PositionDAO positionDAO = PositionDAO.getInstance();
        Position position = positionDAO.findById(getLastPositionId());
        Assert.assertEquals(getLastPositionId(), position.getId());
    }   
    
    @Test
    public void update() {
        PositionDAO positionDAO = PositionDAO.getInstance();
        Position position = findPositionFromId(getLastPositionId());
        positionDAO.update(position);
        Assert.assertEquals(getLastPositionId(), position.getId());
    }
    
    @Test 
    public void findAll(){
        PositionDAO positionDAO = PositionDAO.getInstance();
        ArrayList<Position> positions = positionDAO.findAll();
        Assert.assertEquals(40, positions.size());
    }

}
