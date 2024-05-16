package librarysystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Librarian {

    private int id;
    private String name, contactInfo, password;

    public Librarian(int id, String name, String contactInfo, String password) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
        this.password = password;
    }

    public Librarian() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Libarian{" + "id=" + id + ", name=" + name + ", contactInfo=" + contactInfo + ", password=" + password + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Librarian)) {
            return false;
        }
        Librarian libarian = (Librarian) obj;
        return id == libarian.id
                && Objects.equals(name, libarian.name)
                && Objects.equals(contactInfo, libarian.contactInfo)
                && Objects.equals(password, libarian.password);
    }

    public String addLibraryItem(LibraryItem item) {
        LibraryDatabase data = new LibraryDatabase();
        String result = "";

        // Insert data into the LibraryItem table to generate the id
        data.insert("LibraryItem", null, item.getTitle(), item.getAuthor(), item.getCategory(), item.getS().toString());

        // Retrieve the generated id from the LibraryItem table 
        item.setId(data.getLastInsertedId());

        try {
            if (item instanceof Book) {
                Book book = (Book) item;
                // Insert the record into the Book table, including the libraryItemId as a foreign key
                data.insert("Book", book.getId(), book.getISBN(), book.getPublishYear(), book.getNumberOfPage(), book.getPublisher(), book.getEdition());
                if (item instanceof EBook) {
                    EBook ebook = (EBook) item;
                    // Insert the record into the E-book table, including the libraryItemId as a foreign key
                    data.insert("E-book", ebook.getId(), ebook.getISBN(), ebook.getFileFormat(), ebook.getFileSize());
                }
            } else if (item instanceof Journal) {
                Journal journal = (Journal) item;
                // Insert the record into the Journal table, including the libraryItemId as a foreign key
                data.insert("Journal", journal.getId(), journal.getISSN(), journal.getPublisher(), journal.getPublicationFrequency());
            } else if (item instanceof DVD) {
                DVD dvd = (DVD) item;
                // Insert the record into the DVD table, including the libraryItemId as a foreign key
                data.insert("DVD", dvd.getId(), dvd.getUPC(), dvd.getSubtitle(), dvd.getRunningTime(), dvd.getReleaseYear());
            } else if (item instanceof Newspaper) {
                Newspaper newspaper = (Newspaper) item;
                // Insert the record into the Newspaper table, including the libraryItemId as a foreign key
                data.insert("Newspaper", newspaper.getId(), newspaper.getPublisher(), newspaper.getPublicationFrequency());
            }

            // Disconnect from the database
            data.disconnect();
            result = "Library item added successfully!";
        } catch (Exception e) {
            result = "Error occurred while adding library item: " + e.getMessage();
        }

        return result;
    }

    // Method to remove library item
    public void removeLibraryItem(LibraryItem item) {
        LibraryDatabase data = new LibraryDatabase();

        if (item instanceof Book) {
            Book book = (Book) item;
            data.delete("Book", "id = " + book.getId());
            if (item instanceof EBook) {
                EBook ebook = (EBook) item;
                data.delete("Ebook", "id = " + ebook.getId());
            }
        } else if (item instanceof Journal) {
            Journal journal = (Journal) item;
            data.delete("Journal", "id = " + journal.getId());
        } else if (item instanceof DVD) {
            DVD dvd = (DVD) item;
            data.delete("DVD", "id = " + dvd.getId());
        } else if (item instanceof Newspaper) {
            Newspaper newspaper = (Newspaper) item;
            data.delete("Newspaper", "id = " + newspaper.getId());
        }

        data.delete("LibraryItem", "id = " + item.getId());

        // Add support for other types of items here
        data.disconnect();
    }

    public void updateLibraryItem(LibraryItem item) {
        LibraryDatabase data = new LibraryDatabase();
        List<String> updatedTables = new ArrayList<>(); // To store the names of updated tables

        // Update common attributes in the LibraryItem table
        data.update("LibraryItem", "title", item.getTitle(), "id = " + item.getId());
        data.update("LibraryItem", "author", item.getAuthor(), "id = " + item.getId());
        data.update("LibraryItem", "category", item.getCategory(), "id = " + item.getId());
        data.update("LibraryItem", "status", item.getS().toString(), "id = " + item.getId());
        updatedTables.add("LibraryItem");

        // Update specific attributes in the corresponding item type tables
        if (item instanceof Book) {
            Book book = (Book) item;
            data.update("Book", "ISBN", book.getISBN(), "id = " + book.getId());
            data.update("Book", "numberOfPage", book.getNumberOfPage(), "id = " + book.getId());
            data.update("Book", "publishYear", book.getPublishYear(), "id = " + book.getId());
            data.update("Book", "publisher", book.getPublisher(), "id = " + book.getId());
            data.update("Book", "edition", book.getEdition(), "id = " + book.getId());
            updatedTables.add("Book");
            if (item instanceof EBook) {
                EBook ebook = (EBook) item;
                data.update("E-book", "file format", ebook.getFileFormat(), "id = " + ebook.getId());
                data.update("E-book", "file size", ebook.getFileSize(), "id = " + ebook.getId());
                updatedTables.add("E-book");
            }
        } else if (item instanceof Journal) {
            Journal journal = (Journal) item;
            data.update("Journal", "publication frequency", journal.getPublicationFrequency(), "id = " + journal.getId());
            data.update("Journal", "publisher", journal.getPublisher(), "id = " + journal.getId());
            updatedTables.add("Journal");
        } else if (item instanceof DVD) {
            DVD dvd = (DVD) item;
            data.update("DVD", "UPC", dvd.getUPC(), "id = " + dvd.getId());
            data.update("DVD", "subtitle", dvd.getSubtitle(), "id = " + dvd.getId());
            data.update("DVD", "running time", dvd.getRunningTime(), "id = " + dvd.getId());
            data.update("DVD", "release year", dvd.getReleaseYear(), "id = " + dvd.getId());
            updatedTables.add("DVD");
        } else if (item instanceof Newspaper) {
            Newspaper newspaper = (Newspaper) item;
            data.update("Newspaper", "publication frequency", newspaper.getPublicationFrequency(), "id = " + newspaper.getId());
            data.update("Newspaper", "publisher", newspaper.getPublisher(), "id = " + newspaper.getId());
            updatedTables.add("Newspaper");
        }

        // Disconnect from the database
        data.disconnect();

        // Print the names of the updated tables
        System.out.println("Updated tables: " + updatedTables);
        System.out.println("Library item updated successfully!");
    }

    // Method to manage user accounts
    public void manageUser(User user, String action) {
        LibraryDatabase data = new LibraryDatabase();

        switch (action.toLowerCase()) {
            case "add":
                data.insert("User", user.getId(), user.getName(), user.getPassword(), user.getContactInfo());
                System.out.println("User added successfully!");
                break;
            case "update":
                data.update("User", "name", user.getName(), "id = " + user.getId());
                data.update("User", "password", user.getPassword(), "id = " + user.getId());
                data.update("User", "contactInfo", user.getContactInfo(), "id = " + user.getId());
                System.out.println("User updated successfully!");
                break;
            case "remove":
                data.delete("User", "id = " + user.getId());
                System.out.println("User removed successfully!");
                break;
            default:
                System.out.println("Invalid action.");
        }

        data.disconnect();
    }

    public User getUser(int userId) {
        LibraryDatabase data = new LibraryDatabase();
        User user = null;
        String condition = "id = " + userId;
        ResultSet resultSet = data.select("User", condition);

        try {
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                String contactInfo = resultSet.getString("contactInfo");

                // Create a User object using the retrieved data
                user = new User(userId, name, password, contactInfo);
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                System.err.println("SQL Exception: " + e.getMessage());
            }
        }
        data.disconnect();
        return user;
    }

    public String login(String username, String password) {
        // Connect to the database
        LibraryDatabase database = new LibraryDatabase();

        try {
            // Check if the username and password match in the database
            ResultSet resultSet = database.select("User", "name = '" + username + "' AND password = '" + password + "'");

            // If there is a matching record, login is successful
            if (resultSet.next()) {
                return "Login successful!";
            } else {
                return "Invalid username or password. Please try again.";
            }
        } catch (SQLException e) {
            return "SQL Exception: " + e.getMessage();
        } finally {
            // Disconnect from the database
            database.disconnect();
        }
    }

    public boolean userIsLibrarian(String username) {
        LibraryDatabase database = new LibraryDatabase();
        boolean isLibrarian = false;
        try {
            // Check if user exists in the User table
            ResultSet userResultSet = database.select("User", "name = '" + username + "'");
            if (userResultSet.next()) {
                int userId = userResultSet.getInt("id");

                // Check if the same ID exists in the Librarian table
                ResultSet librarianResultSet = database.select("Librarian", "id = " + userId);
                if (librarianResultSet.next()) {
                    // User ID exists in the Librarian table
                    isLibrarian = true;
                }
            }
        } catch (SQLException e) {
            e.getMessage();
        } finally {
            // Disconnect from the database
            database.disconnect();
        }
        return isLibrarian; // Return true if user is a librarian, false otherwise
    }

    // Method to track borrowings
    public void trackBorrowings() {
        LibraryDatabase data = new LibraryDatabase();

        try {
            // Retrieve borrowing details data from the database
            ResultSet resultSet = data.select("BorrowingDetails", "");

            // Iterate through the result set and display borrowing details
            while (resultSet.next()) {
                int itemId = resultSet.getInt("itemID");
                int userId = resultSet.getInt("userID");
                Date borrowDate = resultSet.getDate("borrowDate");
                Date dueDate = resultSet.getDate("dueDate");
                Date returnDate = resultSet.getDate("returnDate");

                System.out.println("Item ID: " + itemId);
                System.out.println("User ID: " + userId);
                System.out.println("Borrow Date: " + borrowDate);
                System.out.println("Due Date: " + dueDate);
                System.out.println("Return Date: " + returnDate);
                System.out.println("---------------------------");
            }

            // Close the result set
            resultSet.close();
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        } finally {
            // Disconnect from the database
            data.disconnect();
        }
    }

    // Method to display users from the database
    public void displayUsers() {
        LibraryDatabase data = new LibraryDatabase();

        try {
            // Retrieve user data from the database
            ResultSet resultSet = data.select("User", null);

            // Iterate through the result set and display user information
            while (resultSet.next()) {
                String userId = resultSet.getString("id");
                String username = resultSet.getString("name");
                String pass = resultSet.getString("password");
                String info = resultSet.getString("contactInfo");

                System.out.println("User ID: " + userId);
                System.out.println("Username: " + username);
                System.out.println("password: " + pass);
                System.out.println("Contact Info: " + info);
                System.out.println("---------------------------");
            }

            // Close the result set
            resultSet.close();
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        } finally {
            // Disconnect from the database
            data.disconnect();
        }
    }

    // Method to display library items from the database
    public void displayLibraryItems() {
        LibraryDatabase data = new LibraryDatabase();

        try {
            // Retrieve library items data from the database
            ResultSet resultSet = data.select("LibraryItem", null);

            // Iterate through the result set and display library item information
            while (resultSet.next()) {
                int itemId = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String category = resultSet.getString("category");
                String status = resultSet.getString("status");
                // Add more attributes as needed

                System.out.println("Item ID: " + itemId);
                System.out.println("Title: " + title);
                System.out.println("Author: " + author);
                System.out.println("Category: " + category);
                System.out.println("Status: " + status);
                // Print more attributes as needed
                System.out.println("---------------------------");
            }

            // Close the result set
            resultSet.close();
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        } finally {
            // Disconnect from the database
            data.disconnect();
        }
    }
}
