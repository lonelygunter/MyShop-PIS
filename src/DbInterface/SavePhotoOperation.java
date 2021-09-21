package DbInterface;

import java.io.File;
import java.sql.ResultSet;

public class SavePhotoOperation implements IDbOperation {

    private static DbConnection conn = DbConnection.getInstance();

    String sql;
    File photo;

    public SavePhotoOperation(String sql, File photo) {
        this.sql = sql;
        this.photo = photo;
    }

    @Override
    public ResultSet execute() {
        //metodo di scrittura quindi conserviamo il numero di righe che sono state modificate che ci viene ritornato
        int i = conn.addFoto(photo, sql);
        return null;
    }
}
