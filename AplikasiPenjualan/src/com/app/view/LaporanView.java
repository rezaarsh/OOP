package com.app.view;

import com.app.dao.LaporanDAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LaporanView extends JFrame {
    private JTable tabelLaporan;
    private DefaultTableModel tableModel;
    private LaporanDAO laporanDAO;

    public LaporanView() {
        laporanDAO = new LaporanDAO();
        
        setTitle("Laporan Penjualan");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Membuat Header
        JLabel lblHeader = new JLabel("Laporan Riwayat Penjualan", SwingConstants.CENTER);
        lblHeader.setFont(new Font("Arial", Font.BOLD, 18));
        lblHeader.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        add(lblHeader, BorderLayout.NORTH);

        // Membuat Tabel Laporan
        String[] header = {"No Faktur", "Tanggal", "Pelanggan", "Barang", "Harga Satuan", "Jumlah", "Subtotal", "Kasir"};
        tableModel = new DefaultTableModel(header, 0);
        tabelLaporan = new JTable(tableModel);
        
        // Mematikan fitur edit sel langsung di tabel laporan
        tabelLaporan.setDefaultEditor(Object.class, null); 
        
        JScrollPane scrollPane = new JScrollPane(tabelLaporan);
        add(scrollPane, BorderLayout.CENTER);

        // Memanggil fungsi DAO untuk mengisi tabel
        laporanDAO.muatLaporan(tableModel);
    }
}