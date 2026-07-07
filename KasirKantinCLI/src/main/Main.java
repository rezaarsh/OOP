package main;

import dao.PelangganDAO;
import dao.ProdukDAO;
import dao.TransaksiDAO;
import util.InputHelper;
import model.Produk;
import model.Pelanggan;
import model.Transaksi;
import java.util.InputMismatchException;

public class Main {

    static ProdukDAO produkDAO = new ProdukDAO();
    static PelangganDAO pelangganDAO = new PelangganDAO();
    static TransaksiDAO transaksiDAO = new TransaksiDAO();

    public static void main(String[] args) {

        int pilihan;

        do {

            System.out.println("\n========================================");
            System.out.println("      APLIKASI KASIR KANTIN CLI");
            System.out.println("========================================");
            System.out.println("1. Kelola Produk");
            System.out.println("2. Kelola Pelanggan");
            System.out.println("3. Transaksi");
            System.out.println("0. Keluar");
            System.out.println("========================================");
            System.out.print("Pilih menu : ");

            try {

                pilihan = InputHelper.input.nextInt();

            } catch (InputMismatchException e) {

                System.out.println("\nInput harus berupa angka!");

                InputHelper.input.nextLine();

                pilihan = -1;
            }

            switch (pilihan) {

                case 1:
                    menuProduk();
                    break;

                case 2:
                    menuPelanggan();
                    break;

                case 3:
                    menuTransaksi();
                    break;

                case 0:
                    System.out.println("\nTerima kasih...");
                    break;

                default:
                    System.out.println("\nMenu tidak tersedia.");
            }

        } while (pilihan != 0);

    }

    public static void menuProduk() {

        int pilih;

        do {

            System.out.println("\n========================================");
            System.out.println("            MENU PRODUK");
            System.out.println("========================================");
            System.out.println("1. Tambah Produk");
            System.out.println("2. Lihat Produk");
            System.out.println("0. Kembali");
            System.out.println("========================================");
            System.out.print("Pilih menu : ");

            try {

                pilih = InputHelper.input.nextInt();
                InputHelper.input.nextLine();

            } catch (InputMismatchException e) {

                System.out.println("\nInput harus berupa angka!");

                InputHelper.input.nextLine();

                pilih = -1;

                continue;
            }

            switch (pilih) {

                case 1:

                    Produk produk = new Produk();

                    System.out.print("Nama Produk : ");
                    produk.setNamaProduk(InputHelper.input.nextLine());

                    System.out.print("Harga       : ");
                    produk.setHarga(InputHelper.input.nextDouble());

                    System.out.print("Stok        : ");
                    produk.setStok(InputHelper.input.nextInt());

                    produkDAO.tambahProduk(produk);

                    break;

                case 2:

                    produkDAO.lihatProduk();

                    break;

                case 0:

                    break;

                default:

                    System.out.println("Menu tidak tersedia.");

            }

        } while (pilih != 0);

    }

    public static void menuPelanggan() {

        int pilih;

        do {

            System.out.println("\n========================================");
            System.out.println("          MENU PELANGGAN");
            System.out.println("========================================");
            System.out.println("1. Tambah Pelanggan");
            System.out.println("2. Lihat Pelanggan");
            System.out.println("0. Kembali");
            System.out.println("========================================");
            System.out.print("Pilih menu : ");

            try {

                pilih = InputHelper.input.nextInt();
                InputHelper.input.nextLine();

            } catch (InputMismatchException e) {

                System.out.println("\nInput harus berupa angka!");

                InputHelper.input.nextLine();

                pilih = -1;

                continue;
            }

            switch (pilih) {

                case 1:

                    Pelanggan pelanggan = new Pelanggan();

                    System.out.print("Nama  : ");
                    pelanggan.setNama(InputHelper.input.nextLine());

                    System.out.print("No HP : ");
                    pelanggan.setNoHp(InputHelper.input.nextLine());

                    pelangganDAO.tambahPelanggan(pelanggan);

                    break;

                case 2:

                    pelangganDAO.lihatPelanggan();

                    break;

                case 0:

                    break;

                default:

                    System.out.println("Menu tidak tersedia.");

            }

        } while (pilih != 0);

    }

    public static void menuTransaksi() {

        int pilih;

        do {

            System.out.println("\n========================================");
            System.out.println("          MENU TRANSAKSI");
            System.out.println("========================================");
            System.out.println("1. Transaksi Pembelian");
            System.out.println("2. Lihat Riwayat");
            System.out.println("0. Kembali");
            System.out.println("========================================");
            System.out.print("Pilih menu : ");

            try {

                pilih = InputHelper.input.nextInt();

            } catch (InputMismatchException e) {

                System.out.println("\nInput harus berupa angka!");

                InputHelper.input.nextLine();

                pilih = -1;
            }

            switch (pilih) {

                case 1:

                    Transaksi transaksi = new Transaksi();

                    System.out.println("\n===== DAFTAR PRODUK =====");
                    produkDAO.lihatProduk();

                    System.out.print("ID Produk      : ");
                    transaksi.setIdProduk(InputHelper.input.nextInt());

                    System.out.println("\n===== DAFTAR PELANGGAN =====");
                    pelangganDAO.lihatPelanggan();

                    System.out.print("ID Pelanggan   : ");
                    transaksi.setIdPelanggan(InputHelper.input.nextInt());

                    System.out.print("Jumlah         : ");
                    transaksi.setJumlah(InputHelper.input.nextInt());

                    int stok = produkDAO.getStokProduk(transaksi.getIdProduk());

                    if (transaksi.getJumlah() > stok) {
                        System.out.println("\nStok tidak mencukupi!");
                        break;
                    }

                    double harga = produkDAO.getHargaProduk(transaksi.getIdProduk());

                    double totall = harga * transaksi.getJumlah();

                    transaksi.setTotalHarga(totall);

                    System.out.println("Total Harga    : Rp " + totall);

                    // System.out.print("Total Harga : ");
                    // transaksi.setTotalHarga(InputHelper.input.nextDouble());

                    transaksiDAO.tambahTransaksi(transaksi);

                    break;

                case 2:

                    transaksiDAO.lihatRiwayatTransaksi();

                    double total = transaksiDAO.hitungTotalPenjualan();

                    System.out.println("----------------------------------------");
                    System.out.println("TOTAL PENJUALAN : Rp" + total);

                    break;

                case 0:

                    break;

                default:

                    System.out.println("Menu tidak tersedia.");

            }

        } while (pilih != 0);

    }

}