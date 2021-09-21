//estrarrà il comando da mandare al DB
package DbInterface;

import java.sql.ResultSet;

public interface IDbOperation {
    ResultSet execute();  //operazioni di lettura
}
