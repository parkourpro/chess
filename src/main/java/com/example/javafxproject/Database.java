package com.example.javafxproject;

import java.sql.*;

public class Database {
    private Connection connection;

    // Establishes the connection to the database
    public void connect(String url, String username, String password) {
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database!");
        } catch (SQLException e) {
            System.out.println("Connection failed. Error: " + e.getMessage());
        }
    }

    // Executes a query and returns the ResultSet
    public ResultSet executeQuery(String query) {
        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("Query execution failed. Error: " + e.getMessage());
        }
        return resultSet;
    }

    // Executes non-query SQL statements (INSERT, UPDATE, DELETE)
    public int executeUpdate(String query) {
        int rowsAffected = 0;
        try {
            Statement statement = connection.createStatement();
            rowsAffected = statement.executeUpdate(query);
            System.out.println("Query executed successfully. Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            System.out.println("Query execution failed. Error: " + e.getMessage());
        }
        return rowsAffected;
    }

    // Closes the connection
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connection closed.");
            }
        } catch (SQLException e) {
            System.out.println("Error in closing connection: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Database credentials
        String url = "jdbc:postgresql://localhost:5432/chess";
        String username = "postgres";
        String password = "020802";

        Database connector = new Database();
        connector.connect(url, username, password);

        // Example query execution - INSERT statements
        String insertQueryUsers = "select * from users";
        // Execute each insert query separately using executeUpdate()

        // Execute the query

        ResultSet resultSet = connector.executeQuery(insertQueryUsers);

        try {
            // Loop through the ResultSet to access each row
            while (resultSet != null && resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                String userName = resultSet.getString("user_name");
                // Extract other columns as needed
                System.out.println("User ID: " + userId + ", User Name: " + userName);
                // Print other information or perform operations
            }
        } catch (SQLException e) {
            System.out.println("Error processing ResultSet: " + e.getMessage());
        }

    }
}
