package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DbInterface.DbConnection;
import DbInterface.DbOperationExecutor;
import DbInterface.IDbConnection;
import DbInterface.IDbOperation;
import DbInterface.ReadOperation;
import Model.Stockist;
import Model.Wholesaler;

public class StockistDAO implements IStockistDAO{

    private static StockistDAO instance = new StockistDAO();
    private Stockist stockist;
    private static IDbConnection conn;
    private static ResultSet rs;

    
    private StockistDAO(){
        stockist = null;
        conn = null;
        rs = null;
    }

    public static StockistDAO getInstance() {
        return instance;
    }

    /**
     * per cercare Stockist tramite id
     */
    @Override
    public Stockist findById(int id) {
        conn = DbConnection.getInstance();
        String sql = "SELECT Wholesaler_Id FROM myshopmf.Stockist WHERE myshopmf.Stockist.Item_Id = '" + id + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                stockist = new Stockist();
                stockist.setId(rs.getInt("Wholesaler_Id"));
                return stockist;
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
     * per avere tutti i dati presenti nella tabella Stockist
     */
    @Override
    public ArrayList<Stockist> findAll() {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT Wholesaler_Id FROM myshopmf.Stockist;");
        ArrayList<Stockist> stockists = new ArrayList<>();
        try {
            while (rs.next()) {
                stockist = new Stockist();
                stockist.setId(rs.getInt("Wholesaler_Id"));
                stockists.add(stockist);
            }
            return stockists;
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
     * per aggiungere un nuovo Stockist nel db
     */
    @Override
    public int add(Wholesaler wholesaler) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO `myshopmf`.`Stockist` (`Wholesaler_Id`) VALUES ('" + wholesaler.getId() + "');");
        conn.close();
        return rowCount;
    }

    /**
     * per modificare un Stockist esistente
     */
    @Override
    public int update(Wholesaler wholesaler, int oldId) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE `myshopmf`.`Stockist` SET `Wholesaler_Id` = '" + wholesaler.getId() + "' WHERE (`Wholesaler_Id` = '" + oldId + "');");
        conn.close();
        return rowCount;
    }

    /**
     * per eliminare un Stockist in base al suo Id
     */
    @Override
    public int removeById(int id) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM `myshopmf`.`Stockist` WHERE (`Wholesaler_Id` = '" + id + "');");
        conn.close();
        return rowCount;
    }
}
