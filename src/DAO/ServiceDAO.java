package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DbInterface.DbConnection;
import DbInterface.DbOperationExecutor;
import DbInterface.IDbConnection;
import DbInterface.IDbOperation;
import DbInterface.ReadOperation;
import Model.Item;
import Model.Service;

public class ServiceDAO implements IServiceDAO{

    private static ServiceDAO instance = new ServiceDAO();
    private Service service;
    private static IDbConnection conn;
    private static ResultSet rs;

    private ServiceDAO(){
        service = null;
        conn = null;
        rs = null;
    }

    /**
     * per cercare Utenti tramite il lodo id
     */
    public static ServiceDAO getInstance() {
        return instance;
    }

    @Override
    public Service findById(int id) {
        conn = DbConnection.getInstance();
        String sql = "SELECT Item_Id FROM myshopmf.Service WHERE myshopmf.Service.Item_Id = '" + id + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                service = new Service();
                service.setId(rs.getInt("Item_Id"));
                return service;
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
     * per avere tutti i dati presenti nella tabella Utente
     */
    @Override
    public ArrayList<Service> findAll() {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT Item_Id FROM myshopmf.Service;");
        ArrayList<Service> services = new ArrayList<>();
        try {
            while (rs.next()) {
                service = new Service();
                service.setId(rs.getInt("Item_Id"));
                services.add(service);
            }
            return services;
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
     * per aggiungere un nuovo Utente nel db
     */
    @Override
    public int add(Item i) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO `myshopmf`.`Service` (`Item_Id`) VALUES ('" + i.getId() + "');");
        conn.close();
        return rowCount;
    }

    /**
     * per modificare un Utente esistente
     */
    @Override
    public int update(Item i, int oldId) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE `myshopmf`.`Service` SET `Item_Id` = '" + i.getId() + "' WHERE (`Item_Id` = '" + oldId + "');");
        conn.close();
        return rowCount;
    }

    /**
     * per eliminare un Utente in base al suo Id
     */
    @Override
    public int removeById(int id) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM `myshopmf`.`Service` WHERE (`Item_Id` = '" + id + "');");
        conn.close();
        return rowCount;
    }

    /**
     * per aggiungere un Item in una lista d'acquisto
     */
    @Override
    public int addToList(int idList, int idService) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO `myshopmf`.`List_has_Item` (`List_Id`, `Item_Id`) VALUES ('" + idList + "', '" + idService +"');");
        conn.close();
        return rowCount;
    }
}
