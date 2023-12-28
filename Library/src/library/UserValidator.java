/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library;

/**
 *
 * @author Administrator
 */
import java.sql.*;

public class UserValidator {

    public static boolean validateUser(String name, String pass) {
        // Set the database credentials
        String url = "jdbc:mysql://localhost:3306/Library";
        String user = "root";
        String password = "";

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Create a connection to the database
            Connection conn = DriverManager.getConnection(url, user, password);

            // Prepare a statement to query the database for the user's name and id
            String sql = "SELECT * FROM users WHERE username=? AND password=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, pass);

            // Execute the query and check if any results were returned
            ResultSet result = statement.executeQuery();
            boolean isValid = result.next();

            // Close the connection and return the result
            conn.close();
            return isValid;

        } catch (ClassNotFoundException e) {
            System.out.println("JDBC driver not found.");
        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
        }

        // If an exception occurred or no results were returned, return false
        return false;
    }
 public static boolean validateAdmin(String name, String pass) {
        // Set the database credentials
        String url = "jdbc:mysql://localhost:3306/Library";
        String user = "root";
        String password = "";

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Create a connection to the database
            Connection conn = DriverManager.getConnection(url, user, password);

            // Prepare a statement to query the database for the admin's name and id
            String sql = "SELECT * FROM admins WHERE username=? AND password=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, pass);

            // Execute the query and check if any results were returned
            ResultSet result = statement.executeQuery();
            boolean isValid = result.next();

            // Close the connection and return the result
            conn.close();
            return isValid;

        } catch (ClassNotFoundException e) {
            System.out.println("JDBC driver not found.");
        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
        }

        // If an exception occurred or no results were returned, return false
        return false;
    }
}
