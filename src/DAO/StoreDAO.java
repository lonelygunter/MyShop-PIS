package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DbInterface.DbConnection;
import DbInterface.DbOperationExecutor;
import DbInterface.IDbConnection;
import DbInterface.IDbOperation;
import DbInterface.ReadOperation;
import Model.Store;

public class StoreDAO implements IStoreDAO {

    private static StoreDAO instance = new StoreDAO();
    private Store store;
    private static IDbConnection conn;
    private static ResultSet rs;

    public static StoreDAO getInstance() {
        return instance;
    }

    /**
     * per cercare Store tramite id
     */
    @Override
    public Store findById(int id) {
        conn = DbConnection.getInstance();
        String sql = "SELECT Id, Telephone, Street, Cap, Nation, Manager_Id FROM myshopmf.Store WHERE myshopmf.Store.Id = '" + id + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                store = new Store();
                store.setId(rs.getInt("Id"));
                store.setTelephone(rs.getString("Telephone"));
                store.setStreet(rs.getString("Street"));
                store.setCap(rs.getString("Cap"));
                store.setNation(rs.getString("Nation"));
                store.setUserId(rs.getInt("Manager_Id"));
                return store;
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
     * per avere tutti i dati presenti nella tabella Store
     */
    @Override
    public ArrayList<Store> findAll() {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT Id, Telephone, Street, Cap, Nation, Manager_Id FROM myshopmf.Store;");
        ArrayList<Store> stores = new ArrayList<>();
        try {
            while (rs.next()) {
                store = new Store();
                store.setId(rs.getInt("Id"));
                store.setTelephone(rs.getString("Telephone"));
                store.setStreet(rs.getString("Street"));
                store.setCap(rs.getString("Cap"));
                store.setNation(rs.getString("Nation"));
                store.setUserId(rs.getInt("Manager_Id"));
                stores.add(store);
            }
            return stores;
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
     * per aggiungere un nuovo Store nel db
     */
    @Override
    public int add(Store s) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO `myshopmf`.`Store` (`Telephone`, `Street`, `Cap`, `Nation`, `Manager_Id`) VALUES ('" + s.getTelephone() + "', '" + s.getStreet() + "', '" + s.getCap() + "', '" + s.getNation() + "', '" + s.getUserId() + "');");
        conn.close();
        return rowCount;
    }

    /**
     * per modificare un Store esistente
     */
    @Override
    public int update(Store s) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE `myshopmf`.`Store` SET `Telephone` = '" + s.getTelephone() + "', `Street` = '" + s.getStreet() + "', `Cap` = '" + s.getCap() + "', `Nation` = '" + s.getNation() + "', `Manager_Id` = '" + s.getUserId() + "' WHERE (`Id` = '" + s.getId() + "');");
        conn.close();
        return rowCount;
    }

    /**
     * per eliminare un Store in base al suo Id
     */
    @Override
    public int removeById(int id) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM `myshopmf`.`Store` WHERE (`Id` = '" + id + "');");
        conn.close();
        return rowCount;
    }

    /**
     * per prendere tutti gli store in un ordine dato
     */
    @Override
    public ArrayList<Store> findAllOrderBy(String sql) {
        if (sql.equals("")) {
            sql = "SELECT Id, Telephone, Street, Cap, Nation, Manager_Id FROM myshopmf.Store;";
        }

        conn = DbConnection.getInstance();
        rs = conn.executeQuery(sql);
        ArrayList<Store> stores = new ArrayList<Store>();
        try {
            while (rs.next()) {
                store = new Store();
                store.setId(rs.getInt("Id"));
                store.setTelephone(rs.getString("Telephone"));
                store.setStreet(rs.getString("Street"));
                store.setCap(rs.getString("Cap"));
                store.setNation(rs.getString("Nation"));
                store.setUserId(rs.getInt("Manager_Id"));

                stores.add(store);
            }
            return stores;
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
     * per verificare che una lista esiste nel db
     */
    @Override
    public boolean storeExists(Store store) {
        conn = DbConnection.getInstance();
        boolean storeExists = false;

        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT count(*) AS `C` FROM `myshopmf`.`Store` WHERE (`Store`.`Id` = '" + store.getId() + "');";
        ReadOperation op = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(op);

        try {
            rs.next();
            if(rs.getRow()==1 && rs.getInt("C")==1) storeExists = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }  finally {
            conn.close();
        }

        return storeExists;
    }

    /**
     * per cercare Store tramite id
     */
    @Override
    public Store findByNation(String storeNation) {
        conn = DbConnection.getInstance();
        String sql = "SELECT Id, Telephone, Street, Cap, Nation, Manager_Id FROM myshopmf.Store WHERE myshopmf.Store.Nation = '" + storeNation + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                store = new Store();
                store.setId(rs.getInt("Id"));
                store.setTelephone(rs.getString("Telephone"));
                store.setStreet(rs.getString("Street"));
                store.setCap(rs.getString("Cap"));
                store.setNation(rs.getString("Nation"));
                store.setUserId(rs.getInt("Manager_Id"));
                return store;
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
}