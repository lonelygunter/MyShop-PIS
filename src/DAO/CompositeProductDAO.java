package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DbInterface.DbConnection;
import DbInterface.DbOperationExecutor;
import DbInterface.IDbConnection;
import DbInterface.IDbOperation;
import DbInterface.ReadOperation;
import Model.CompositeProduct;
import Model.Product;

public class CompositeProductDAO implements ICompositeProductDAO{

    private static CompositeProductDAO instance = new CompositeProductDAO();
    private CompositeProduct compositeProduct;
    private static IDbConnection conn;
    private static ResultSet rs;


    protected CompositeProductDAO() {
        compositeProduct = null;
        conn = null;
        rs = null;
    }

    public static CompositeProductDAO getInstance() {
        return instance;
    }

    /**
     * per cercare Composite Product tramite id
     */
    @Override
    public CompositeProduct findById(int id) {
        conn = DbConnection.getInstance();
        String sql = "SELECT `Composite_Product_Id`, `Inserted_Product_Id` FROM `myshopmf`.`Product_has_Product` WHERE (`myshopmf`.`Product_has_Product`.`Composite_Product_Id` = '" + id + "');";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                compositeProduct = new CompositeProduct();
                compositeProduct.setId(rs.getInt("Composite_Product_Id"));
                //TODO compositeProduct.set???(rs.getInt("Inserted_Product_Id"));
                return compositeProduct;
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
     * per avere tutti i dati presenti nella tabella dei Composite Product
     */
    @Override
    public ArrayList<CompositeProduct> findAll() {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT `Composite_Product_Id`, `Inserted_Product_Id` FROM `myshopmf`.`Product_has_Product`;");
        ArrayList<CompositeProduct> compositeProducts = new ArrayList<>();
        try {
            while (rs.next()) {
                compositeProduct = new CompositeProduct();
                compositeProduct.setId(rs.getInt("Id"));
                //TODO compositeProduct.set???(rs.getInt("Inserted_Product_Id"));
                compositeProducts.add(compositeProduct);
            }
            return compositeProducts;
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
     * per aggiungere un nuovo Composite Product nel db
     */
    @Override
    public int add(CompositeProduct cp, Product p) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO `myshopmf`.`Product_has_Product` (`Composite_Product_Id`, `Inserted_Product_Id`) VALUES ('"+ cp.getId() + "', '" + p.getId() + "');");
        conn.close();
        return rowCount;
    }

    /**
     * per modificare un Composite Product esistente
     */
    @Override
    public int update(CompositeProduct cp, int newId) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE `myshopmf`.`Product_has_Product` SET `Composite_Product_Id` = '" + cp.getId() + "' WHERE (`Composite_Product_Id` = '" + newId + "');");
        conn.close();
        return rowCount;
    }

    /**
     * per eliminare un Composite Product in base al suo Id
     */
    @Override
    public int removeById(int id) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM `myshopmf`.`Product_has_Product` WHERE (`Composite_Product_Id` = '" + id + "');");
        conn.close();
        return rowCount;
    }
}