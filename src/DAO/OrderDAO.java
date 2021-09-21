package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import DbInterface.DbConnection;
import DbInterface.DbOperationExecutor;
import DbInterface.IDbConnection;
import DbInterface.IDbOperation;
import DbInterface.ReadOperation;
import Model.Order;

public class OrderDAO implements IOrderDAO{

    private static OrderDAO instance = new OrderDAO();
    private Order order;
    private static IDbConnection conn;
    private static ResultSet rs;

    private OrderDAO(){
        order = null;
        conn = null;
        rs = null;
    }

    public static OrderDAO getInstance() {
        return instance;
    }

    /**
     * per cercare Order tramite id
     */
    @Override
    public Order findById(int id) {
        conn = DbConnection.getInstance();
        String sql = "SELECT Id, Date, Price, User_Id, Item_Id FROM myshopmf.Order WHERE myshopmf.Order.Id = '" + id + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                order = new Order();
                order.setId(rs.getInt("Id"));
                order.setDate(rs.getDate("Date"));
                order.setPrice(rs.getFloat("Price"));
                order.setUserId(rs.getInt("User_Id"));
                order.setItemId(rs.getInt("Item_Id"));
                return order;
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
     * per avere tutti i dati presenti nella tabella Order
     */
    @Override
    public ArrayList<Order> findAll() {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT Id, Date, Price, User_Id, Item_Id FROM myshopmf.Order;");
        ArrayList<Order> orders = new ArrayList<>();
        try {
            while (rs.next()) {
                order = new Order();
                order.setId(rs.getInt("Id"));
                order.setDate(rs.getDate("Date"));
                order.setPrice(rs.getFloat("Price"));
                order.setUserId(rs.getInt("User_Id"));
                order.setItemId(rs.getInt("Item_Id"));
                orders.add(order);
            }
            return orders;
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
     * per aggiungere un nuovo Order nel db
     */
    @Override
    public int add(Order o) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
        LocalDateTime now = LocalDateTime.now();  

        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO `myshopmf`.`Order` (`Date`, `Price`, `User_Id`, `Item_Id`) VALUES ('" + dtf.format(now) + "', '" + o.getPrice() + "', '" + o.getUser().getId() + "', '" + o.getItem().getId() + "');");
        conn.close();
        return rowCount;
    }

    /**
     * per modificare un Order esistente
     */
    @Override
    public int update(Order o) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE `myshopmf`.`Order` SET `Date` = '" + o.getDate() + "', `Price` = '" + o.getPrice() + "', `User_Id` = '" + o.getUserId() + "', `Item_Id` = '" + o.getItemId() + "' WHERE (`Id` = '" + o.getId() + "');");
        conn.close();
        return rowCount;
    }

    /**
     * per eliminare un Order in base al suo Id
     */
    @Override
    public int removeById(int id) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM `myshopmf`.`Order` WHERE (`Id` = '" + id + "');");
        conn.close();
        return rowCount;
    }

    /**
     * per avere tutti i dati presenti nella tabella Item
     */
    @Override
    public ArrayList<Order> findAllOrderBy(String sql) {
        if (sql.equals("")) {
            sql = "SELECT Id, Date, Price, User_Id, Item_Id FROM myshopmf.Order;";
        }

        conn = DbConnection.getInstance();
        rs = conn.executeQuery(sql);
        ArrayList<Order> orders = new ArrayList<>();
        try {
            while (rs.next()) {
                order = new Order();
                order.setId(rs.getInt("Id"));
                order.setDate(rs.getDate("Date"));
                order.setPrice(rs.getFloat("Price"));
                order.setUserId(rs.getInt("User_Id"));
                order.setUser(UserDAO.getInstance().findById(order.getUserId()));
                order.setItemId(rs.getInt("Item_Id"));
                order.setItem(ItemDAO.getInstance().findById(order.getItemId()));

                orders.add(order);
            }
            return orders;
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
     * per cercare Item tramite id
     */
    @Override
    public Order findByName(String name) {
        conn = DbConnection.getInstance();
        String sql = "SELECT Id, Date, Price, User_Id, Item_Id FROM myshopmf.Order WHERE myshopmf.Order.Item_Id = '" + ItemDAO.getInstance().findByName(name).getId() + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                order = new Order();
                order.setId(rs.getInt("Id"));
                order.setDate(rs.getDate("Date"));
                order.setPrice(rs.getFloat("Price"));
                order.setUserId(rs.getInt("User_Id"));
                order.setItemId(rs.getInt("Item_Id"));
                return order;
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
     * per cercare Item tramite id
     */
    @Override
    public Order findByNameAndUserId(String name, int userId) {
        String sql = "SELECT Id, Date, Price, User_Id, Item_Id FROM myshopmf.Order WHERE myshopmf.Order.Item_Id = '" + ItemDAO.getInstance().findByName(name).getId() + "' AND myshopmf.Order.User_Id = '" + userId + "';";

        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                order = new Order();
                order.setId(rs.getInt("Id"));
                order.setDate(rs.getDate("Date"));
                order.setPrice(rs.getFloat("Price"));
                order.setUserId(rs.getInt("User_Id"));
                order.setItemId(rs.getInt("Item_Id"));
                return order;
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
     * per eliminare tutti gli elementi nella tabell
     */
    @Override
    public int removeAll(int currentUserId) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM `myshopmf`.`Order` AS `o` WHERE (`o`.`User_Id` = '" + currentUserId + "');");
        conn.close();
        return rowCount;
    }

    /**
     * per eliminare un Order in base al suo Id
     */
    @Override
    public int removeToOrder(int id) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM `myshopmf`.`Order` WHERE (`Item_Id` = '" + id + "');");
        conn.close();
        return rowCount;
    }
}
