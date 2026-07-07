package dao;

import database.DatabaseConnection;
import model.Produk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdukDAO {
    public void tambahProduk(Produk produk) {

        String sql = "INSERT INTO produk(nama_produk, harga, stok) VALUES (?, ?, ?)";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produk.getNamaProduk());
            stmt.setDouble(2, produk.getHarga());
            stmt.setInt(3, produk.getStok());

            int hasil = stmt.executeUpdate();

            if (hasil > 0) {
                System.out.println("\nProduk berhasil ditambahkan.");
            }

        } catch (SQLException e) {
            System.out.println("Gagal menambah produk.");
            System.out.println(e.getMessage());
        }
    }

    public void lihatProduk() {

        String sql = "SELECT * FROM produk";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            System.out.println("\n========================================");
            System.out.println("          DAFTAR PRODUK");
            System.out.println("========================================");

            while (rs.next()) {

                System.out.println("----------------------------------------");
                System.out.printf("ID      : %d%n", rs.getInt("id_produk"));
                System.out.printf("Nama    : %s%n", rs.getString("nama_produk"));
                System.out.printf("Harga   : Rp%,.0f%n", rs.getDouble("harga"));
                System.out.printf("Stok    : %d%n", rs.getInt("stok"));
            }

        } catch (SQLException e) {
            System.out.println("Gagal menampilkan produk.");
            System.out.println(e.getMessage());
        }
    }

    public double getHargaProduk(int idProduk) {

        String sql = """
                SELECT harga
                FROM produk
                WHERE id_produk = ?
                """;

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProduk);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getDouble("harga");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return 0;
    }

    public int getStokProduk(int idProduk) {

        String sql = """
                SELECT stok
                FROM produk
                WHERE id_produk = ?
                """;

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProduk);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("stok");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return 0;
    }
}