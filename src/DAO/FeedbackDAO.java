package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import DbInterface.DbConnection;
import DbInterface.DbOperationExecutor;
import DbInterface.IDbConnection;
import DbInterface.IDbOperation;
import DbInterface.ReadOperation;
import Model.Feedback;

public class FeedbackDAO implements IFeedbackDAO {
    private static FeedbackDAO instance = new FeedbackDAO();
    private Feedback feedback;
    private static IDbConnection conn;
    private static ResultSet rs;

    private FeedbackDAO(){
        feedback = null;
        conn = null;
        rs = null;
    }

    public static FeedbackDAO getInstance() {
        return instance;
    }

    /**
     * per cercare Feedback tramite il lodo id
     */
    @Override
    public Feedback findById(int id) {
        conn = DbConnection.getInstance();
        String sql = "SELECT Id, Comment, Rating, Item_Id, Purchase_has_Item_Id, Date FROM myshopmf.Feedback WHERE myshopmf.Feedback.Id = '" + id + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                feedback = new Feedback();
                feedback.setId(rs.getInt("Id"));
                feedback.setComment(rs.getString("Comment"));
                feedback.setRating(rs.getInt("Rating"));
                feedback.setPurchaseHasItem(rs.getInt("Purchase_has_Item_Id"));
                feedback.setDate(rs.getDate("Date"));
                return feedback;
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
     * per avere tutti i dati presenti nella tabella Feedback
     */
    @Override
    public ArrayList<Feedback> findAll() {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT Id, Comment, Rating, Item_Id, Purchase_has_Item_Id, Date, Answering FROM myshopmf.Feedback;");
        ArrayList<Feedback> feedbacks = new ArrayList<>();
        try {
            while (rs.next()) {
                feedback = new Feedback();
                feedback.setId(rs.getInt("Id"));
                feedback.setComment(rs.getString("Comment"));
                feedback.setRating(rs.getInt("Rating"));
                feedback.setPurchaseHasItem(rs.getInt("Purchase_has_Item_Id"));
                feedback.setDate(rs.getDate("Date"));
                feedback.setAnswering(rs.getInt("Answering"));
                feedbacks.add(feedback);
            }
            return feedbacks;
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
     * per aggiungere un commento da parte dell'utente acquirente
     */
    @Override
    public int write(Feedback f) {
        conn = DbConnection.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        int rowCount = conn.executeUpdate("INSERT INTO `myshopmf`.`Feedback` (`Comment`, `Rating`, `Date`, `Item_Id`, `Purchase_has_Item_Id`, `Answering`) VALUES ('" + f.getComment() + "', '" + f.getRating() + "', '" + formatter.format(f.getDate()) + "', '" + f.getItemId() + "', '" + f.getPurchaseHasItem() + "', '" + f.getAnswering() + "');");
        conn.close();
        return rowCount;
    }

    /**
     * per rispondere ad un commento da parte del Manager
     */
    @Override
    public int answer(Feedback f, boolean managerOrAdmin) {
        conn = DbConnection.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        String sql = "";
        if (managerOrAdmin) {
            sql = "INSERT INTO `myshopmf`.`Feedback` (`Comment`, `Rating`, `Date`, `Item_Id`, `Answering`) VALUES ('" + f.getComment() + "', '" + f.getRating() + "', '" + formatter.format(f.getDate()) + "', '" + f.getItemId() + "', '" + f.getAnswering() + "');";
        } else {
            sql = "INSERT INTO `myshopmf`.`Feedback` (`Comment`, `Rating`, `Date`, `Item_Id`, `Purchase_has_Item_Id`, `Answering`) VALUES ('" + f.getComment() + "', '" + f.getRating() + "', '" + formatter.format(f.getDate()) + "', '" + f.getItemId() + "', '" + f.getPurchaseHasItem() + "', '" + f.getAnswering() + "');";
        }
        int rowCount = conn.executeUpdate(sql);
        conn.close();
        return rowCount;
    }

    /**
     * per prendere l'item id dall'id
     */
    @Override
    public int getItemIdById(int id) {
        conn = DbConnection.getInstance();
        String sql = "SELECT `Item_Id` FROM `myshopmf`.`Feedback` AS `f` WHERE (`f`.Id = '" + id + "');";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                return rs.getInt("Item_Id");
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
        return 0;
    }
    
    /**
     * per prendere il PurchaseHaseItem dall'id
     */
    @Override
    public int getPurchaseHasItemById(int id) {
        conn = DbConnection.getInstance();
        String sql = "SELECT `Purchase_has_Item_Id` FROM `myshopmf`.`Feedback` AS `f` WHERE (`f`.`Id` = '" + id + "');";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                return rs.getInt("Purchase_has_Item_Id");
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
        return 0;
    }
}
