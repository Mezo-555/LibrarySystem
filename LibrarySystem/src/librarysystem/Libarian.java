package librarysystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class Libarian {

    private int id;
    private String name, contactInfo, password;

    public Libarian(int id, String name, String contactInfo, String password) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
        this.password = password;
    }

    public Libarian() {
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
        if (!(obj instanceof Libarian)) {
            return false;
        }
        Libarian libarian = (Libarian) obj;
        return id == libarian.id
                && Objects.equals(name, libarian.name)
                && Objects.equals(contactInfo, libarian.contactInfo)
                && Objects.equals(password, libarian.password);
    }

    public void addLibraryItem(LibraryItem item) {
        LibraryDatabase data = new LibraryDatabase();
        if (item instanceof Book) {
            Book book = (Book) item;
            data.insert("Book", book.getId(), book.getISBN(), book.getPublishYear(), book.getNumberOfPage(), book.getPublisher(), book.getEdition());
            if (item instanceof EBook) {
                EBook ebook = (EBook) item;
                data.insert("E-book", ebook.getId(), ebook.getISBN(), ebook.getFileFormat(), ebook.getFileSize());
            }
        } else if (item instanceof Journal) {
            Journal journal = (Journal) item;
            data.insert("Journal", journal.getId(), journal.getISSN(), journal.getPublisher(), journal.getPublicationFrequency());
        } else if (item instanceof DVD) {
            DVD dvd = (DVD) item;
            data.insert("DVD", dvd.getId(), dvd.getUPC(), dvd.getSubtitle(), dvd.getRunningTime(), dvd.getReleaseYear());
        } else if (item instanceof Newspaper) {
            Newspaper newspaper = (Newspaper) item;
            data.insert("Newspaper", newspaper.getId(), newspaper.getPublisher(), newspaper.getPublicationFrequency());
        }

        // Insert data into the LibraryItem table
        data.insert("LibraryItem", item.getId(), item.getTitle(), item.getAuthor(), item.getCategory(), item.getS().toString());

        // Add support for other types of items here
        data.disconnect();
        System.out.println("Library item added successfully!");
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
        System.out.println("Library item removed successfully!");
    }

    public void updateLibraryItem(LibraryItem item) {
        LibraryDatabase data = new LibraryDatabase();

        if (item instanceof Book) {
            Book book = (Book) item;
            data.update("Book", "ISBN", book.getISBN(), "id = " + book.getId());
            data.update("Book", "number of page", book.getNumberOfPage(), "id = " + book.getId());
            data.update("Book", "publish year", book.getPublishYear(), "id = " + book.getId());
            data.update("Book", "publisher", book.getPublisher(), "id = " + book.getId());
            data.update("Book", "edition", book.getEdition(), "id = " + book.getId());
            if (item instanceof EBook) {
                EBook ebook = (EBook) item;
                data.update("E-book", "file format", ebook.getFileFormat(), "id = " + ebook.getId());
                data.update("E-book", "file size", ebook.getFileSize(), "id = " + ebook.getId());
            }
        } else if (item instanceof Journal) {
            Journal journal = (Journal) item;
            data.update("Journal", "publication frequency", journal.getPublicationFrequency(), "id = " + journal.getId());
            data.update("Journal", "publisher", journal.getPublisher(), "id = " + journal.getId());
        } else if (item instanceof DVD) {
            DVD dvd = (DVD) item;
            data.update("DVD", "UPC", dvd.getUPC(), "id = " + dvd.getId());
            data.update("DVD", "subtitle", dvd.getSubtitle(), "id = " + dvd.getId());
            data.update("DVD", "running time", dvd.getRunningTime(), "id = " + dvd.getId());
            data.update("DVD", "release year", dvd.getReleaseYear(), "id = " + dvd.getId());
        } else if (item instanceof Newspaper) {
            Newspaper newspaper = (Newspaper) item;
            data.update("Newspaper", "publication frequency", newspaper.getPublicationFrequency(), "id = " + newspaper.getId());
            data.update("Newspaper", "publisher", newspaper.getPublisher(), "id = " + newspaper.getId());
        }

        data.disconnect();
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
                data.update("User", "username", user.getName(), "id = " + user.getId());
                data.update("User", "password", user.getPassword(), "id = " + user.getId());
                data.update("User", "contact_info", user.getContactInfo(), "id = " + user.getId());
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

    // Method to track borrowings
    public void trackBorrowings(ArrayList<BorrowingDetails> borrowings) {
        // Iterate over the list of borrowing details
        for (BorrowingDetails borrowing : borrowings) {
            System.out.println("Item ID: " + borrowing.getItemID());
            System.out.println("User ID: " + borrowing.getUserID());
            System.out.println("Borrow Date: " + borrowing.getBorrowDate());
            System.out.println("Due Date: " + borrowing.getDueDate());
            System.out.println("Return Date: " + borrowing.getReturnDate());
            System.out.println("---------------------------");
        }
    }

    // Method to display users from the database
    public void displayUsers() {
        LibraryDatabase data = new LibraryDatabase();

        try {
            // Retrieve user data from the database
            ResultSet resultSet = data.select("users", "");

            // Iterate through the result set and display user information
            while (resultSet.next()) {
                String userId = resultSet.getString("id");
                String username = resultSet.getString("name");
                String pass = resultSet.getString("password");
                String info = resultSet.getString("contact info");

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
        LibraryDatabase libraryDatabase = new LibraryDatabase();

        try {
            // Retrieve library items data from the database
            ResultSet resultSet = libraryDatabase.select("Library Items", "");

            // Iterate through the result set and display library item information
            while (resultSet.next()) {
                String itemId = resultSet.getString("id");
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
            libraryDatabase.disconnect();
        }
    }
}
