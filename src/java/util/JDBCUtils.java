package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {

    private static final String URL = "jdbc:mysql://localhost:3306/pizzadb?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    private static JDBCUtils instance;
    
    
    private JDBCUtils() {
        try {
            // Initialize the MySQL JDBC driver
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    // Singleton instance
    public static JDBCUtils getInstance() {
        if (instance == null) {
            instance = new JDBCUtils();
        }
        return instance;
    }
    
    // Method to get a connection
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
