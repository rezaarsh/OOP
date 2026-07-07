package dao;

import database.DatabaseConnection;
import model.Transaksi;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransaksiDAO {
    public void tambahTransaksi(Transaksi transaksi) {

        String sql = "{CALL tambah_transaksi(?, ?, ?, ?)}";

        try (
                Connection conn = DatabaseConnection.getConnection();
                CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, transaksi.getIdProduk());
            stmt.setInt(2, transaksi.getIdPelanggan());
            stmt.setInt(3, transaksi.getJumlah());
            stmt.setDouble(4, transaksi.getTotalHarga());

            stmt.execute();

            System.out.println("\nTransaksi berhasil disimpan.");

        } catch (SQLException e) {
            System.out.println("Gagal melakukan transaksi.");
            System.out.println(e.getMessage());
        }
    }

    public void lihatRiwayatTransaksi() {

        String sql = """
                SELECT *
                FROM v_transaksi
                """;

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            System.out.println("\n========================================");
            System.out.println("          RIWAYAT TRANSAKSI");
            System.out.println("========================================");

            while (rs.next()) {

                System.out.println("----------------------------------------");
                System.out.println("ID              : " + rs.getInt("id_transaksi"));
                System.out.println("Produk          : " + rs.getString("nama_produk"));
                System.out.println("Pelanggan       : " + rs.getString("nama_pelanggan"));
                System.out.println("Jumlah          : " + rs.getInt("jumlah"));
                System.out.printf("Total Harga     : Rp %,.0f%n", rs.getDouble("total_harga"));
                System.out.println("Tanggal         : " + rs.getTimestamp("tanggal"));
            }

        } catch (SQLException e) {
            System.out.println("Gagal menampilkan riwayat transaksi.");
            System.out.println(e.getMessage());
        }
    }

    public double hitungTotalPenjualan() {

        String sql = "SELECT hitung_total_penjualan()";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getDouble(1);
            }

        } catch (SQLException e) {
            System.out.println("Gagal menghitung total penjualan.");
            System.out.println(e.getMessage());
        }

        return 0;
    }
}
