package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DbInterface.DbConnection;
import DbInterface.DbOperationExecutor;
import DbInterface.IDbConnection;
import DbInterface.IDbOperation;
import DbInterface.ReadOperation;
import Model.Order;
import Model.Payment;

public class PaymentDAO implements IPaymentDAO{

    private static PaymentDAO instance = new PaymentDAO();
    private Payment payment;
    private static IDbConnection conn;
    private static ResultSet rs;

    private PaymentDAO(){
        payment = null;
        conn = null;
        rs = null;
    }

    public static PaymentDAO getInstance() {
        return instance;
    }

    /**
     * per cercare Payment tramite id
     */
    @Override
    public Payment findById(int id) {
        conn = DbConnection.getInstance();
        String sql = "SELECT NCard, VDate, CVV, Order_Id FROM myshopmf.Payment WHERE myshopmf.Payment.Order_Id = '" + id + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                payment = new Payment();
                payment.setNCard(rs.getLong("NCard"));
                payment.setVDate(rs.getDate("VDate"));
                payment.setCvv(rs.getInt("CVV"));
                payment.setOrderId(rs.getInt("Order_Id"));
                return payment;
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
     * per avere tutti i dati presenti nella tabella Payment
     */
    @Override
    public ArrayList<Payment> findAll() {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT Order_Id, NCard, VDate, CVV FROM myshopmf.Payment;");
        ArrayList<Payment> payments = new ArrayList<>();
        try {
            while (rs.next()) {
                payment = new Payment();
                payment.setNCard(rs.getLong("NCard"));
                payment.setVDate(rs.getDate("VDate"));
                payment.setCvv(rs.getInt("CVV"));
                payment.setOrderId(rs.getInt("Order_Id"));
                payments.add(payment);
            }
            return payments;
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
     * per aggiungere un nuovo Payment nel db
     */
    @Override
    public int add(Payment p) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO `myshopmf`.`Payment` (`Order_Id`, `NCard`, `VDate`, `CVV`) VALUES ('" + getLastOrderId() + "', '" + p.getNCard() + "', '" + p.getVDate() + "', '" + p.getCvv() + "');");
        conn.close();
        return rowCount;
    }

    public int getLastOrderId() {
        IDbConnection conn;
        ResultSet rs;
        Order order;
        conn = DbConnection.getInstance();
        String sql = "SELECT `Id` FROM `myshopmf`.`Order` ORDER BY `Id` DESC LIMIT 1;";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                order = new Order();
                order.setId(rs.getInt("Id"));
                return order.getId();
            }
        } catch (SQLException e) {

            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {

            System.out.println("Resultset: " + e.getMessage());
        }  finally {
            conn.close();
        }
        return 0;
    }
}