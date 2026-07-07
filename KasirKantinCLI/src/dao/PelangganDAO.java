package dao;

import database.DatabaseConnection;
import model.Pelanggan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PelangganDAO {

    public void tambahPelanggan(Pelanggan pelanggan) {

        String sql = """
                INSERT INTO pelanggan (nama, no_hp)
                VALUES (?, ?)
                """;

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pelanggan.getNama());
            stmt.setString(2, pelanggan.getNoHp());

            int hasil = stmt.executeUpdate();

            if (hasil > 0) {
                System.out.println("\nPelanggan berhasil ditambahkan.");
            }

        } catch (SQLException e) {
            System.out.println("Gagal menambah pelanggan.");
            System.out.println(e.getMessage());
        }
    }

    public void lihatPelanggan() {

        String sql = """
                SELECT *
                FROM pelanggan
                """;

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            System.out.println("\n========================================");
            System.out.println("          DAFTAR PELANGGAN");
            System.out.println("========================================");

            while (rs.next()) {

                System.out.println("----------------------------------------");
                System.out.println("ID      : " + rs.getInt("id_pelanggan"));
                System.out.println("Nama    : " + rs.getString("nama"));
                System.out.println("No HP   : " + rs.getString("no_hp"));
            }

        } catch (SQLException e) {
            System.out.println("Gagal menampilkan pelanggan.");
            System.out.println(e.getMessage());
        }
    }
}