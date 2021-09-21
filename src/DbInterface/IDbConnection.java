package DbInterface;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface IDbConnection {
    ResultSet executeQuery(String sqlStatement);
    int executeUpdate(String sqlStatement); //per le query UPDATE, DELETE, ecc...
    void close();

    int addFoto(File photo, String sql);
    
    public boolean isClosed() throws SQLException;
}
