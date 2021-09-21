package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DbInterface.DbConnection;
import DbInterface.DbOperationExecutor;
import DbInterface.IDbConnection;
import DbInterface.IDbOperation;
import DbInterface.ReadOperation;
import Model.Category;

public class CategoryDAO implements ICategoryDAO{

    private static CategoryDAO instance = new CategoryDAO();
    private Category category;
    private static IDbConnection conn;
    private static ResultSet rs;

    private CategoryDAO(){
        category = null;
        conn = null;
        rs = null;
    }

    public static CategoryDAO getInstance() {
        return instance;
    }

    /**
     * per cercare Category tramite id
     */
    @Override
    public Category findById(int id) {
        conn = DbConnection.getInstance();
        String sql = "SELECT `Id`, `Name`, `Category_father_Id` FROM `myshopmf`.`Category` WHERE `myshopmf`.`Category`.`Id` = '" + id + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                category = new Category();
                category.setId(rs.getInt("Id"));
                category.setName(rs.getString("Name"));
                category.setFatherId(rs.getInt("Category_father_Id"));
                return category;
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
     * per avere tutti i dati presenti nella tabella Category
     */
    @Override
    public ArrayList<Category> findAll() {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT `Id`, `Name`, `Category_father_Id` FROM `myshopmf`.`Category`;");
        ArrayList<Category> categories = new ArrayList<>();
        try {
            while (rs.next()) {
                category = new Category();
                category.setId(rs.getInt("Id"));
                category.setName(rs.getString("Name"));
                category.setFatherId(rs.getInt("Category_father_Id"));
                categories.add(category);
            }
            return categories;
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
     * per aggiungere una nuova Category nel db
     */
    @Override
    public int add(Category c) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO `myshopmf`.`Category` (`Name`, `Category_father_Id`) VALUES ('" + c.getName() + "', '" + c.getFatherId() + "');");
        conn.close();
        return rowCount;
    }

    /**
     * per modificare una Category esistente
     */
    @Override
    public int update(Category c) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE `myshopmf`.`Category` SET `Name` = '" + c.getName() + "', `Category_father_Id` = '" + c.getFatherId() + "' WHERE (`Id` = '" + c.getId() + "');");
        conn.close();
        return rowCount;
    }

    /**
     * per eliminare una Category in base al suo Id
     */
    @Override
    public int removeById(int id) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM `myshopmf`.`Category` WHERE (`Id` = '" + id + "');");
        conn.close();
        return rowCount;
    }

    /**
     * per avere tutti i nomi delle categorie in un String[]
     */
    @Override
    public String[] getAllNames() {
        ArrayList<Category> categories = findAll();
        String[] names = new String[categories.size()];
        int i = 0;

        while (i < categories.size()) {
            if(categories.get(i).getId() != 0) {
                names[i] = categories.get(i).getName();
            }
            i++;
        }

        return names;
    }

    /**
     * per recuperare un User tramite il suo father id
     */
    @Override
    public ArrayList<Category> getAllByFatherId(int fatherId) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT `Id`, `Name`, `Category_father_Id` FROM `myshopmf`.`Category` WHERE (`Category`.`Category_father_Id` = '" + fatherId + "');");
        ArrayList<Category> categories = new ArrayList<>();
        try {
            while (rs.next()) {
                category = new Category();
                category.setId(rs.getInt("Id"));
                category.setName(rs.getString("Name"));
                category.setFatherId(rs.getInt("Category_father_Id"));
                categories.add(category);
            }
            return categories;
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
     * per poer prendere tutte le categorie in ordine
     */
    @Override
    public ArrayList<Category> findAllOrderBy(String sql) {
        if (sql.equals("")) {
            sql = "SELECT Id, Name, Category_father_Id FROM myshopmf.Category;";
        }

        conn = DbConnection.getInstance();
        rs = conn.executeQuery(sql);
        ArrayList<Category> categories = new ArrayList<Category>();
        try {
            while (rs.next()) {
                category = new Category();
                category.setId(rs.getInt("Id"));
                category.setName(rs.getString("Name"));
                category.setFatherId(rs.getInt("Category_father_Id"));
                categories.add(category);
            }
            return categories;
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
     * per cercare Category tramite id
     */
    @Override
    public Category findByName(String name) {
        conn = DbConnection.getInstance();
        String sql = "SELECT `Id`, `Name`, `Category_father_Id` FROM myshopmf.Category WHERE myshopmf.Category.Name = '" + name + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                category = new Category();
                category.setId(rs.getInt("Id"));
                category.setName(rs.getString("Name"));
                category.setFatherId(rs.getInt("Category_father_Id"));
                return category;
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
    public boolean categoryExists(Category category) {
        conn = DbConnection.getInstance();
        boolean categoryExists = false;

        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT count(*) AS `C` FROM `myshopmf`.`Category` WHERE (`Category`.`Id` = '" + category.getId() + "');";
        ReadOperation op = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(op);

        try {
            rs.next();
            if(rs.getRow()==1 && rs.getInt("C")==1) categoryExists = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }  finally {
            conn.close();
        }

        return categoryExists;
    }

    /**
     * per verificare che una lista esiste nel db
     */
    @Override
    public boolean categoryNameExists(Category category) {
        conn = DbConnection.getInstance();
        boolean categoryExists = false;

        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT count(*) AS `C` FROM `myshopmf`.`Category` WHERE (`Category`.`Name` = '" + category.getName() + "');";
        ReadOperation op = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(op);

        try {
            rs.next();
            if(rs.getRow()==1 && rs.getInt("C")==1) categoryExists = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }  finally {
            conn.close();
        }

        return categoryExists;
    }

    
    /**
     * per aggiungere una nuova Category allo store
     */
    @Override
    public int addToStoreCategories(Category c, String store) {
        String sql = "INSERT INTO `myshopmf`.`Store_has_Category` (`Store_Id`, `Category_Id`) VALUES ('" + StoreDAO.getInstance().findByNation(store).getId() + "', '" + c.getId() + "');";
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate(sql);
        conn.close();
        return rowCount;
    }


    /**
     * per eliminare una nuova Category allo store
     */
    @Override
    public int deleteToStoreCategories(Category c, String store) {
        String sql = "DELETE FROM `myshopmf`.`Store_has_Category` WHERE (`Store_Id` = '" + StoreDAO.getInstance().findByNation(store).getId() + "' AND `Category_Id` = '" + c.getId() + "');";
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate(sql);
        conn.close();
        return rowCount;
    }

    /**
     * per cercare Category tramite id
     */
    @Override
    public ArrayList<String> findAllCurrentStore(int storeId) {
        conn = DbConnection.getInstance();
        String sql = "SELECT `Id`, `Store_Id`, `Category_Id` FROM `myshopmf`.`Store_has_Category` `c` WHERE (`c`.`Store_Id` = '" + storeId + "');";
        rs = conn.executeQuery(sql);
        ArrayList<String> categories = new ArrayList<>();
        try {
            while (rs.next()) {
                rs.getString("Id");
                rs.getString("Store_Id");
                categories.add(rs.getString("Category_Id"));
            }
            return categories;
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

