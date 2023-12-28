/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library;
import java.sql.*;

/**
 *
 * @author Administrator
 */
public class Admin {
    public String getAllBorrowedBooks() {
    StringBuilder sb = new StringBuilder();

    try {
        String url = "jdbc:mysql://localhost:3306/Library";
        String user = "root";
        String password = "";
        Connection conn = DriverManager.getConnection(url, user, password);
        String sql = "SELECT username, title, days, date FROM borrowedbooks";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            String username = rs.getString("username");
            String title = rs.getString("title");
            int days = rs.getInt("days");
            Date date = rs.getDate("date");

            sb.append("Username: " + username + "\n");
            sb.append("Title: " + title + "\n");
            sb.append("Days: " + days + "\n");
            sb.append("Date: " + date + "\n");
            sb.append("-----------------------\n");
        }

        rs.close();
        pstmt.close();
        conn.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }

    return sb.toString();
}
public String showAllUsers() {
    StringBuilder result = new StringBuilder();
    try {
        String url = "jdbc:mysql://localhost:3306/Library";
        String user = "root";
        String password1 = "";
        Connection conn = DriverManager.getConnection(url, user, password1);
        String sql = "SELECT * FROM users";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("user_id");

            String username = rs.getString("username");
            String password = rs.getString("password");
            String name = rs.getString("name");
            String email = rs.getString("email");
            String IDnumber1 = rs.getString("IDnumber");
            result.append("User Id: ").append(id).append("\n");

            result.append("Username: ").append(username).append("\n");
            result.append("Password: ").append(password).append("\n");
            result.append("Name: ").append(name).append("\n");
            result.append("Email: ").append(email).append("\n");
            result.append("ID number: ").append(IDnumber1).append("\n");
            result.append("-----------------------\n");
        }

        rs.close();
        pstmt.close();
        conn.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return result.toString();
}
public String showAllBooks() {
    StringBuilder result = new StringBuilder();
    try {
        String url = "jdbc:mysql://localhost:3306/Library";
        String user = "root";
        String password = "";
        Connection conn = DriverManager.getConnection(url, user, password);
        String sql = "SELECT * FROM books";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
                        int id = rs.getInt("book_id");
            String title = rs.getString("title");
            String author = rs.getString("author");
            String year = rs.getString("publication_year");
            String isbn = rs.getString("publisher");
            String quantity = rs.getString("Type");

            result.append("Book Id: ").append(id).append("\n");
            result.append("Title: ").append(title).append("\n");
            result.append("Author: ").append(author).append("\n");
            result.append("Year: ").append(year).append("\n");
            result.append("ISBN: ").append(isbn).append("\n");
            result.append("Quantity: ").append(quantity).append("\n");
            result.append("-----------------------\n");
        }

        rs.close();
        pstmt.close();
        conn.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return result.toString();
}
public void addBook(String bookId, String title, String author, String publisher, String publicationYear, String type) {
    try {
        String url = "jdbc:mysql://localhost:3306/Library";
        String user = "root";
        String password = "";
        Connection conn = DriverManager.getConnection(url, user, password);
        String sql = "INSERT INTO books (book_id, title, author, publisher, publication_year, type) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, bookId);
        pstmt.setString(2, title);
        pstmt.setString(3, author);
        pstmt.setString(4, publisher);
        pstmt.setString(5, publicationYear);
        pstmt.setString(6, type);
        pstmt.executeUpdate();

        pstmt.close();
        conn.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}
public boolean insertUser(String name, String password,  String email, String address, String idNumber, String username) {
    boolean success = false;
    try {
        String url = "jdbc:mysql://localhost:3306/Library";
        String user = "root";
        String dbPassword = "";
        Connection conn = DriverManager.getConnection(url, user, dbPassword);
        String sql = "INSERT INTO users (name, password,  email, address, IDnumber, username) VALUES ( ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, name);
        pstmt.setString(2, password);
        pstmt.setString(3, email);
        pstmt.setString(4, address);
        pstmt.setString(5, idNumber);
        pstmt.setString(6, username);

        int rows = pstmt.executeUpdate();
        if (rows > 0) {
            success = true;
        }

        pstmt.close();
        conn.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return success;
}
public void deleteBook(String bookId) {
    try {
        String url = "jdbc:mysql://localhost:3306/Library";
        String user = "root";
        String password = "";
        Connection conn = DriverManager.getConnection(url, user, password);
        
        String sql = "DELETE FROM books WHERE book_id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, bookId);
        
        int rowsAffected = pstmt.executeUpdate();
        System.out.println(rowsAffected + " rows deleted.");
        
        pstmt.close();
        conn.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}
public void deleteUser(String UID) {
    try {
        String url = "jdbc:mysql://localhost:3306/Library";
        String user = "root";
        String password = "";
        Connection conn = DriverManager.getConnection(url, user, password);
        
        String sql = "DELETE FROM users WHERE user_id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, UID);
        
        int rowsAffected = pstmt.executeUpdate();
        System.out.println(rowsAffected + " rows deleted.");
        
        pstmt.close();
        conn.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}
}
