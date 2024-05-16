package librarysystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class User {

    private int id;
    private String name, contactInfo, password;

    public User(int id, String name, String contactInfo, String password) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
        this.password = password;
    }

    public User() {
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
        return "User{" + "id=" + id + ", name=" + name + ", contactInfo=" + contactInfo + ", password=" + password + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof User)) {
            return false;
        }
        User user = (User) obj;
        return id == user.id
                && Objects.equals(name, user.name)
                && Objects.equals(contactInfo, user.contactInfo)
                && Objects.equals(password, user.password);
    }

    public String register(String name, String password, String contactInfo) {
        LibraryDatabase data = new LibraryDatabase();

        try {
            // Check if the username already exists
            ResultSet resultSet = data.select("User", "name = '" + name + "'");
            if (resultSet.next()) {
                // Username already exists, return failure message
                return "User registration failed: Username already exists";
            } else {
                // Insert the user into the database
                data.insert("User", null, name, password, contactInfo);
                // Disconnect from the database
                data.disconnect();
                return "User registered successfully!";
            }
        } catch (SQLException e) {
            // If an SQL exception occurs, return failure message
            return "User registration failed: " + e.getMessage();
        }
    }

    public void updateDetails(String newUsername, String newPassword, String contactInfo) {
        LibraryDatabase data = new LibraryDatabase();
        data.update("User", "name", newUsername, "id = " + id);
        data.update("User", "password", newPassword, "id = " + id);
        data.update("User", "contactInfo", contactInfo, "id = " + id);
        data.disconnect();
        System.out.println("User details updated successfully!");
    }
}
