package library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserPasswordRetriever {
    
    private Connection conn;
    
    // Constructor to establish the database connection
    public UserPasswordRetriever() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/Library";
        String user = "root";
        String password = "";
        conn = DriverManager.getConnection(url, user, password);
    }
    
    // Method to retrieve the user's password based on their email and username
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
