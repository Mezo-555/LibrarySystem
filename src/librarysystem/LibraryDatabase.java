package librarysystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LibraryDatabase {

    private static final String URL = "jdbc:sqlite:C:/Users/user/Documents/NetBeansProjects/LibrarySystem/LibraryDatabase.db";
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public LibraryDatabase() {
        connect();
    }

    private void connect() {
        try {
            connection = DriverManager.getConnection(URL);
            System.out.println("Connected to the SQLite database.");
        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
        }
    }

    public void disconnect() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Disconnected from the SQLite database.");
            }
        } catch (SQLException e) {
            System.err.println("Error disconnecting from the database: " + e.getMessage());
        }
    }

    public void insert(String tableName, Object... values) {
        String query = "INSERT INTO " + tableName + " VALUES (";
        for (int i = 0; i < values.length; i++) {
            query += "?";
            if (i < values.length - 1) {
                query += ",";
            }
        }
        query += ")";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (int i = 0; i < values.length; i++) {
                preparedStatement.setObject(i + 1, values[i]);
            }
            preparedStatement.executeUpdate();
            System.out.println("Data inserted successfully into " + tableName + " table.");
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    public int getLastInsertedId() {
        String query = "SELECT last_insert_rowid()";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt(1); // Retrieve the last inserted ID
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }

        return -1; // Return -1 if no ID is found (error occurred)
    }

    public void update(String tableName, String columnName, Object newValue, String condition) {
        String query = "UPDATE " + tableName + " SET " + columnName + " = ? WHERE " + condition;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setObject(1, newValue);
            preparedStatement.executeUpdate();
            System.out.println("Column '" + columnName + "' updated successfully in " + tableName + " table.");
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    public void delete(String tableName, String condition) {
        String query = "DELETE FROM " + tableName + " WHERE " + condition;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Data deleted successfully from " + tableName + " table.");
            } else {
                System.out.println("No data found in " + tableName + " table matching the condition: " + condition);
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    public ResultSet select(String tableName, String condition) {
        if (tableName == null || tableName.isEmpty()) {
            throw new IllegalArgumentException("Table name cannot be null or empty.");
        }

        String query = "SELECT * FROM " + tableName;
        if (condition != null && !condition.isEmpty()) {
            query += " WHERE " + condition;
        }

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            handleSQLException(e);
            return null;
        }
    }

    private void handleSQLException(SQLException e) {
        System.err.println("SQL Exception: " + e.getMessage());
    }
}
