package com.app.dao;

import com.app.config.DatabaseConnection;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class LaporanDAO {
    private Connection conn;

    public LaporanDAO() {
        conn = DatabaseConnection.getConnection();
    }

    // Mengambil data langsung dari VIEW yang sudah dibuat di MySQL
    public void muatLaporan(DefaultTableModel tableModel) {
        // Mengosongkan tabel sebelum diisi data baru
        tableModel.setRowCount(0);

        String sql = "SELECT * FROM v_laporan_penjualan ORDER BY tanggal DESC";

        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                tableModel.addRow(new Object[] {
                        rs.getString("no_faktur"),
                        rs.getString("tanggal"),
                        rs.getString("nama_pelanggan"),
                        rs.getString("nama_barang"),
                        rs.getDouble("harga_satuan"),
                        rs.getInt("jumlah"),
                        rs.getDouble("subtotal"),
                        rs.getString("nama_kasir")
                });
            }
        } catch (SQLException e) {
            System.err.println("Gagal memuat laporan: " + e.getMessage());
        }
    }
}