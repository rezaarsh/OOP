package com.app.model;

public class BarangModel {
    private int idBarang;
    private String kodeBarang;
    private String namaBarang;
    private double harga;
    private int stok;

    // Construktor Kosong
    public BarangModel() {}

    // Construktor Lengkap
    public BarangModel(int idBarang, String kodeBarang, String namaBarang, double harga, int stok) {
        this.idBarang = idBarang;
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.harga = harga;
        this.stok = stok;
    }

    // Getter dan Setter
    public int getIdBarang() {
        return idBarang;
    }
    public void setIdBarang(int idBarang) {
        this.idBarang = idBarang;
    }

    public String getKodeBarang() {
        return kodeBarang;
    }
    public void setKodeBarang(String kodeBarang) {
        this.kodeBarang = kodeBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }
    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public double getHarga() {
        return harga;
    }
    public void setHarga(double harga) {
        this.harga = harga;
    }

    public int getStok() {
        return stok;
    }
    public void setStok(int stok) {
        this.stok = stok;
    }
}