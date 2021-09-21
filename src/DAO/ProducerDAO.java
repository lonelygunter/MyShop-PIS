package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DbInterface.DbConnection;
import DbInterface.DbOperationExecutor;
import DbInterface.IDbConnection;
import DbInterface.IDbOperation;
import DbInterface.ReadOperation;
import Model.Producer;

public class ProducerDAO implements IProducerDAO{

    private static ProducerDAO instance = new ProducerDAO();
    private Producer producer;
    private static IDbConnection conn;
    private static ResultSet rs;


    private ProducerDAO(){
        producer = null;
        conn = null;
        rs = null;
    }

    public static ProducerDAO getInstance() {
        return instance;
    }

    
    /**
     * per cercare Producer tramite id
     */
    @Override
    public Producer findById(int id) {
        conn = DbConnection.getInstance();
        String sql = "SELECT Wholesaler_Id FROM myshopmf.Producer WHERE myshopmf.Producer.Wholesaler_Id = '" + id + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                producer = new Producer();
                producer.setId(rs.getInt("Wholesaler_Id"));
                return producer;
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
     * per avere tutti i dati presenti nella tabella Producer
     */
    @Override
    public ArrayList<Producer> findAll() {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT Wholesaler_Id FROM myshopmf.Producer;");
        ArrayList<Producer> producers = new ArrayList<>();
        try {
            while (rs.next()) {
                producer = new Producer();
                producer.setId(rs.getInt("Wholesaler_Id"));
                producers.add(producer);
            }
            return producers;
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
     * per aggiungere un nuovo Producer nel db
     */
    @Override
    public int add(Producer p) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO `myshopmf`.`Producer` (`Wholesaler_Id`) VALUES ('" + p.getId() + "');");
        conn.close();
        return rowCount;
    }

    /**
     * per modificare un Producer esistente
     */
    @Override
    public int update(Producer p, int oldId) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE `myshopmf`.`Producer` SET `Wholesaler_Id` = '" + p.getId() + "' WHERE (`Wholesaler_Id` = '" + oldId +"');");
        conn.close();
        return rowCount;
    }

    /**
     * per eliminare un Producer in base al suo Id
     */
    @Override
    public int removeById(int id) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM `myshopmf`.`Producer` WHERE (`Wholesaler_Id` = '" + id + "');");
        conn.close();
        return rowCount;
    }
}
