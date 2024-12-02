package com.bmiapp;

import org.mindrot.jbcrypt.BCrypt;

import java.io.File;
import java.sql.*;

public class UserDAO {
    private static final String DB_URL = "jdbc:sqlite:C:/Users/adama/Desktop/BMIcalcApp/src/main/resources/calcapp.db";

    static {
        // Initialize the SQLite Driver and test the connection
        try {
            File dbFile = new File("C:/Users/adama/Desktop/BMIcalcApp/src/main/resources/calcapp.db");
            if (!dbFile.exists()) {
                throw new RuntimeException("Database file not found at: " + dbFile.getAbsolutePath());
            }
            Class.forName("org.sqlite.JDBC");
            try (Connection conn = DriverManager.getConnection(DB_URL)) {
                System.out.println("Connection successful!");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("SQLite Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
        }
    }

    // Method to authenticate user credentials
    public static boolean authenticate(String username, String password) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            // Check if the database connection is successful
            if (conn == null) {
                System.out.println("Failed to connect to the database!");
                return false;
            }

            // Prepare and execute the SQL query
            String query = "SELECT password FROM users WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String hashedPassword = rs.getString("password");
                return BCrypt.checkpw(password, hashedPassword); // Verify the hashed password
            } else {
                System.out.println("User not found!");
            }
        } catch (SQLException e) {
            System.out.println("SQL Error during authentication!");
            e.printStackTrace();
        }
        return false; // Authentication failed
    }

    // Method to register a new user
    public static void registerUser(String username, String password) {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            // Check if the database connection is successful
            if (conn == null) {
                System.out.println("Failed to connect to the database!");
                return;
            }

            // Insert the new user into the database
            String query = "INSERT INTO users (username, password) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, hashedPassword);
            stmt.executeUpdate();
            System.out.println("User registered successfully!");
        } catch (SQLException e) {
            if (e.getMessage().contains("UNIQUE constraint failed")) {
                System.out.println("Username already exists!");
            } else {
                System.out.println("SQL Error during user registration!");
            }
            e.printStackTrace();
        }
    }
}
