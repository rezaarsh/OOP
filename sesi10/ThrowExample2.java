public class ThrowExample2 {
    public static void main(String[] args) {
        try {
            throw new Exception("Here's my Exception"); // membuat dan melempar exception secara manual
        } catch (Exception e) { // menangkap exception yang terjadi
            System.out.println("Caught Exception"); // menampilkan bahwa exception berhasil ditangkap
            System.out.println("e.getMessage():" + e.getMessage()); // menampilkan pesan exception saja
            System.out.println("e.toString():" + e.toString()); // menampilkan jenis exception + pesan exception
            System.out.println("e.printStackTrace():");
            e.printStackTrace(); // menampilkan detail lengkap error dan lokasi error
        }
    }
}