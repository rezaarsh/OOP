package com.app.dao;

import com.app.config.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    
    // Method untuk mengecek login
    public boolean cekLogin(String username, String password) {
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean isLoginSuccessful = false;
        
        // Query SQL untuk mencari user
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        
        try { // Exception Handling
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            
            rs = stmt.executeQuery();
            
            // Jika data ditemukan (rs.next() bernilai true), berarti login sukses
            if (rs.next()) {
                isLoginSuccessful = true;
            }
        } catch (SQLException e) {
            System.err.println("Error saat login: " + e.getMessage());
        } finally {
            // Menutup statement dan result set untuk mencegah memory leak
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return isLoginSuccessful;
    }
}