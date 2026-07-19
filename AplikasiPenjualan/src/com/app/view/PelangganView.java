package com.app.view;

import com.app.dao.PelangganDAO;
import com.app.model.PelangganModel;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PelangganView extends JFrame {
    private JTextField txtId, txtNama, txtAlamat, txtTelp, txtCari;
    private JButton btnSimpan, btnUbah, btnHapus, btnBatal, btnCari;
    private JTable tabelPelanggan;
    private DefaultTableModel tableModel;
    private PelangganDAO pelangganDAO;

    public PelangganView() {
        pelangganDAO = new PelangganDAO();
        
        setTitle("Kelola Data Pelanggan");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // --- PANEL FORM ---
        JPanel panelForm = new JPanel(new GridLayout(4, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createTitledBorder("Form Data Pelanggan"));

        panelForm.add(new JLabel("ID Pelanggan (Auto):"));
        txtId = new JTextField();
        txtId.setEditable(false); // ID otomatis dari database
        panelForm.add(txtId);

        panelForm.add(new JLabel("Nama Pelanggan:"));
        txtNama = new JTextField();
        panelForm.add(txtNama);

        panelForm.add(new JLabel("Alamat:"));
        txtAlamat = new JTextField();
        panelForm.add(txtAlamat);

        panelForm.add(new JLabel("No. Telepon:"));
        txtTelp = new JTextField();
        panelForm.add(txtTelp);

        // --- PANEL AKSI & PENCARIAN ---
        JPanel panelAksi = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnSimpan = new JButton("Simpan");
        btnUbah = new JButton("Ubah");
        btnHapus = new JButton("Hapus");
        btnBatal = new JButton("Batal");
        panelAksi.add(btnSimpan); panelAksi.add(btnUbah); panelAksi.add(btnHapus); panelAksi.add(btnBatal);

        JPanel panelCari = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        txtCari = new JTextField(15);
        btnCari = new JButton("Cari");
        panelCari.add(new JLabel("Cari:")); panelCari.add(txtCari); panelCari.add(btnCari);

        JPanel panelTengah = new JPanel(new BorderLayout());
        panelTengah.add(panelAksi, BorderLayout.WEST);
        panelTengah.add(panelCari, BorderLayout.EAST);

        // --- TABEL ---
        String[] header = {"ID", "Nama", "Alamat", "No. Telepon"};
        tableModel = new DefaultTableModel(header, 0);
        tabelPelanggan = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tabelPelanggan);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Tabel Data Pelanggan"));

        JPanel panelAtas = new JPanel(new BorderLayout());
        panelAtas.add(panelForm, BorderLayout.NORTH);
        panelAtas.add(panelTengah, BorderLayout.SOUTH);

        add(panelAtas, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        inisialisasiEvent();
        muatData();
    }

    private void inisialisasiEvent() {
        btnSimpan.addActionListener(e -> {
            PelangganModel p = new PelangganModel(0, txtNama.getText(), txtAlamat.getText(), txtTelp.getText());
            if (pelangganDAO.tambahPelanggan(p)) {
                JOptionPane.showMessageDialog(this, "Tersimpan!");
                bersihkanForm(); muatData();
            }
        });

        tabelPelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int baris = tabelPelanggan.getSelectedRow();
                if (baris > -1) {
                    txtId.setText(tableModel.getValueAt(baris, 0).toString());
                    txtNama.setText(tableModel.getValueAt(baris, 1).toString());
                    txtAlamat.setText(tableModel.getValueAt(baris, 2).toString());
                    txtTelp.setText(tableModel.getValueAt(baris, 3).toString());
                }
            }
        });

        btnUbah.addActionListener(e -> {
            if (txtId.getText().isEmpty()) return;
            PelangganModel p = new PelangganModel(Integer.parseInt(txtId.getText()), txtNama.getText(), txtAlamat.getText(), txtTelp.getText());
            if (pelangganDAO.ubahPelanggan(p)) {
                JOptionPane.showMessageDialog(this, "Diubah!");
                bersihkanForm(); muatData();
            }
        });

        btnHapus.addActionListener(e -> {
            if (txtId.getText().isEmpty()) return;
            if (JOptionPane.showConfirmDialog(this, "Hapus?", "Konfirmasi", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                if (pelangganDAO.hapusPelanggan(Integer.parseInt(txtId.getText()))) {
                    JOptionPane.showMessageDialog(this, "Dihapus!");
                    bersihkanForm(); muatData();
                }
            }
        });

        btnBatal.addActionListener(e -> bersihkanForm());
        btnCari.addActionListener(e -> muatDataPencarian(txtCari.getText()));
    }

    private void bersihkanForm() {
        txtId.setText(""); txtNama.setText(""); txtAlamat.setText(""); txtTelp.setText(""); txtCari.setText("");
    }

    private void muatData() {
        tableModel.setRowCount(0);
        for (PelangganModel p : pelangganDAO.getSemuaPelanggan()) {
            tableModel.addRow(new Object[]{p.getId(), p.getNama(), p.getAlamat(), p.getNoTelp()});
        }
    }

    private void muatDataPencarian(String key) {
        tableModel.setRowCount(0);
        for (PelangganModel p : pelangganDAO.cariPelanggan(key)) {
            tableModel.addRow(new Object[]{p.getId(), p.getNama(), p.getAlamat(), p.getNoTelp()});
        }
    }
}