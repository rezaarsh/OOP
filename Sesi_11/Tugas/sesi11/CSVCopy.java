import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CSVCopy {
    public static void main(String[] args) {
        // Tentukan nama file asal dan file tujuan
        String fileAsal = "students.csv";
        String fileTujuan = "students_copy.csv";
        String line;

        System.out.println("Menyalin data dari " + fileAsal + " ke " + fileTujuan + "...");

        // Membuka BufferedReader dan BufferedWriter sekaligus di dalam blok try
        try (
            BufferedReader br = new BufferedReader(new FileReader(fileAsal));
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileTujuan))
        ) {
            // Lakukan perulangan selama masih ada baris yang bisa dibaca dari file asal
            while ((line = br.readLine()) != null) {
                bw.write(line);   // Tulis baris tersebut ke file tujuan
                bw.newLine();     // Tambahkan baris baru agar tidak menumpuk ke samping
            }

            System.out.println("Proses penyalinan sukses! File berhasil dicopy.");

        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menyalin file.");
            e.printStackTrace();
        }
    }
}