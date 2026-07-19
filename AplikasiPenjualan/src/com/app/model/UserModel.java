package com.app.model;

public class UserModel extends Orang {
    private String username;
    private String password;
    private String role;

    public UserModel(int id, String nama, String username, String password, String role) {
        super(id, nama); // Memanggil konstruktor dari class Orang
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Polimorfisme: Mengganti perilaku method induk
    @Override
    public String deskripsiPeran() {
        return "Login sebagai: " + this.role + " (" + this.nama + ")";
    }

    // Getter & Setter untuk username, password, dan role
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getRole() { return role; }
}