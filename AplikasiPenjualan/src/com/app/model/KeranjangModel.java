package com.app.model;

public class KeranjangModel {
    private int idBarang;
    private String kodeBarang;
    private String namaBarang;
    private double hargaSatuan;
    private int jumlah;
    private double subtotal;

    public KeranjangModel(int idBarang, String kodeBarang, String namaBarang, double hargaSatuan, int jumlah) {
        this.idBarang = idBarang;
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.hargaSatuan = hargaSatuan;
        this.jumlah = jumlah;
        this.subtotal = hargaSatuan * jumlah;
    }

    // Getter
    public int getIdBarang() { return idBarang; }
    public String getKodeBarang() { return kodeBarang; }
    public String getNamaBarang() { return namaBarang; }
    public double getHargaSatuan() { return hargaSatuan; }
    public int getJumlah() { return jumlah; }
    public double getSubtotal() { return subtotal; }
}