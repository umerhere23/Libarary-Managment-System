/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library;

import java.sql.*;
import javax.swing.JOptionPane;

public class BookDetailsRetriever{
    
    private Connection conn;
    
    // Constructor to establish the database connection
    public BookDetailsRetriever() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/Library";
        String user = "root";
        String password = "";
        conn = DriverManager.getConnection(url, user, password);
    }
    
    // Method to retrieve book details
    public void retrieveBookDetails(String title, String type) {
        try {
            // Prepare the SQL query
            String sql = "SELECT * FROM books WHERE title = ? AND Type = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, title);
            pstmt.setString(2, type);
            
            // Execute the query and print the book details
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                JOptionPane.showMessageDialog(null, "Book ID: " + rs.getInt("book_id") + "\n"
                                     + "Title: " + rs.getString("title") + "\n"
                                     + "Author: " + rs.getString("author") + "\n"
                                     + "Publisher: " + rs.getString("publisher") + "\n"
                                     + "Publication Year: " + rs.getInt("publication_year") + "\n"
                                     + "Type: " + rs.getString("Type"));

            }
            
            // Close the result set and statement
            rs.close();
            pstmt.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Method to close the database connection
    public void closeConnection() throws SQLException {
        conn.close();
    }
    
}
