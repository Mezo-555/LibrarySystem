package librarysystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class LibraryActions {

    // Method to search for library items by keyword
    public void searchLibraryItems(String keyword) {
        LibraryDatabase data = new LibraryDatabase();
        try {
            ResultSet resultSet = data.select("LibraryItem", "title LIKE '%" + keyword + "%' OR author LIKE '%" + keyword + "%' OR category LIKE '%" + keyword + "%'");
            boolean found = false;
            while (resultSet.next()) {
                found = true;
                String itemId = resultSet.getString("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String category = resultSet.getString("category");
                String status = resultSet.getString("status");

                // Print or process the retrieved information as needed
                System.out.println("Item ID: " + itemId);
                System.out.println("Title: " + title);
                System.out.println("Author: " + author);
                System.out.println("Category: " + category);
                System.out.println("Status: " + status);
                System.out.println("---------------------------");
            }

            if (!found) {
                System.out.println("No items found matching the search criteria.");
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        } finally {
            data.disconnect();
        }
    }

    // Method to lend a library item to a user
    public void lendItem(int itemId, int userId, Date dueDate) {
        LibraryDatabase data = new LibraryDatabase();
        // Update the status of the item in the database to 'Borrowed'
        data.update("LibraryItem", "status", "Borrowed", "id = '" + itemId + "'");
        // Record the borrowing details in the database
        data.insert("BorrowingDetails", itemId, userId, new java.sql.Date(new Date().getTime()), new java.sql.Date(dueDate.getTime()), null);
        System.out.println("Item " + itemId + " lent to user " + userId + ".");
        data.disconnect();
    }

    // Method to return a borrowed library item
    public void returnItem(int itemId) {
        LibraryDatabase data = new LibraryDatabase();
        // Update the status of the item in the database to 'Available'
        data.update("LibraryItem", "status", "Available", "id = '" + itemId + "'");
        // Update the return date in the borrowing details
        data.update("BorrowingDetails", "returnDate", new java.sql.Date(new Date().getTime()), "itemId = '" + itemId + "' AND returnDate IS NULL");
        System.out.println("Item " + itemId + " returned.");
        data.disconnect();
    }
}
