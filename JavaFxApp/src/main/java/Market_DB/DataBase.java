package Market_DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {

    // pord addrec Postgre
    private static final String URL = "jdbc:postgresql://localhost:5432/marketplace";
    private static final String USER = "market_user";
    private static final  String  PASSWORD = "market_pass";


    public static Connection getConnection() throws SQLException {
        try{
            return DriverManager.getConnection(URL,USER,PASSWORD);
        }
        catch (Exception e){
            throw new RuntimeException("Sorry DataBase connection error: " +e.getMessage());
        }
    }

}
