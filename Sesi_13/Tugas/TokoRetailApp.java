import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class TokoRetailApp {

    // Konfigurasi Database (Menggunakan database toko_retail)
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; 
    static final String DB_URL = "jdbc:mysql://localhost/toko_retail";
    static final String USER = "root";
    static final String PASS = "";

    static Connection conn;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            // Register Driver dan Buka Koneksi
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            int pilihan;
            do {
                tampilkanMenu();
                System.out.print("Pilihan : ");
                // Membaca input dan mencegah error pindah baris (newline)
                pilihan = Integer.parseInt(scanner.nextLine()); 
                
                System.out.println(); // Spasi pemisah
                switch (pilihan) {
                    case 1:
                        tampilSemuaData();
                        break;
                    case 2:
                        tambahData();
                        break;
                    case 3:
                        cariData();
                        break;
                    case 4:
                        ubahData();
                        break;
                    case 5:
                        hapusData();
                        break;
                    case 0:
                        System.out.println("Keluar dari program. Terima kasih!");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid!");
                }
                System.out.println();
            } while (pilihan != 0);

            // Tutup koneksi saat keluar
            conn.close();

        } catch (Exception e) {
            System.out.println("Terjadi kesalahan sistem:");
            e.printStackTrace();
        }
    }

    // MENU UTAMA
    static void tampilkanMenu() {
        System.out.println("=====================================");
        System.out.println("          MENU TOKO RETAIL           ");
        System.out.println("=====================================");
        System.out.println("  1. Tampil Semua Data");
        System.out.println("  2. Tambah Data");
        System.out.println("  3. Cari Data");
        System.out.println("  4. Ubah Data");
        System.out.println("  5. Hapus Data");
        System.out.println("  0. Keluar");
        System.out.println("=====================================");
    }

    // 1. TAMPIL SEMUA DATA (READ)
    static void tampilSemuaData() {
        try {
            String query = "SELECT * FROM tbl_barang";
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery(query);

            cetakHeaderTabel();
            int nomor = 0;
            while (rs.next()) {
                nomor++;
                System.out.printf("| %-2d | %-7s | %-25s | %-10d | %-5d |\n",
                        nomor,
                        rs.getString("kode_barang"),
                        rs.getString("nama_barang"),
                        rs.getInt("harga_barang"),
                        rs.getInt("stok_barang"));
            }
            System.out.println("===============================================================");
            System.out.println("Total: " + nomor + " barang");

        } catch (Exception e) {
            System.out.println("Gagal menampilkan data: " + e.getMessage());
        }
    }

    // 2. TAMBAH DATA (CREATE)
    static void tambahData() {
        try {
            System.out.println("--- TAMBAH DATA ---");
            System.out.print("Kode Barang  : ");
            String kode = scanner.nextLine();
            System.out.print("Nama Barang  : ");
            String nama = scanner.nextLine();
            System.out.print("Harga Barang : ");
            int harga = Integer.parseInt(scanner.nextLine());
            System.out.print("Stok Barang  : ");
            int stok = Integer.parseInt(scanner.nextLine());

            String query = "INSERT INTO tbl_barang VALUES (?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, kode);
            ps.setString(2, nama);
            ps.setInt(3, harga);
            ps.setInt(4, stok);

            if (ps.executeUpdate() > 0) {
                System.out.println("-> Proses Berhasil: Data ditambahkan!");
            } else {
                System.out.println("-> Proses Gagal.");
            }
        } catch (Exception e) {
            System.out.println("Gagal menambah data: " + e.getMessage());
        }
    }

    // 3. CARI DATA (SEARCH)
    static void cariData() {
        try {
            System.out.println("--- CARI DATA ---");
            System.out.print("Masukkan Keyword (Kode/Nama) : ");
            String keyword = scanner.nextLine();

            String query = "SELECT * FROM tbl_barang WHERE nama_barang LIKE ? OR kode_barang LIKE ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            
            ResultSet rs = ps.executeQuery();

            cetakHeaderTabel();
            int nomor = 0;
            while (rs.next()) {
                nomor++;
                System.out.printf("| %-2d | %-7s | %-25s | %-10d | %-5d |\n",
                        nomor,
                        rs.getString("kode_barang"),
                        rs.getString("nama_barang"),
                        rs.getInt("harga_barang"),
                        rs.getInt("stok_barang"));
            }
            System.out.println("===============================================================");
            System.out.println("Total ditemukan: " + nomor + " barang");

        } catch (Exception e) {
            System.out.println("Gagal mencari data: " + e.getMessage());
        }
    }

    // 4. UBAH DATA (UPDATE)
    static void ubahData() {
        try {
            System.out.println("--- UBAH DATA ---");
            System.out.print("Masukkan Kode Barang yang akan diubah: ");
            String kode = scanner.nextLine();

            System.out.print("Nama Barang Baru  : ");
            String nama = scanner.nextLine();
            System.out.print("Harga Barang Baru : ");
            int harga = Integer.parseInt(scanner.nextLine());
            System.out.print("Stok Barang Baru  : ");
            int stok = Integer.parseInt(scanner.nextLine());

            String query = "UPDATE tbl_barang SET nama_barang=?, harga_barang=?, stok_barang=? WHERE kode_barang=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, nama);
            ps.setInt(2, harga);
            ps.setInt(3, stok);
            ps.setString(4, kode);

            if (ps.executeUpdate() > 0) {
                System.out.println("-> Proses Berhasil: Data diupdate!");
            } else {
                System.out.println("-> Data tidak ditemukan atau gagal diupdate.");
            }
        } catch (Exception e) {
            System.out.println("Gagal mengubah data: " + e.getMessage());
        }
    }

    // 5. HAPUS DATA (DELETE)
    static void hapusData() {
        try {
            System.out.println("--- HAPUS DATA ---");
            System.out.print("Isikan Kode Barang yang akan dihapus: ");
            String kode = scanner.nextLine();

            String query = "DELETE FROM tbl_barang WHERE kode_barang=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, kode);

            if (ps.executeUpdate() > 0) {
                System.out.println("-> Proses Berhasil: Data dihapus!");
            } else {
                System.out.println("-> Data tidak ditemukan.");
            }
        } catch (Exception e) {
            System.out.println("Gagal menghapus data: " + e.getMessage());
        }
    }

    // FUNGSI BANTUAN UNTUK FORMAT TABEL
    static void cetakHeaderTabel() {
        System.out.println("===============================================================");
        System.out.println("                 DAFTAR BARANG TOKO RETAIL                     ");
        System.out.println("===============================================================");
        System.out.printf("| %-2s | %-7s | %-25s | %-10s | %-5s |\n", "#", "Kode", "Nama Barang", "Harga", "Stok");
        System.out.println("---------------------------------------------------------------");
    }
}