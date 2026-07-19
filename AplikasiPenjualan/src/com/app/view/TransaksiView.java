package com.app.view;

import com.app.dao.TransaksiDAO;
import com.app.model.BarangModel;
import com.app.model.KeranjangModel;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TransaksiView extends JFrame {
    private JTextField txtNoFaktur, txtIdPelanggan, txtKodeBarang, txtJumlah, txtTotalHarga;
    private JButton btnTambah, btnSimpan;
    private JTable tabelKeranjang;
    private DefaultTableModel tableModel;

    private TransaksiDAO transaksiDAO;
    private List<KeranjangModel> listKeranjang;
    private double grandTotal = 0;

    public TransaksiView() {
        transaksiDAO = new TransaksiDAO();
        listKeranjang = new ArrayList<>();

        setTitle("Transaksi Penjualan");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // --- PANEL ATAS: DATA MASTER ---
        JPanel panelAtas = new JPanel(new GridLayout(2, 4, 10, 10));
        panelAtas.setBorder(BorderFactory.createTitledBorder("Data Transaksi"));

        panelAtas.add(new JLabel("No Faktur:"));
        txtNoFaktur = new JTextField("INV-" + System.currentTimeMillis()); // Generate Faktur Otomatis
        txtNoFaktur.setEditable(false);
        panelAtas.add(txtNoFaktur);

        panelAtas.add(new JLabel("ID Pelanggan:"));
        txtIdPelanggan = new JTextField("1"); // Default ID pelanggan 1
        panelAtas.add(txtIdPelanggan);

        // --- PANEL TENGAH: INPUT BARANG ---
        JPanel panelBarang = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBarang.setBorder(BorderFactory.createTitledBorder("Input Barang (Keranjang)"));

        panelBarang.add(new JLabel("Kode Barang:"));
        txtKodeBarang = new JTextField(10);
        panelBarang.add(txtKodeBarang);

        panelBarang.add(new JLabel("Jumlah:"));
        txtJumlah = new JTextField(5);
        panelBarang.add(txtJumlah);

        btnTambah = new JButton("Tambah ke Keranjang");
        panelBarang.add(btnTambah);

        // --- TABEL KERANJANG ---
        String[] header = { "ID Barang", "Kode Barang", "Nama Barang", "Harga", "Jumlah", "Subtotal" };
        tableModel = new DefaultTableModel(header, 0);
        tabelKeranjang = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tabelKeranjang);

        // --- PANEL BAWAH: TOTAL & SIMPAN ---
        JPanel panelBawah = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBawah.add(new JLabel("TOTAL HARGA: Rp"));
        txtTotalHarga = new JTextField(15);
        txtTotalHarga.setEditable(false);
        txtTotalHarga.setFont(new Font("Arial", Font.BOLD, 16));
        panelBawah.add(txtTotalHarga);

        btnSimpan = new JButton("Simpan Transaksi");
        panelBawah.add(btnSimpan);

        // --- MENYUSUN LAYOUT UTAMA ---
        JPanel panelGabunganAtas = new JPanel(new BorderLayout());
        panelGabunganAtas.add(panelAtas, BorderLayout.NORTH);
        panelGabunganAtas.add(panelBarang, BorderLayout.CENTER);

        add(panelGabunganAtas, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelBawah, BorderLayout.SOUTH);

        inisialisasiEvent();
    }

    private void inisialisasiEvent() {
        // Event Tambah ke Keranjang
        btnTambah.addActionListener(e -> {
            String kode = txtKodeBarang.getText();
            int jumlah = 0;
            try {
                jumlah = Integer.parseInt(txtJumlah.getText());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Jumlah tidak valid!");
                return;
            }

            BarangModel barang = transaksiDAO.getBarangByKode(kode);
            if (barang != null) {
                if (barang.getStok() < jumlah) {
                    JOptionPane.showMessageDialog(this, "Stok tidak mencukupi! Stok tersisa: " + barang.getStok());
                    return;
                }

                KeranjangModel item = new KeranjangModel(barang.getIdBarang(), barang.getKodeBarang(),
                        barang.getNamaBarang(), barang.getHarga(), jumlah);
                listKeranjang.add(item);

                // Tambah ke tabel visual
                tableModel.addRow(new Object[] { item.getIdBarang(), item.getKodeBarang(), item.getNamaBarang(),
                        item.getHargaSatuan(), item.getJumlah(), item.getSubtotal() });

                // Update Total
                grandTotal += item.getSubtotal();
                txtTotalHarga.setText(String.valueOf(grandTotal));

                txtKodeBarang.setText("");
                txtJumlah.setText("");
                txtKodeBarang.requestFocus();
            } else {
                JOptionPane.showMessageDialog(this, "Barang dengan kode tersebut tidak ditemukan!");
            }
        });

        // Event Simpan Transaksi Utama
        btnSimpan.addActionListener(e -> {
            if (listKeranjang.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Keranjang masih kosong!");
                return;
            }

            String noFaktur = txtNoFaktur.getText();
            int idPelanggan = Integer.parseInt(txtIdPelanggan.getText());
            int idUser = 1; // asumsikan ID User (Kasir) yang login adalah 1

            if (transaksiDAO.simpanTransaksi(noFaktur, idUser, idPelanggan, grandTotal, listKeranjang)) {
                JOptionPane.showMessageDialog(this, "Transaksi Berhasil Disimpan!");
                this.dispose(); // Tutup form setelah transaksi sukses
            }
        });
    }
}