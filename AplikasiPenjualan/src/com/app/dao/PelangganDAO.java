package com.app.dao;

import com.app.config.DatabaseConnection;
import com.app.model.PelangganModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PelangganDAO {
    private Connection conn;

    public PelangganDAO() {
        conn = DatabaseConnection.getConnection();
    }

    // 1. CREATE
    public boolean tambahPelanggan(PelangganModel p) {
        String sql = "INSERT INTO pelanggan (nama_pelanggan, alamat, no_telp) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getNama());
            stmt.setString(2, p.getAlamat());
            stmt.setString(3, p.getNoTelp());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Gagal tambah pelanggan: " + e.getMessage());
            return false;
        }
    }

    // 2. READ
    public List<PelangganModel> getSemuaPelanggan() {
        List<PelangganModel> list = new ArrayList<>();
        String sql = "SELECT * FROM pelanggan ORDER BY id_pelanggan DESC";
        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new PelangganModel(
                        rs.getInt("id_pelanggan"),
                        rs.getString("nama_pelanggan"),
                        rs.getString("alamat"),
                        rs.getString("no_telp")));
            }
        } catch (SQLException e) {
            System.err.println("Gagal ambil data pelanggan: " + e.getMessage());
        }
        return list;
    }

    // 3. UPDATE
    public boolean ubahPelanggan(PelangganModel p) {
        String sql = "UPDATE pelanggan SET nama_pelanggan=?, alamat=?, no_telp=? WHERE id_pelanggan=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getNama());
            stmt.setString(2, p.getAlamat());
            stmt.setString(3, p.getNoTelp());
            stmt.setInt(4, p.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Gagal ubah pelanggan: " + e.getMessage());
            return false;
        }
    }

    // 4. DELETE
    public boolean hapusPelanggan(int id) {
        String sql = "DELETE FROM pelanggan WHERE id_pelanggan=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Gagal hapus pelanggan: " + e.getMessage());
            return false;
        }
    }

    // 5. SEARCH (Pencarian Data Pelanggan)
    public List<PelangganModel> cariPelanggan(String keyword) {
        List<PelangganModel> list = new ArrayList<>();
        String sql = "SELECT * FROM pelanggan WHERE nama_pelanggan LIKE ? OR no_telp LIKE ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new PelangganModel(
                        rs.getInt("id_pelanggan"),
                        rs.getString("nama_pelanggan"),
                        rs.getString("alamat"),
                        rs.getString("no_telp")));
            }
        } catch (SQLException e) {
            System.err.println("Gagal cari pelanggan: " + e.getMessage());
        }
        return list;
    }
}