public class ThrowExample {
    static void demo() {
        NullPointerException t;
        t = new NullPointerException("Coba Throw");
        throw t;
        // Baris ini tidak lagi dikerjakan;
        System.out.println("Ini tidak lagi dicetak");
    }

    public static void main(String[] args) {
        try {
            demo(); // memanggil method demo()
            System.out.println("Selesai"); // tidak dijalankan karena exception sudah terjadi
        } catch (NullPointerException e) { // menangkap exception bertipe NullPointerException
            System.out.println("Ada pesan error: " + e); // menampilkan informasi exception yang terjadi
        }
    }
}