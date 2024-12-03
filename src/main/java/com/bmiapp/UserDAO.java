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
    public static Object authenticate(String username, String password) {
        Integer userId = null;
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            // Check if the database connection is successful
            if (conn == null) {
                System.out.println("Failed to connect to the database!");
                return null;
            }

            // Prepare and execute the SQL query
            String query = "SELECT id, password FROM users WHERE username = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    String hashedPassword = rs.getString("password");
                    if (BCrypt.checkpw(password, hashedPassword)) {
                        // If password matches, retrieve the user ID
                        userId = rs.getInt("id");
                    } else {
                        System.out.println("Password does not match!");
                    }
                } else {
                    System.out.println("User not found!");
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL Error during authentication!");
            e.printStackTrace();
        }
        return userId; // Return the user ID if authentication is successful, otherwise null
    }

    // Method to register a new user
    private static boolean usernameExists(String username) throws SQLException {
        String sql = "SELECT 1 FROM users WHERE username = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    public static void registerUser(String username, String password) throws Exception {
        if (usernameExists(username)) {
            throw new Exception("Username already exists!");
        }

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)")) {
            stmt.setString(1, username);
            stmt.setString(2, hashedPassword);
            stmt.executeUpdate();
            System.out.println("User registered successfully!");
        } catch (SQLException e) {
            System.out.println("SQL Error during user registration!");
            e.printStackTrace();
            throw e; // rethrow any SQL exceptions that occur
        }
    }

    public static int getUserIdByUsername(String username) throws SQLException {
        int userId = -1; // Default value if not found
        String sql = "SELECT id FROM users WHERE username = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    userId = rs.getInt("id");
                }
            }
        }
        return userId;
    }
}
