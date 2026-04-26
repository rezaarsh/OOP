package com.agency.entitas;

public abstract class ProjectWeb implements AturanProject {
    // Encapsulation: Variabel/Atribut dibuat private
    private String namaKlien;
    private double harga;

    // Constructor
    public ProjectWeb(String namaKlien, double harga) {
        this.namaKlien = namaKlien;
        this.harga = harga;
    }

    // Getter untuk mengambil data private
    public String getNamaKlien() {
        return namaKlien;
    }

    public double getHarga() {
        return harga;
    }

    // Method abstract yang harus di-override subclass
    // Fungsinya belum jelas, nanti dijelaskan di class turunan
    public abstract void tampilkanDetail();
}