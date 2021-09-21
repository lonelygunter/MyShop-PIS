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
import Model.Item;
import Model.List;

public class ListDAO implements IListDAO {
    private static ListDAO instance = new ListDAO();
    private List list;
    private Item item;
    private static IDbConnection conn;
    private static ResultSet rs;

    private ListDAO(){
        list = null;
        item = null;
        conn = null;
        rs = null;
    }

    public static ListDAO getInstance() {
        return instance;
    }

    /**
     * per cercare List tramite id
     */
    @Override
    public List findById(int id) {
        conn = DbConnection.getInstance();
        String sql = "SELECT Id, Name, Date, Price, State, User_Id FROM myshopmf.List WHERE myshopmf.List.Id = '" + id + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                list = new List();
                list.setId(rs.getInt("Id"));
                list.setName(rs.getString("Name"));
                list.setDate(rs.getDate("Date"));
                list.setPrice(rs.getFloat("Price"));
                list.setState(rs.getString("State"));
                list.setBuyerId(rs.getInt("User_Id"));
                return list;
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
     * per avere tutti i dati presenti nella tabella List
     */
    @Override
    public ArrayList<List> findAll() {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT Id, Name, Date, Price, State, User_Id FROM myshopmf.List;");
        ArrayList<List> lists = new ArrayList<>();
        try {
            while (rs.next()) {
                list = new List();
                list.setId(rs.getInt("Id"));
                list.setName(rs.getString("Name"));
                list.setDate(rs.getDate("Date"));
                list.setPrice(rs.getFloat("Price"));
                list.setState(rs.getString("State"));
                list.setBuyerId(rs.getInt("User_Id"));
                lists.add(list);
            }
            return lists;
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
     * per aggiungere un nuovo List nel db
     */
    @Override
    public int add(List l) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
        LocalDateTime now = LocalDateTime.now();  

        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO `myshopmf`.`List` (`Name`, `Date`, `Price`, `User_Id`, `State`) VALUES ('" + l.getName() + "', '" + dtf.format(now) + "', '" + l.getPrice() + "', '" + l.getBuyerId() + "', '" + l.getState() + "');");
        conn.close();
        return rowCount;
    }

    /**
     * per modificare un List esistente
     */
    @Override
    public int update(List l) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE `myshopmf`.`List` SET `Name` = '" + l.getName() + "', `Date` = '" + l.getDate() + "', `Price` = '" + l.getPrice() + "', `User_Id` = '" + l.getBuyerId() + "', `State` = '" + l.getState() + "' WHERE (`Id` = '" + l.getId() + "');");
        conn.close();
        return rowCount;
    }

    /**
     * per eliminare un List in base al suo Id
     */
    @Override
    public int removeById(int id) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM `myshopmf`.`List` WHERE (`Id` = '" + id +"');");
        conn.close();
        return rowCount;
    }

    /**
     * per verificare che una lista esiste nel db
     */
    @Override
    public boolean listExists(String name) {
        conn = DbConnection.getInstance();
        boolean listExists = false;

        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT count(*) AS `C` FROM `myshopmf`.`List` WHERE (`List`.`Name` = '" + name + "');";
        ReadOperation op = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(op);

        try {
            rs.next();
            if(rs.getRow()==1 && rs.getInt("C")==1) listExists = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }  finally {
            conn.close();
        }

        return listExists;
    }

    /**
     * per avere tutti i dati presenti nella tabella List
     */
    @Override
    public ArrayList<Item> getProductListById(int listId) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT `Item_Id` FROM `myshopmf`.`List_has_Item` AS `l` WHERE (`l`.`List_Id` = '" + listId + "');");
        ArrayList<Item> items = new ArrayList<>();
        try {
            while (rs.next()) {
                item = ItemDAO.getInstance().findById(rs.getInt("Item_Id"));
                items.add(item);
            }
            return items;
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
     * per avere tutti i dati presenti nella tabella List
     */
    @Override
    public int insertItem(Item item, List list) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO `myshopmf`.`List_has_Item` (`List_Id`, `Item_Id`) VALUES ('" + list.getId() + "', '" + item.getId() + "');");
        conn.close();
        return rowCount;
    }

    /**
     * per cercare List tramite id
     */
    @Override
    public List getByName(String name) {
        conn = DbConnection.getInstance();
        String sql = "SELECT Id, Name, Date, Price, State, User_Id FROM myshopmf.List WHERE myshopmf.List.Name = '" + name + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                list = new List();
                list.setId(rs.getInt("Id"));
                list.setName(rs.getString("Name"));
                list.setDate(rs.getDate("Date"));
                list.setPrice(rs.getFloat("Price"));
                list.setState(rs.getString("State"));
                list.setBuyerId(rs.getInt("User_Id"));
                return list;
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
     * per cercare gli item presenti nella lista
     */
    @Override
    public ArrayList<Item> getListItemsByName(String name) {
        String sql = "SELECT `Item_Id` FROM `myshopmf`.`List_has_Item` AS `l` WHERE (`l`.`List_Id` = '" + getByName(name).getId() + "');";
        conn = DbConnection.getInstance();
        rs = conn.executeQuery(sql);

        ArrayList<Item> items = new ArrayList<>();
        try {
            while (rs.next()) {
                items.add(ItemDAO.getInstance().findById(rs.getInt("Item_Id")));
            }
            return items;
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
     * per cercare gli item presenti nella lista dall'id della lista
     */
    @Override
    public ArrayList<Item> getListItemsById(int id){
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT `Item_Id` FROM `myshopmf`.`List_has_Item` AS `l` WHERE (`l`.`List_Id` = '" + id + "');");
        ArrayList<Item> items = new ArrayList<>();
        try {
            while (rs.next()) {
                items.add(ItemDAO.getInstance().findById(rs.getInt("Item_Id")));
            }
            return items;
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
    public boolean itemExists(int itemId, int listId) {
        conn = DbConnection.getInstance();
        boolean itemExists = false;

        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT count(*) AS `C` FROM `myshopmf`.`List_has_Item` AS `l` WHERE (`l`.`Item_Id` = '" + itemId + "' AND `l`.`List_Id` = '" + listId + "');";
        ReadOperation op = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(op);

        try {
            rs.next();
            if(rs.getRow()==1 && rs.getInt("C")==1) itemExists = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            conn.close();
        }

        return itemExists;
    }

    /**
     * per eliminare un List_has_Item in base al suo Id
     */
    @Override
    public int removeListHasItemById(int listId, int itemId) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM `myshopmf`.`List_has_Item` WHERE (`List_Id` = '" + listId +"' AND `Item_Id` = '" + itemId + "');");
        conn.close();
        return rowCount;
    }

    /**
     * per eliminare tutti gli List_has_Item in base al suo Id
     */
    @Override
    public int removeAllListHasItemById(int listId) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM `myshopmf`.`List_has_Item` WHERE (`List_Id` = '" + listId + "');");
        conn.close();
        return rowCount;
    }

    /**
     * per aggiornare il prezzo della lista
     */
    @Override
    public int updatePrice(int listId, float totalPrice) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE `myshopmf`.`List` SET `Price` = '" + totalPrice+ "' WHERE (`Id` = '" + listId + "');");
        conn.close();
        return rowCount;
    }
}
