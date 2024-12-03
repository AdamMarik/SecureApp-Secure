package com.bmiapp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InputDAO {
    private static final String DB_URL = "jdbc:sqlite:C:/Users/adama/Desktop/BMIcalcApp/src/main/resources/calcapp.db";

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public void addInput(Input input) throws SQLException {
        String sql = "INSERT INTO Inputs (user_id, weight, height, age, gender, bodyfat, bmi) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, input.getUserId());
            pstmt.setDouble(2, input.getWeight());
            pstmt.setDouble(3, input.getHeight());
            pstmt.setInt(4, input.getAge());
            pstmt.setString(5, input.getGender());
            pstmt.setDouble(6, input.getBodyFatPercentage());
            pstmt.setDouble(7, input.getBmi());
            pstmt.executeUpdate();
        }
    }

    public List<Input> getUserInputs(int userId) throws SQLException {
        List<Input> inputs = new ArrayList<>();
        String sql = "SELECT * FROM Inputs WHERE user_id = ?";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    inputs.add(mapInput(rs));
                }
            }
        }
        return inputs;
    }

    public void deleteInput(int userId, String dateAsString) throws SQLException {
        String sql = "DELETE FROM Inputs WHERE user_id = ? AND datetime(date) = datetime(?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setString(2, dateAsString); // Use the same string format stored in DB
            int affectedRows = pstmt.executeUpdate();
            System.out.println("Deleted " + affectedRows + " row(s).");
        }
    }

    public static List<Input> searchInputs(int userId, String query) throws SQLException {
        List<Input> inputs = new ArrayList<>();
        String sql = "SELECT * FROM Inputs WHERE user_id = ? AND (CAST(weight AS TEXT) LIKE ? OR CAST(height AS TEXT) LIKE ?)";

        String likeQuery = "%" + query + "%";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setString(2, likeQuery);
            pstmt.setString(3, likeQuery);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    inputs.add(mapInput(rs));
                }
            }
        }
        return inputs;
    }

    private static Input mapInput(ResultSet rs) throws SQLException {
        return new Input(
                rs.getInt("id"),
                rs.getInt("user_id"),
                rs.getDouble("weight"),
                rs.getDouble("height"),
                rs.getInt("age"),
                rs.getString("gender"),
                rs.getTimestamp("date"),
                rs.getDouble("bodyfat"),
                rs.getDouble("bmi")
        );
    }
}