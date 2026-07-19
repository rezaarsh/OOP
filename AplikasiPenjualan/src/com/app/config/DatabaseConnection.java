package com.app.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // Menggunakan pola Singleton agar koneksi tidak terbuka berkali-kali
    private static Connection koneksi;

    public static Connection getConnection() {
        if (koneksi == null) {
            try { // IMPLEMENTASI EXCEPTION HANDLING
                String url = "jdbc:mysql://localhost:3306/db_penjualan";
                String user = "root";
                String password = ""; // Default XAMPP biasanya kosong
                
                // Membuat koneksi ke database
                koneksi = DriverManager.getConnection(url, user, password);
                System.out.println("Koneksi ke database db_penjualan berhasil");
                
            } catch (SQLException e) {
                System.err.println("Koneksi database gagal: " + e.getMessage());
            }
        }
        return koneksi;
    }
}