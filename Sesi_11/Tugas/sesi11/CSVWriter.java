import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CSVWriter {
    public static void main(String[] args) {
        String csvFile = "new_students.csv";
        // String[] data = {
        //     "4,David,23",
        //     "5,Eva,22",
        //     "6,Ferdi,21"
        // };

        // Modifikasi CSVWriter.java untuk menambahkan input dari pengguna menggunakan Scanner
        Scanner input = new Scanner(System.in);

        // new FileWriter(csvFile, true) --> menambah data tanpa menghapus isi file lama (append)
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile, true))) {
            // for (String line : data) {
            //     bw.write(line);
            //     bw.newLine();
            // }
            System.out.println("=== INPUT DATA MAHASISWA ===");
            System.out.print("Masukkan NIM   : ");
            String nim = input.nextLine();
            
            System.out.print("Masukkan Nama  : ");
            String nama = input.nextLine();
            
            System.out.print("Masukkan Umur  : ");
            String umur = input.nextLine();

            String line = nim + "," + nama + "," + umur;

            bw.write(line);
            bw.newLine();
            
            System.out.println("------------------------------------------------");
            System.out.println("Data berhasil disimpan ke dalam " + csvFile);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Selalu tutup scanner di blok finally untuk mencegah kebocoran memori
            input.close();
        }
    }
}
