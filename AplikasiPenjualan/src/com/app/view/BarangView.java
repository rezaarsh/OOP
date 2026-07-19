package com.app.view;

import com.app.dao.BarangDAO;
import com.app.model.BarangModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class BarangView extends JFrame {
    private JTextField txtKode, txtNama, txtHarga, txtStok, txtCari;
    private JButton btnSimpan, btnUbah, btnHapus, btnBatal, btnCari;
    private JTable tabelBarang;
    private DefaultTableModel tableModel;
    private BarangDAO barangDAO;

    public BarangView() {
        barangDAO = new BarangDAO();

        setTitle("Kelola Data Barang");
        setSize(700, 500);
        setLocationRelativeTo(null); // Tampilan di tengah layar
        setLayout(new BorderLayout(10, 10));

        // --- PANEL ATAS: FORM INPUT ---
        JPanel panelForm = new JPanel(new GridLayout(4, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createTitledBorder("Form Data Barang"));

        panelForm.add(new JLabel("Kode Barang:"));
        txtKode = new JTextField();
        panelForm.add(txtKode);

        panelForm.add(new JLabel("Nama Barang:"));
        txtNama = new JTextField();
        panelForm.add(txtNama);

        panelForm.add(new JLabel("Harga (Rp):"));
        txtHarga = new JTextField();
        panelForm.add(txtHarga);

        panelForm.add(new JLabel("Stok:"));
        txtStok = new JTextField();
        panelForm.add(txtStok);

        // --- PANEL TENGAH: TOMBOL AKSI & PENCARIAN ---
        JPanel panelAksi = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnSimpan = new JButton("Simpan");
        btnUbah = new JButton("Ubah");
        btnHapus = new JButton("Hapus");
        btnBatal = new JButton("Batal");

        panelAksi.add(btnSimpan);
        panelAksi.add(btnUbah);
        panelAksi.add(btnHapus);
        panelAksi.add(btnBatal);

        JPanel panelCari = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        txtCari = new JTextField(15);
        btnCari = new JButton("Cari");
        panelCari.add(new JLabel("Cari:"));
        panelCari.add(txtCari);
        panelCari.add(btnCari);

        // Gabungkan tombol aksi dan pencarian
        JPanel panelTengah = new JPanel(new BorderLayout());
        panelTengah.add(panelAksi, BorderLayout.WEST);
        panelTengah.add(panelCari, BorderLayout.EAST);

        // --- PANEL BAWAH: TABEL DATA ---
        String[] header = { "ID", "Kode", "Nama Barang", "Harga", "Stok" };
        tableModel = new DefaultTableModel(header, 0);
        tabelBarang = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tabelBarang);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Tabel Data Barang"));

        // --- MENYUSUN SEMUA PANEL KE FRAME UTAMA ---
        JPanel panelAtas = new JPanel(new BorderLayout());
        panelAtas.add(panelForm, BorderLayout.NORTH);
        panelAtas.add(panelTengah, BorderLayout.SOUTH);

        add(panelAtas, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // --- EVENT LISTENER (LOGIKA TOMBOL) ---
        inisialisasiEvent();

        // Load data saat form pertama kali dibuka
        muatDataKeTabel();
    }

    private void inisialisasiEvent() {
        // Tombol Simpan
        btnSimpan.addActionListener(e -> {
            try {
                BarangModel barang = new BarangModel(0, txtKode.getText(), txtNama.getText(),
                        Double.parseDouble(txtHarga.getText()), Integer.parseInt(txtStok.getText()));
                if (barangDAO.tambahBarang(barang)) {
                    JOptionPane.showMessageDialog(this, "Data berhasil disimpan!");
                    bersihkanForm();
                    muatDataKeTabel();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Harga dan Stok harus berupa angka!", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // Klik Tabel untuk mengisi form
        tabelBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int baris = tabelBarang.getSelectedRow();
                if (baris > -1) {
                    txtKode.setText(tableModel.getValueAt(baris, 1).toString());
                    txtNama.setText(tableModel.getValueAt(baris, 2).toString());
                    txtHarga.setText(tableModel.getValueAt(baris, 3).toString());
                    txtStok.setText(tableModel.getValueAt(baris, 4).toString());
                    txtKode.setEditable(false); // Kode tidak boleh diubah (Primary/Unique key)
                }
            }
        });

        // Tombol Ubah
        btnUbah.addActionListener(e -> {
            try {
                BarangModel barang = new BarangModel(0, txtKode.getText(), txtNama.getText(),
                        Double.parseDouble(txtHarga.getText()), Integer.parseInt(txtStok.getText()));
                if (barangDAO.ubahBarang(barang)) {
                    JOptionPane.showMessageDialog(this, "Data berhasil diubah!");
                    bersihkanForm();
                    muatDataKeTabel();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error input angka!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Tombol Hapus
        btnHapus.addActionListener(e -> {
            String kode = txtKode.getText();
            if (!kode.isEmpty()) {
                int confirm = JOptionPane.showConfirmDialog(this, "Yakin hapus barang ini?", "Konfirmasi",
                        JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    if (barangDAO.hapusBarang(kode)) {
                        JOptionPane.showMessageDialog(this, "Data dihapus!");
                        bersihkanForm();
                        muatDataKeTabel();
                    }
                }
            }
        });

        // Tombol Batal/Clear
        btnBatal.addActionListener(e -> bersihkanForm());

        // Tombol Cari
        btnCari.addActionListener(e -> muatDataPencarian(txtCari.getText()));
    }

    private void bersihkanForm() {
        txtKode.setText("");
        txtNama.setText("");
        txtHarga.setText("");
        txtStok.setText("");
        txtKode.setEditable(true);
    }

    private void muatDataKeTabel() {
        tableModel.setRowCount(0); // Kosongkan tabel
        List<BarangModel> list = barangDAO.getSemuaBarang();
        for (BarangModel b : list) {
            tableModel.addRow(
                    new Object[] { b.getIdBarang(), b.getKodeBarang(), b.getNamaBarang(), b.getHarga(), b.getStok() });
        }
    }

    private void muatDataPencarian(String keyword) {
        tableModel.setRowCount(0);
        List<BarangModel> list = barangDAO.cariBarang(keyword);
        for (BarangModel b : list) {
            tableModel.addRow(
                    new Object[] { b.getIdBarang(), b.getKodeBarang(), b.getNamaBarang(), b.getHarga(), b.getStok() });
        }
    }
}