package model;

public class Pelanggan extends Person {

    private String noHp;

    public Pelanggan() {

    }

    public Pelanggan(int id, String nama, String noHp) {
        super(id, nama);
        this.noHp = noHp;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    @Override
    public void tampilInfo() {
        super.tampilInfo();
        System.out.println("No HP : " + noHp);
    }
}