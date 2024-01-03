package com.example.javafxproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    public static void main(String[] args) {
        // JDBC URL for PostgreSQL database
        String jdbcURL = "jdbc:postgresql://localhost:5432/chess";
        String username = "postgres";
        String password = "020802";

        try {
            // Load the JDBC driver
            Class.forName("org.postgresql.Driver");

            // Connect to the database
            Connection connection = DriverManager.getConnection(jdbcURL, username, password);

            if (connection != null) {
                System.out.println("Connected to the PostgreSQL database!");
                // Perform database operations here
                // Remember to close the connection when done
                connection.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
