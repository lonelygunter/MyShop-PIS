//command singleton
package DbInterface;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection implements IDbConnection {

    //Inplementa ed e' un singleton
    private static DbUser dbUser = DbUser.getInstance();
    private static DbConnection instance = new DbConnection();
    private static Connection conn;
    private static Statement stmt;
    private static ResultSet rs;
    private static int rowCount;

    private DbConnection() {
        conn = null;
        stmt = null;
        rs = null;
        rowCount = 0;
        try {
            Class cls = Class.forName("com.mysql.cj.jdbc.Driver"); //java data base connettivity (jdbc)
            System.out.println("Ho caricato la classe di nome: " + cls.getName());
        } catch (ClassNotFoundException e) {
            System.out.println("Non trovo il driver MySQL JDBC: " + e.getMessage());
        }
    }

    public static DbConnection getInstance() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/" + dbUser.getSchemaName() + "?serverTimezone=UTC", dbUser.getUserName(), dbUser.getPwd());
        } catch (SQLException e) {
            System.out.println("1");
            System.out.println("SQL Exception: " + e.getMessage());
            System.out.println("SQL State: " + e.getSQLState());
            System.out.println("Vendor Error: " + e.getErrorCode());
        }
        return instance;
    }

    @Override
    public ResultSet executeQuery(String sqlStatement) {
        try {
            //prendiam oin esecuzione la query e la pazziamo a rs
            stmt = conn.createStatement();
            
            rs = stmt.executeQuery(sqlStatement);
        } catch (SQLException e) {
            System.out.println("2");
            System.out.println("SQL Exception: " + e.getMessage());
            System.out.println("SQL State: " + e.getSQLState());
            System.out.println("Vendor Error: " + e.getErrorCode());
        }
        return rs;
    }

    @Override
    public int executeUpdate(String sqlStatement) {
        try {
            stmt = conn.createStatement();
            rowCount = stmt.executeUpdate(sqlStatement);
        } catch (SQLException e) {
            System.out.println("3");
            System.out.println("SQL Exception: " + e.getMessage());
            System.out.println("SQL State: " + e.getSQLState());
            System.out.println("Vendor Error: " + e.getErrorCode());
        }
        return rowCount;
    }



    @Override
    public void close() {
        //e' buona norma chiudere la connessione aperta
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                System.out.println("4");
                System.out.println("SQL Exception: " + e.getMessage());
                System.out.println("SQL State: " + e.getSQLState());
                System.out.println("Vendor Error: " + e.getErrorCode());
            }
            rs = null;
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                System.out.println("5");
                System.out.println("SQL Exception: " + e.getMessage());
                System.out.println("SQL State: " + e.getSQLState());
                System.out.println("Vendor Error: " + e.getErrorCode());
            }
            stmt = null;
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("6");
                System.out.println("SQL Exception: " + e.getMessage());
                System.out.println("SQL State: " + e.getSQLState());
                System.out.println("Vendor Error: " + e.getErrorCode());
            }
        }
    }

    @Override
    public int addFoto(File photo, String sql) {
        return 0;
    }

    public boolean isClosed() throws SQLException {
        return conn.isClosed();
    }
}