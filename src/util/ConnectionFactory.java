package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static Properties p = new Properties();
    private static String username;
    private static String pass;
    private static String connString;

    public static Connection getConnection() {
        try {
            InputStream is = new FileInputStream("dataConfig.properties");
            p.load(is);
            username = p.getProperty("DB_USER");
            pass = p.getProperty("DB_PASS");
            connString = p.getProperty("DB_URL");

            return DriverManager.getConnection(connString, username, pass);
        }
        catch (SQLException | IOException e) {
            throw new RuntimeException("Error connecting to database", e);
        }

    }
}
