package model;

import java.sql.Timestamp;

public class Transaksi {

    private int idTransaksi;
    private int idProduk;
    private int idPelanggan;
    private int jumlah;
    private double totalHarga;
    private Timestamp tanggal;

    public Transaksi() {

    }

    public Transaksi(int idTransaksi, int idProduk, int idPelanggan, int jumlah, double totalHarga, Timestamp tanggal) {

        this.idTransaksi = idTransaksi;
        this.idProduk = idProduk;
        this.idPelanggan = idPelanggan;
        this.jumlah = jumlah;
        this.totalHarga = totalHarga;
        this.tanggal = tanggal;
    }

    public int getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(int idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public int getIdProduk() {
        return idProduk;
    }

    public void setIdProduk(int idProduk) {
        this.idProduk = idProduk;
    }

    public int getIdPelanggan() {
        return idPelanggan;
    }

    public void setIdPelanggan(int idPelanggan) {
        this.idPelanggan = idPelanggan;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public double getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(double totalHarga) {
        this.totalHarga = totalHarga;
    }

    public Timestamp getTanggal() {
        return tanggal;
    }

    public void setTanggal(Timestamp tanggal) {
        this.tanggal = tanggal;
    }
}