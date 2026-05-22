public class Exception5 {
    public static void main(String[] args) {
        int bil = 10;
        try {
            System.out.println(bil / 0); // terjadi error karena pembagian dengan nol
        } catch (ArithmeticException e) {
            System.out.println("Pesan error: "); // menampilkan teks pesan error
            System.out.println(e.getMessage()); // menampilkan pesan detail error → "/ by zero"
            System.out.println("Info stack erase"); // menampilkan informasi sebelum stack trace
            e.printStackTrace(); // menampilkan detail lengkap error dan lokasi error
            e.printStackTrace(System.out); // menampilkan stack trace ke output System.out
        } catch (Exception e) {
            System.out.println("Ini menghandle error yang terjadi"); // pesan jika terjadi exception umum
        }
    }
}