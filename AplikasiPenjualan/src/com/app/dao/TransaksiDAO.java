package com.app.dao;

import com.app.config.DatabaseConnection;
import com.app.model.BarangModel;
import com.app.model.KeranjangModel;
import java.sql.*;
import java.util.List;

public class TransaksiDAO {
    private Connection conn;

    public TransaksiDAO() {
        conn = DatabaseConnection.getConnection();
    }

    // Mengambil data barang spesifik berdasarkan Kode untuk dimasukkan ke keranjang
    public BarangModel getBarangByKode(String kode) {
        String sql = "SELECT * FROM barang WHERE kode_barang = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, kode);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new BarangModel(rs.getInt("id_barang"), rs.getString("kode_barang"),
                        rs.getString("nama_barang"), rs.getDouble("harga"), rs.getInt("stok"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Proses menyimpan transaksi utama dan detail transaksi
    public boolean simpanTransaksi(String noFaktur, int idUser, int idPelanggan, double totalHarga,
            List<KeranjangModel> keranjang) {
        // SQL untuk memanggil STORED PROCEDURE
        String sqlSP = "{CALL sp_buat_transaksi_baru(?, ?, ?, ?)}";
        String sqlDetail = "INSERT INTO detail_transaksi (id_transaksi, id_barang, harga_satuan, jumlah, subtotal) VALUES (?, ?, ?, ?, ?)";

        try {
            // 1. Eksekusi Stored Procedure untuk tabel transaksi
            CallableStatement cs = conn.prepareCall(sqlSP);
            cs.setString(1, noFaktur);
            cs.setInt(2, idUser); // Id kasir/admin
            cs.setInt(3, idPelanggan);
            cs.setDouble(4, totalHarga);

            ResultSet rs = cs.executeQuery();
            int idTransaksiBaru = 0;

            // Mengambil ID Transaksi yang baru saja dibuat oleh Procedure
            if (rs.next()) {
                idTransaksiBaru = rs.getInt("id_transaksi");
            }

            // 2. Insert ke tabel detail_transaksi
            if (idTransaksiBaru > 0) {
                PreparedStatement psDetail = conn.prepareStatement(sqlDetail);
                for (KeranjangModel item : keranjang) {
                    psDetail.setInt(1, idTransaksiBaru);
                    psDetail.setInt(2, item.getIdBarang());
                    psDetail.setDouble(3, item.getHargaSatuan());
                    psDetail.setInt(4, item.getJumlah());
                    psDetail.setDouble(5, item.getSubtotal());
                    psDetail.addBatch(); // Gunakan Batch untuk mempercepat insert data banyak sekaligus
                }
                psDetail.executeBatch();
            }
            return true;

        } catch (SQLException e) {
            System.err.println("Gagal simpan transaksi: " + e.getMessage());
            return false;
        }
    }
}