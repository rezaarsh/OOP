public class Nilai extends DataMahasiswa {
    private int nilai;

    public Nilai(String nim, String nama, int nilai) {
        super(nim, nama);
        this.nilai = nilai;
    }

    public int getNilai() {
        return nilai;
    }

    public String getGrade() {
        if (nilai <= 100 && nilai >= 80) {
            return "A";
        }
        else if (nilai <= 79 && nilai >= 70) {
            return "B";
        }
        else if (nilai <= 69 && nilai >= 60) {
            return "C";
        }
        else if (nilai <= 59 && nilai >= 50) {
            return "D";
        }
        else if (nilai < 50 && nilai >= 0) {
            return "E";
        }
        else {
            return "Error";
        }
    }

    public boolean isLulus() {
        if (nilai >= 60) {
            return true;
        }
        else {
            return false;
        }
    }
}
