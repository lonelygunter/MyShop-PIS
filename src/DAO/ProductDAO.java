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
import Model.Position;
import Model.Product;

public class ProductDAO implements IProductDAO{

    private static ProductDAO instance = new ProductDAO();
    private Product product;
    private static IDbConnection conn;
    private static ResultSet rs;


    protected ProductDAO() {
        product = null;
        conn = null;
        rs = null;
    }


    public static ProductDAO getInstance() {
        return instance;
    }

    /**
     * per cercare Utenti tramite il lodo id
     */
    @Override
    public Product findById(int id) {
        conn = DbConnection.getInstance();
        String sql = "SELECT Item_Id, Quantity, Position_Id FROM myshopmf.Product WHERE myshopmf.Product.Item_Id = '" + id + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                product = new Product();
                product.setId(rs.getInt("Item_Id"));
                product.setQuantity(rs.getInt("Quantity"));
                product.setPositionId(rs.getInt("Position_Id"));
                return product;
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
    public ArrayList<Product> findAll() {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT Item_Id, Quantity, Position_Id  FROM myshopmf.Product;");
        ArrayList<Product> products = new ArrayList<>();
        try {
            while (rs.next()) {
                product = new Product();
                product.setId(rs.getInt("Item_Id"));
                product.setQuantity(rs.getInt("Quantity"));
                product.setPositionId(rs.getInt("Position_Id"));
                products.add(product);
            }
            return products;
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
    public int add(Item item, int quantity, Position position) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO `myshopmf`.`Product` (`Item_Id`, `Quantity`, `Position_Id`) VALUES ('" + item.getId() + "', '" + quantity + "', '" + position.getId() + "');");
        conn.close();
        return rowCount;
    }

    /**
     * per modificare un Utente esistente
     */
    @Override
    public int update(Item item, int quantity, Position position) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE `myshopmf`.`Product` SET `Item_Id` = '" + item.getId() + "', `Quantity` = '" + quantity + "', `Position_Id` = '" + position.getId() + "' WHERE (`Item_Id` = '" + item.getId() + "');");
        conn.close();
        return rowCount;
    }

    /**
     * per eliminare un Utente in base al suo Id
     */
    @Override
    public int removeById(int id) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM `myshopmf`.`Product` WHERE (`Item_Id` = '" + id + "');");
        conn.close();
        return rowCount;
    }

    /**
     * permette di richiedere la prenotazione di un articolo se quest'ultimo non è disponibile 
     */
    @Override
    public int reservation(Product p, Item i) {
        conn = DbConnection.getInstance();
        if(p.getQuantity() == 0) {
            conn = DbConnection.getInstance();
            int rowCount = conn.executeUpdate("INSERT INTO `myshopmf`.`Item_has_Buyer` (`Item_Id`, `Buyer_Id`) VALUES ('" + i.getId() + "', '" + p.getId() + "');");
            conn.close();
            return rowCount;
        }
        return 1;
    }


    /**
     * per aggiungere un Item in una lista d'acquisto
     */
    @Override
    public int addToList(int idList, int idProduct) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO `myshopmf`.`List_has_Item` (`List_Id`, `Item_Id`) VALUES ('" + idList + "', '" + idProduct +"');");
        conn.close();
        return rowCount;
    }
}