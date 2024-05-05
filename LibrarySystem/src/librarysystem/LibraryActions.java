package librarysystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class LibraryActions {

    private LibraryDatabase data;

    public LibraryActions() {
        this.data = new LibraryDatabase();
    }

    // Method to search for library items by keyword
    public void searchLibraryItems(String keyword) {
        try {
            ResultSet resultSet = data.select("Library Items", "title LIKE '%" + keyword + "%' OR author LIKE '%" + keyword + "%' OR category LIKE '%" + keyword + "%'");
            if (resultSet != null) {
                while (resultSet.next()) {
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
            } else {
                System.out.println("No items found matching the search criteria.");
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        }
    }

    // Method to lend a library item to a user
    public void lendItem(String itemId, String userId, Date dueDate) {
        // Update the status of the item in the database to 'Borrowed'
        data.update("Library Item", "status", "Borrowed", "item id = '" + itemId + "'");
        // Record the borrowing details in the database
        data.insert("Borrowing details", itemId, userId, new java.sql.Date(new Date().getTime()), new java.sql.Date(dueDate.getTime()));
        System.out.println("Item " + itemId + " lent to user " + userId + ".");
    }

    // Method to return a borrowed library item
    public void returnItem(String itemId) {
        // Update the status of the item in the database to 'Available'
        data.update("Library Item", "status", "Available", "item_id = '" + itemId + "'");
        // Update the return date in the borrowing details
        data.update("Borrowing details", "return date", new java.sql.Date(new Date().getTime()), "item id = '" + itemId + "' AND return date IS NULL");
        System.out.println("Item " + itemId + " returned.");
    }

}
