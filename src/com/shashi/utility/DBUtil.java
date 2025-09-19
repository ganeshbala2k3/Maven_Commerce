package com.shashi.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBUtil {
    private static Connection conn;

    public static Connection provideConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                // Load the MySQL driver
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Create the connection and assign to static variable
                conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/shopping-cart?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
                    "root",
                    "StrongP@ssw0rd!2"
                );
                System.out.println("Database connected successfully!");
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL Driver not found!", e);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to DB!", e);
        }

        return conn;
    }

    public static void closeConnection(Connection con) {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection(ResultSet rs) {
        try {
            if (rs != null && !rs.isClosed()) rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection(PreparedStatement ps) {
        try {
            if (ps != null && !ps.isClosed()) ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
