package com.app.view;

import javax.swing.*;
import java.awt.*;

public class DashboardView extends JFrame {

    public DashboardView(String username) {
        // Konfigurasi Frame Utama
        setTitle("Dashboard - Aplikasi Penjualan");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Membuat Header Pesan Selamat Datang
        JLabel lblWelcome = new JLabel("Selamat Datang, " + username + "!", SwingConstants.CENTER);
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 20));
        lblWelcome.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(lblWelcome, BorderLayout.NORTH);

        // Membuat Panel Menu dengan Grid Layout (2 baris, 2 kolom)
        JPanel panelMenu = new JPanel(new GridLayout(2, 2, 15, 15));
        panelMenu.setBorder(BorderFactory.createEmptyBorder(20, 40, 40, 40));

        // Membuat Tombol-tombol Menu Utama
        JButton btnBarang = new JButton("Kelola Data Barang");
        JButton btnPelanggan = new JButton("Kelola Data Pelanggan");
        JButton btnTransaksi = new JButton("Transaksi Penjualan");
        JButton btnLaporan = new JButton("Laporan Penjualan");

        // Menambahkan tombol ke panel
        panelMenu.add(btnBarang);
        panelMenu.add(btnPelanggan);
        panelMenu.add(btnTransaksi);
        panelMenu.add(btnLaporan);

        // Menambahkan panel menu ke tengah Frame
        add(panelMenu, BorderLayout.CENTER);

        // Membuka form kelola barang dan membuatnya tidak menutup dashboard sepenuhnya saat di close
        btnBarang.addActionListener(e -> {
            BarangView formBarang = new BarangView();
            formBarang.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Penting: agar dashboard tidak ikut tertutup
            formBarang.setVisible(true);
        });

        // Membuka form kelola pelanggan dan membuatnya tidak menutup dashboard sepenuhnya saat di close
        btnPelanggan.addActionListener(e -> {
            PelangganView formPelanggan = new PelangganView();
            formPelanggan.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            formPelanggan.setVisible(true);
        });

        // Membuka form kelola transaksi dan membuatnya tidak menutup dashboard sepenuhnya saat di close
        btnTransaksi.addActionListener(e -> {
            TransaksiView formTransaksi = new TransaksiView();
            formTransaksi.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            formTransaksi.setVisible(true);
        });

        // Membuka form kelola laporan dan membuatnya tidak menutup dashboard sepenuhnya saat di close
        btnLaporan.addActionListener(e -> {
            LaporanView formLaporan = new LaporanView();
            formLaporan.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            formLaporan.setVisible(true);
        });
    }
}