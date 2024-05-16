package librarysystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class LibraryActions {

    // Method to search for library items by keyword
    public static void searchLibraryItems(DefaultTableModel tableModel, String keyword) {
        LibraryDatabase data = new LibraryDatabase();
        try {
            ResultSet resultSet = data.select("LibraryItem", "title LIKE '%" + keyword + "%' OR author LIKE '%" + keyword + "%' OR category LIKE '%" + keyword + "%'");
            tableModel.setRowCount(0); // Clear previous search results
            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();
                row.add(resultSet.getString("id"));
                row.add(resultSet.getString("type"));
                row.add(resultSet.getString("title"));
                row.add(resultSet.getString("author"));
                row.add(resultSet.getString("category"));
                row.add(resultSet.getString("status"));
                tableModel.addRow(row);
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        } finally {
            data.disconnect();
        }
    }

    // Method to lend a library item to a user and return a message indicating success or failure
    public String lendItem(int itemId, int userId, Date dueDate) {
        LibraryDatabase data = new LibraryDatabase();
        try {
            // Update the status of the item in the database to 'Borrowed'
            data.update("LibraryItem", "status", "Borrowed", "id = '" + itemId + "'");
            // Record the borrowing details in the database
            data.insert("BorrowingDetails", itemId, userId, new java.sql.Date(new Date().getTime()), new java.sql.Date(dueDate.getTime()), null);
            data.disconnect();
            return "Item " + itemId + " lent to user successfully.";
        } catch (Exception e) {
            return "Failed to lend item " + itemId + ": " + e.getMessage();
        } finally {
            data.disconnect();
        }
    }

// Method to return a borrowed library item and return a message indicating success or failure
    public String returnItem(int itemId) {
        LibraryDatabase data = new LibraryDatabase();
        try {
            // Update the status of the item in the database to 'Available'
            data.update("LibraryItem", "status", "Available", "id = '" + itemId + "'");
            // Update the return date in the borrowing details
            data.update("BorrowingDetails", "returnDate", new java.sql.Date(new Date().getTime()), "itemId = '" + itemId + "' AND returnDate IS NULL");
            data.disconnect();
            return "Item " + itemId + " returned.";
        } catch (Exception e) {
            return "Failed to return item " + itemId + ": " + e.getMessage();
        } finally {
            data.disconnect();
        }
    }

}
