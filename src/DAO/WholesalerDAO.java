package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DbInterface.DbConnection;
import DbInterface.DbOperationExecutor;
import DbInterface.IDbConnection;
import DbInterface.IDbOperation;
import DbInterface.ReadOperation;
import Model.Wholesaler;

public class WholesalerDAO implements IWholesalerDAO{

    private static WholesalerDAO instance = new WholesalerDAO();
    private Wholesaler wholesaler;
    private static IDbConnection conn;
    private static ResultSet rs;

    private WholesalerDAO(){
        wholesaler = null;
        conn = null;
        rs = null;
    }

    public static WholesalerDAO getInstance() {
        return instance;
    }

    /**
     * per cercare Wholesaler tramite id
     */
    @Override
    public Wholesaler findById(int id) {
        conn = DbConnection.getInstance();
        String sql = "SELECT Id, Name, Email, Telephone, Website, City, Nation FROM myshopmf.Wholesaler WHERE myshopmf.Wholesaler.Id = '" + id + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
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

    /**
     * per avere tutti i dati presenti nella tabella Wholesaler
     */
    @Override
    public ArrayList<Wholesaler> findAll() {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT Id, Name, Email, Telephone, Website, City, Nation FROM myshopmf.Wholesaler;");
        ArrayList<Wholesaler> wholesalers = new ArrayList<>();
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
                wholesalers.add(wholesaler);
            }
            return wholesalers;
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
     * per aggiungere un nuovo Wholesaler nel db
     */
    @Override
    public int add(Wholesaler w) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO `myshopmf`.`Wholesaler` (`Name`, `Email`, `Telephone`, `Website`, `City`, `Nation`) VALUES ('" + w.getName() + "', '" + w.getEmail() + "', '" + w.getTelephone() + "', '" + w.getWebsite() + "', '" + w.getCity() + "', '" + w.getNation() + "');");
        conn.close();
        return rowCount;
    }

    /**
     * per modificare un Wholesaler esistente
     */
    @Override
    public int update(Wholesaler w) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE `myshopmf`.`Wholesaler` SET `Name` = '" + w.getName() + "', `Email` = '" + w.getEmail() + "', `Telephone` = '" + w.getTelephone() + "', `Website` = '" + w.getWebsite() + "', `City` = '" + w.getCity() + "', `Nation` = '" + w.getNation() + "' WHERE (`Id` = '" + w.getId() + "');");
        conn.close();
        return rowCount;
    }

    /**
     * per eliminare un Wholesaler in base al suo Id
     */
    @Override
    public int removeById(int id) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM `myshopmf`.`Wholesaler` WHERE (`Id` = '" + id + "');");
        conn.close();
        return rowCount;
    }

    /**
     * per prendere tutti i wholesaler ordinati
     */
    @Override
    public ArrayList<Wholesaler> findAllOrderBy(String sql) {
        if (sql.equals("")) {
            sql = "SELECT Id, Name, Email, Telephone, Website, City, Nation FROM myshopmf.Wholesaler;";
        }

        conn = DbConnection.getInstance();
        rs = conn.executeQuery(sql);
        ArrayList<Wholesaler> wholesalers = new ArrayList<>();
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
                wholesalers.add(wholesaler);
            }
            return wholesalers;
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
     * per cercare Wholesaler tramite id
     */
    @Override
    public Wholesaler findByName(String name) {
        conn = DbConnection.getInstance();
        String sql = "SELECT Id, Name, Email, Telephone, Website, City, Nation FROM myshopmf.Wholesaler WHERE myshopmf.Wholesaler.Name = '" + name + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
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

    /**
     * per verificare che una lista esiste nel db
     */
    @Override
    public boolean wholesalerExists(Wholesaler wholesaler) {
        conn = DbConnection.getInstance();
        boolean wholesalerExists = false;

        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT count(*) AS `C` FROM `myshopmf`.`Wholesaler` WHERE (`Wholesaler`.`Id` = '" + wholesaler.getId() + "');";
        ReadOperation op = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(op);

        try {
            rs.next();
            if(rs.getRow()==1 && rs.getInt("C")==1) wholesalerExists = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }  finally {
            conn.close();
        }

        return wholesalerExists;
    }

    /**
     * per verificare che una lista esiste nel db
     */
    @Override
    public boolean wholesalerNameExists(Wholesaler wholesaler) {
        conn = DbConnection.getInstance();
        boolean wholesalerExists = false;

        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT count(*) AS `C` FROM `myshopmf`.`Wholesaler` WHERE (`Wholesaler`.`Name` = '" + wholesaler.getName() + "');";
        ReadOperation op = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(op);

        try {
            rs.next();
            if(rs.getRow()==1 && rs.getInt("C")==1) wholesalerExists = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }  finally {
            conn.close();
        }

        return wholesalerExists;
    }
}
