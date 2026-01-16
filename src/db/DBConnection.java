package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    // Make sure database name matches your SQL
    private static final String URL = "jdbc:mysql://localhost:3306/CollegeVisitorDB";
    private static final String USER = "root";
    private static final String PASSWORD = "_mehakgarg01";

    public static Connection getConnection() {
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.println("✅ Database Connected Successfully!");
            return con;
        } catch (Exception e) {
            System.out.println("❌ Database Connection Failed!");
            System.out.println("Error: " + e.getMessage());
            System.out.println("Please check:");
            System.out.println("1. Is MySQL running?");
            System.out.println("2. Is username/password correct?");
            System.out.println("3. Is CollegeVisitorDB created?");
            return null;
        }
    }

    // Test connection
    public static void main(String[] args) {
        Connection test = getConnection();
        if (test != null) {
            System.out.println("Connection test successful!");
        }
    }
}
