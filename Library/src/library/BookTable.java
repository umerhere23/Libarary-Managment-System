package library;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class BookTable {
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;

    public void showBooks(JTable table) {
        try {
            String url = "jdbc:mysql://localhost:3306/Library";
            String user = "root";
            String password = "";
            conn = DriverManager.getConnection(url, user, password);

            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM book");

            // Create table model
            DefaultTableModel tableModel = new DefaultTableModel();

            // Get metadata of the result set
            ResultSetMetaData metaData = rs.getMetaData();

            // Get number of columns
            int columnCount = metaData.getColumnCount();

            // Add column names to table model
            for (int i = 1; i <= columnCount; i++) {
                tableModel.addColumn(metaData.getColumnName(i));
            }

            // Add rows to table model
            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                tableModel.addRow(row);
            }

            // Set table model to JTable
            table.setModel(tableModel);

            // Close resources
            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
  public String showBorrowedBooks(String username) {
    String result = "";
    try {
        String url = "jdbc:mysql://localhost:3306/Library";
        String user = "root";
        String password = "";
        Connection conn = DriverManager.getConnection(url, user, password);
        String sql = "SELECT title, days FROM borrowedbooks WHERE username = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, username);
        ResultSet rs = pstmt.executeQuery();

        int bookCount = 0;
        int totalDays = 0;
        while (rs.next()) {
            String title = rs.getString("title");
            int days = rs.getInt("days");
            bookCount++;
            totalDays += days;
            result += "Title: " + title + ", Days: " + days + "\n";
        }

        result += "Total Books Borrowed: " + bookCount + "\n";
        result += "Total Days Borrowed: " + totalDays + "\n";

        rs.close();
        pstmt.close();
        conn.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return result+"\n";
}

}
