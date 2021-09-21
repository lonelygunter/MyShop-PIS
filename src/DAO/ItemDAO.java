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
import Model.User;

public class ItemDAO implements IItemDAO{

    private static  ItemDAO instance = new  ItemDAO();
    private Item item;
    private static IDbConnection conn;
    private static ResultSet rs;

    private  ItemDAO(){
        item = null;
        conn = null;
        rs = null;
    }

    public static  ItemDAO getInstance() {
        return instance;
    }

    /**
     * per cercare Item tramite id
     */
    @Override
    public Item findById(int id) {
        conn = DbConnection.getInstance();
        String sql = "SELECT Id, Name, Description, Price, Type, Wholesaler_Id, Category_Id FROM myshopmf.Item WHERE myshopmf.Item.Id = '" + id + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                item = new Item();
                item.setId(rs.getInt("Id"));
                item.setName(rs.getString("Name"));
                item.setDescription(rs.getString("Description"));
                item.setPrice(rs.getFloat("Price"));
                item.setType(rs.getString("Type"));
                item.setWholesalerId(rs.getInt("Wholesaler_Id"));
                item.setCategoryId(rs.getInt("Category_Id"));
                return item;
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
     * per avere tutti i dati presenti nella tabella Item
     */
    @Override
    public ArrayList<Item> findAll() {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT Id, Name, Description, Price, Type, Wholesaler_Id, Category_Id FROM myshopmf.Item;");
        ArrayList<Item> items = new ArrayList<>();
        try {
            while (rs.next()) {
                item = new Item();
                item.setId(rs.getInt("Id"));
                item.setName(rs.getString("Name"));
                item.setDescription(rs.getString("Description"));
                item.setPrice(rs.getFloat("Price"));
                item.setType(rs.getString("Type"));
                item.setWholesalerId(rs.getInt("Wholesaler_Id"));
                item.setCategoryId(rs.getInt("Category_Id"));
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
     * per avere tutti i dati presenti nella tabella Item
     */
    @Override
    public ArrayList<Item> findAllOrderBy(String sql) {
        if (sql.equals("")) {
            sql = "SELECT Id, Name, Description, Price, Type, Wholesaler_Id, Category_Id FROM myshopmf.Item;";
        }

        conn = DbConnection.getInstance();
        rs = conn.executeQuery(sql);
        ArrayList<Item> items = new ArrayList<>();
        try {
            while (rs.next()) {
                item = new Item();
                item.setId(rs.getInt("Id"));
                item.setName(rs.getString("Name"));
                item.setDescription(rs.getString("Description"));
                item.setPrice(rs.getFloat("Price"));
                item.setType(rs.getString("Type"));
                item.setWholesalerId(rs.getInt("Wholesaler_Id"));
                item.setCategoryId(rs.getInt("Category_Id"));
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
     * per aggiungere un nuovo Utente nel db
     */
    @Override
    public int add(Item i) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO `myshopmf`.`Item` (`Name`, `Description`, `Price`, `Type`, `Available`, `Wholesaler_Id`, `Category_Id`) VALUES ('" + i.getName() + "', '" + i.getDescription() + "', '" + i.getPrice() + "', '" + i.getType() + "', '" + i.isAvailable() + "', '" + i.getWholesalerId() + "', '" + i.getCategoryId() + "');");
        conn.close();
        return rowCount;
    }

    /**
     * per modificare un Utente esistente
     */
    @Override
    public int update(Item i) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE `myshopmf`.`Item` SET `Name` = '" + i.getName() + "', `Description` = '" + i.getDescription() + "', `Price` = '" + i.getPrice() + "', `Type` = '" + i.getType() + "', `Available` = '" + i.isAvailable() + "', `Wholesaler_Id` = '" + i.getWholesalerId() + "', `Category_Id` = '" + i.getCategoryId() + "' WHERE (`Id` = '" + i.getId() +"');");
        conn.close();
        return rowCount;
    }

    /**
     * per eliminare un Utente in base al suo Id
     */
    @Override
    public int removeById(int id) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM `myshopmf`.`Item` WHERE (`Id` = '" + id + "');");
        conn.close();
        return rowCount;
    }

    /**
     * per aggiungere un Item in una lista d'acquisto
     */
    @Override
    public int addToList(int idList, int idItem) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO `myshopmf`.`List_has_Item` (`List_Id`, `Item_Id`) VALUES ('" + idList + "', '" + idItem +"');");
        conn.close();
        return rowCount;
    }

    /**
     * per creare l'ordine di un item
     */
    @Override
    public int addOrder(Item i, List list, User user) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
        LocalDateTime now = LocalDateTime.now();  

        conn = DbConnection.getInstance();
        int rowCount;
        if (list == null) {
            rowCount = conn.executeUpdate("INSERT INTO `myshopmf`.`Order` (`Date`, `Price`, `User_Id`) VALUES ('" + dtf.format(now) + "', '" + i.getPrice() + "', '"  + user.getId() + "');");
        } else {
            rowCount = conn.executeUpdate("INSERT INTO `myshopmf`.`Order` (`Date`, `Price`, `List_Id`, `User_Id`) VALUES ('" + dtf.format(now) + "', '" + i.getPrice() + "', '" + list.getId() + "', '" + user.getId() + "');");
        }
        conn.close();
        return rowCount;
    }

    /**
     * per cercare Item tramite id
     */
    @Override
    public Item findByName(String name) {
        conn = DbConnection.getInstance();
        String sql = "SELECT Id, Name, Description, Price, Type, Wholesaler_Id, Category_Id FROM myshopmf.Item WHERE myshopmf.Item.Name = '" + name + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                item = new Item();
                item.setId(rs.getInt("Id"));
                item.setName(rs.getString("Name"));
                item.setDescription(rs.getString("Description"));
                item.setPrice(rs.getFloat("Price"));
                item.setType(rs.getString("Type"));
                item.setWholesalerId(rs.getInt("Wholesaler_Id"));
                item.setCategoryId(rs.getInt("Category_Id"));
                return item;
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
    public boolean itemExists(Item item) {
        conn = DbConnection.getInstance();
        boolean itemExists = false;

        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT count(*) AS `C` FROM `myshopmf`.`Item` WHERE (`Item`.`Id` = '" + item.getId() + "');";
        ReadOperation op = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(op);

        try {
            rs.next();
            if(rs.getRow()==1 && rs.getInt("C")==1) itemExists = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }  finally {
            conn.close();
        }

        return itemExists;
    }

    /**
     * per verificare che una lista esiste nel db
     */
    @Override
    public boolean itemNameExists(Item item) {
        conn = DbConnection.getInstance();
        boolean itemExists = false;

        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT count(*) AS `C` FROM `myshopmf`.`Item` WHERE (`Item`.`Name` = '" + item.getName() + "');";
        ReadOperation op = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(op);

        try {
            rs.next();
            if(rs.getRow()==1 && rs.getInt("C")==1) itemExists = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }  finally {
            conn.close();
        }

        return itemExists;
    }
}