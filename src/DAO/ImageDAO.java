package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DbInterface.DbConnection;
import DbInterface.DbOperationExecutor;
import DbInterface.IDbConnection;
import DbInterface.IDbOperation;
import DbInterface.ReadOperation;
import Model.Image;

public class ImageDAO implements IImageDAO{
    private static ImageDAO instance = new ImageDAO();
    private Image image;
    private static IDbConnection conn;
    private static ResultSet rs;

    private ImageDAO(){
        image = null;
        conn = null;
        rs = null;
    }

    public static ImageDAO getInstance() {
        return instance;
    }

    /**
     * per cercare Image tramite id
     */
    @Override
    public Image findById(int id) {
        conn = DbConnection.getInstance();
        String sql = "SELECT Id, Image, Item_Id FROM myshopmf.Image WHERE myshopmf.Image.Id = '" + id + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                image = new Image();
                image.setId(rs.getInt("Id"));
                image.setImage(rs.getBytes("Image"));
                image.setItemId(rs.getInt("Item_Id"));
                return image;
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
     * per avere tutti i dati presenti nella tabella Image
     */
    @Override
    public ArrayList<Image> findAll() {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT Id, Image, Item_Id FROM myshopmf.Image;");
        ArrayList<Image> images = new ArrayList<>();
        try {
            while (rs.next()) {
                image = new Image();
                image.setId(rs.getInt("Id"));
                image.setImage(rs.getBytes("Image"));
                image.setItemId(rs.getInt("Item_Id"));
                images.add(image);
            }
            return images;
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
     * per cercare Image tramite l'id del suo item
     */
    @Override
    public Image findByItemId(int id) {
        conn = DbConnection.getInstance();
        String sql = "SELECT `Id`, `Image`, `Item_Id` FROM `myshopmf`.`Image` AS `i` WHERE (`i`.`Item_Id` = '" + id + "');";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                image = new Image();
                image.setId(rs.getInt("Id"));
                image.setImage(rs.getBytes("Image"));
                image.setItemId(rs.getInt("Item_Id"));
                return image;
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
