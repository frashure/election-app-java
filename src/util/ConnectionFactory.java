package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String USERNAME = System.getenv("DB_USER");
    private static final String PASS = System.getenv("DB_PASS");
    private static final String CONN_STRING = System.getenv("DB_URL");

    public static Connection getConnection() {
        try {
        return DriverManager.getConnection(CONN_STRING, USERNAME, PASS);
        }
        catch (SQLException e) {
            throw new RuntimeException("Error connecting to database", e);
        }

    }
}
