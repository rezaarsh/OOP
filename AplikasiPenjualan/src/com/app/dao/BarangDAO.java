package com.app.dao;

import com.app.config.DatabaseConnection;
import com.app.model.BarangModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BarangDAO {
    private Connection conn;

    public BarangDAO() {
        conn = DatabaseConnection.getConnection();
    }

    // 1. CREATE (Tambah Barang)
    public boolean tambahBarang(BarangModel barang) {
        String sql = "INSERT INTO barang (kode_barang, nama_barang, harga, stok) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, barang.getKodeBarang());
            stmt.setString(2, barang.getNamaBarang());
            stmt.setDouble(3, barang.getHarga());
            stmt.setInt(4, barang.getStok());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Gagal tambah barang: " + e.getMessage());
            return false;
        }
    }

    // 2. READ (Tampilkan Semua Barang)
    public List<BarangModel> getSemuaBarang() {
        List<BarangModel> listBarang = new ArrayList<>();
        String sql = "SELECT * FROM barang ORDER BY id_barang DESC";
        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                BarangModel b = new BarangModel(
                        rs.getInt("id_barang"),
                        rs.getString("kode_barang"),
                        rs.getString("nama_barang"),
                        rs.getDouble("harga"),
                        rs.getInt("stok"));
                listBarang.add(b);
            }
        } catch (SQLException e) {
            System.err.println("Gagal ambil data barang: " + e.getMessage());
        }
        return listBarang;
    }

    // 3. UPDATE (Ubah Barang)
    public boolean ubahBarang(BarangModel barang) {
        String sql = "UPDATE barang SET nama_barang=?, harga=?, stok=? WHERE kode_barang=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, barang.getNamaBarang());
            stmt.setDouble(2, barang.getHarga());
            stmt.setInt(3, barang.getStok());
            stmt.setString(4, barang.getKodeBarang());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Gagal ubah barang: " + e.getMessage());
            return false;
        }
    }

    // 4. DELETE (Hapus Barang)
    public boolean hapusBarang(String kodeBarang) {
        String sql = "DELETE FROM barang WHERE kode_barang=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, kodeBarang);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Gagal hapus barang: " + e.getMessage());
            return false;
        }
    }

    // 5. SEARCH (Pencarian Data Barang)
    public List<BarangModel> cariBarang(String keyword) {
        List<BarangModel> listBarang = new ArrayList<>();
        String sql = "SELECT * FROM barang WHERE kode_barang LIKE ? OR nama_barang LIKE ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                listBarang.add(new BarangModel(
                        rs.getInt("id_barang"),
                        rs.getString("kode_barang"),
                        rs.getString("nama_barang"),
                        rs.getDouble("harga"),
                        rs.getInt("stok")));
            }
        } catch (SQLException e) {
            System.err.println("Gagal cari barang: " + e.getMessage());
        }
        return listBarang;
    }
}