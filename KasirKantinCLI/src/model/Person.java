package model;

public class Person {

    private int id;
    private String nama;

    public Person() {

    }

    public Person(int id, String nama) {
        this.id = id;
        this.nama = nama;
    }

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

    public void tampilInfo() {
        System.out.println("ID : " + id);
        System.out.println("Nama : " + nama);
    }
}