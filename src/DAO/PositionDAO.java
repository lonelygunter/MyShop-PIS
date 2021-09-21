package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DbInterface.DbConnection;
import DbInterface.DbOperationExecutor;
import DbInterface.IDbConnection;
import DbInterface.IDbOperation;
import DbInterface.ReadOperation;
import Model.Position;

public class PositionDAO implements IPositionDAO{

    private static PositionDAO instance = new PositionDAO();
    private Position position;
    private static IDbConnection conn;
    private static ResultSet rs;

    private PositionDAO(){
        position = null;
        conn = null;
        rs = null;
    }

    public static PositionDAO getInstance() {
        return instance;
    }

    /**
     * per cercare Position tramite id
     */
    @Override
    public Position findById(int id) {
        conn = DbConnection.getInstance();
        String sql = "SELECT Id, Lane_Number, Shelf_Number FROM myshopmf.Position WHERE myshopmf.Position.Id = '" + id + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
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

    /**
     * per avere tutti i dati presenti nella tabella Position
     */
    @Override
    public ArrayList<Position> findAll() {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT Id, Lane_Number, Shelf_Number FROM myshopmf.Position;");
        ArrayList<Position> categories = new ArrayList<>();
        try {
            while (rs.next()) {
                position = new Position();
                position.setId(rs.getInt("Id"));
                position.setLane(rs.getInt("Lane_Number"));
                position.setShelf(rs.getInt("Shelf_Number"));
                categories.add(position);
            }
            return categories;
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

    /**
     * per aggiungere un nuovo Position nel db
     */
    @Override
    public int add(Position p) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO `myshopmf`.`Position` (`Lane_Number`, `Shelf_Number`) VALUES ('" + p.getLane() + "', '" + p.getShelf() + "');");
        conn.close();
        return rowCount;
    }

    /**
     * per modificare un Position esistente
     */
    @Override
    public int update(Position p) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE `myshopmf`.`Position` SET `Lane_Number` = '" + p.getLane() + "', `Shelf_Number` = '" + p.getShelf() + "' WHERE (`Id` = '" + p.getId() + "');");
        conn.close();
        return rowCount;
    }

    /**
     * per eliminare un Position in base al suo Id
     */
    @Override
    public int removeById(int id) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM `myshopmf`.`Position` WHERE (`Id` = '" + id +"');");
        conn.close();
        return rowCount;
    }
}