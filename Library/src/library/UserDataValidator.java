
package library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDataValidator {
    
    private Connection conn;
    
    // Constructor to establish the database connection
    public UserDataValidator() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/Library";
        String user = "root";
        String password = "";
        conn = DriverManager.getConnection(url, user, password);
    }
    
    // Method to validate user data
    public boolean validateUserData(String name, String password, String email, String address, String idNumber, String username) {
        boolean isInserted = false;
        try {
            // Prepare the SQL query
            String sql = "INSERT INTO users (name, password, email, address, IDnumber, username) VALUES (?, ?, ?,  ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, password);
            pstmt.setString(3, email);
  
            pstmt.setString(4, address);
            pstmt.setString(5, idNumber);
            pstmt.setString(6, username);
            
            // Execute the query and check if a matching record exists
             int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                isInserted = true; /// A matching record was found
            
            }
            // Close the result set and statement
            pstmt.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isInserted;
    }
     public String retrieveUserPassword(String email, String username) {
        String password = null;
        try {
            // Prepare the SQL query
            String sql = "SELECT password FROM users WHERE email = ? AND username = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, username);
            
            // Execute the query and retrieve the password
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                password = rs.getString("password");
            }
            
            // Close the result set and statement
            rs.close();
            pstmt.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return password;
    }
    
 
    
    // Method to close the database connection
    public void closeConnection() throws SQLException {
        conn.close();
    }
    
}