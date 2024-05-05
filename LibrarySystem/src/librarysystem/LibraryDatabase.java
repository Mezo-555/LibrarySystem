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

    public void update(String tableName, String columnName, Object newValue, String condition) {
        String query = "UPDATE " + tableName + " SET " + columnName + " = ? WHERE " + condition;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setObject(1, newValue);
            preparedStatement.executeUpdate();
            System.out.println("Data updated successfully in " + tableName + " table.");
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    public void delete(String tableName, String condition) {
        String query = "DELETE FROM " + tableName + " WHERE " + condition;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
            System.out.println("Data deleted successfully from " + tableName + " table.");
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    public ResultSet select(String tableName, String condition) {
        String query = "SELECT * FROM " + tableName + " WHERE " + condition;

        try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {
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
