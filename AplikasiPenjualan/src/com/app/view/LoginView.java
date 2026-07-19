package com.app.view;

import com.app.dao.UserDAO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Menerapkan INHERITANCE (LoginView mewarisi sifat JFrame)
public class LoginView extends JFrame {
    
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public LoginView() {
        // Konfigurasi Frame dasar
        setTitle("Aplikasi Penjualan - Login");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Membuat window muncul di tengah layar
        setLayout(new GridLayout(4, 1, 10, 10)); // Grid layout yang rapi

        // Inisialisasi Komponen GUI
        JPanel panelUser = new JPanel(new FlowLayout());
        panelUser.add(new JLabel("Username: "));
        txtUsername = new JTextField(15);
        panelUser.add(txtUsername);

        JPanel panelPass = new JPanel(new FlowLayout());
        panelPass.add(new JLabel("Password: "));
        txtPassword = new JPasswordField(15);
        panelPass.add(txtPassword);

        JPanel panelBtn = new JPanel(new FlowLayout());
        btnLogin = new JButton("Login");
        panelBtn.add(btnLogin);

        // Menambahkan komponen ke dalam Frame
        add(new JLabel("SILAHKAN LOGIN", SwingConstants.CENTER));
        add(panelUser);
        add(panelPass);
        add(panelBtn);

        // Event Listener untuk tombol Login
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prosesLogin();
            }
        });
    }

    private void prosesLogin() {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        UserDAO userDAO = new UserDAO();
        
        // Memanggil method dari DAO
        if (userDAO.cekLogin(username, password)) {
            JOptionPane.showMessageDialog(this, "Login Berhasil!");
            
            this.dispose(); // Perintah untuk menutup form login
            new DashboardView(username).setVisible(true); // Membuka form Dashboard
            
        } else {
            JOptionPane.showMessageDialog(this, "Login Gagal! Username atau Password salah.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}