package com.agency.entitas;

// Inheritance: Mewarisi sifat dari class induk (ProjectWeb)
public class ProjectKatalog extends ProjectWeb {
    private String namaBrand;

    public ProjectKatalog(String namaKlien, double harga, String namaBrand) {
        super(namaKlien, harga); // Memanggil constructor dari class induk
        this.namaBrand = namaBrand;
    }

    // Polymorphism: Menimpa (override) method abstrak dari class induk
    @Override
    public void tampilkanDetail() {
        System.out.println("Tipe Project : Website Katalog");
        System.out.println("Nama Brand   : " + namaBrand);
    }

    // Polymorphism: Menimpa method dari interface
    @Override
    public void cetakInvoice() {
        System.out.println("Tagihan Klien " + getNamaKlien() + " sebesar Rp" + getHarga());
    }
}