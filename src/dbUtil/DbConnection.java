package dbUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static final String USERNAME = "dbuser";
    private static  final String PASSWORD = "dbpassword";
    private static final String CONNECTION = "jdbc:sqlite:school.sqlite";

    public static Connection getConnection() throws SQLException{
        try{
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(CONNECTION);
        }
        catch (ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }
}
