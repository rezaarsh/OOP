package com.app.model;

public class PelangganModel extends Orang {
    private String alamat;
    private String noTelp;

    public PelangganModel(int id, String nama, String alamat, String noTelp) {
        super(id, nama);
        this.alamat = alamat;
        this.noTelp = noTelp;
    }

    // Polimorfisme
    @Override
    public String deskripsiPeran() {
        return "Data Pelanggan Toko: " + this.nama;
    }

    // Getter & Setter untuk alamat dan noTelp
    public String getAlamat() { return alamat; }
    public String getNoTelp() { return noTelp; }
}