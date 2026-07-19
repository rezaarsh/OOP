package com.app.model;

public abstract class Orang {
    protected int id;
    protected String nama;

    public Orang(int id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    // Enkapsulasi: Getter & Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    // Method abstrak untuk Polimorfisme
    public abstract String deskripsiPeran();
}