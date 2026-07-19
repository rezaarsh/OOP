package com.app.main;

import com.app.view.LoginView;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Menjalankan GUI di thread yang aman (Best Practice Java Swing)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginView().setVisible(true);
            }
        });
    }
}