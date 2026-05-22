import java.util.Scanner;
import java.util.ArrayList;

public class DemoNilai {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Nilai> daftarMahasiswa = new ArrayList<>();
        boolean lanjut = true;

        System.out.println("=== ENTRI DATA NILAI PEMROGRAMAN JAVA MAHASISWA ===");

        // --- FASE 1: INPUT DATA ---
        while (lanjut) {
            System.out.print("Masukkan NIM : ");
            String nim = input.nextLine();

            System.out.print("Masukkan Nama : ");
            String nama = input.nextLine();

            System.out.print("Masukkan Nilai : ");
            int nilai = input.nextInt();
            input.nextLine(); // Membersihkan sisa enter

            // Validasi nilai
            if (nilai >= 0 && nilai <= 100) {
                // Jika valid, buat objek baru dan simpan ke ArrayList
                Nilai mhsBaru = new Nilai(nim, nama, nilai);
                daftarMahasiswa.add(mhsBaru);
            } else {
                // Jika tidak valid, munculkan notifikasi
                System.out.println("Input nilai anda salah");
            }

            System.out.print("Tambah data lagi? (y/n): ");
            String pilihan = input.nextLine();
            if (pilihan.equalsIgnoreCase("n")) {
                lanjut = false;
            }
            System.out.println();
        }

        // --- FASE 2: CETAK DATA INDIVIDUAL ---
        for (Nilai mhs : daftarMahasiswa) {
            System.out.println("NIM  : " + mhs.getNim());
            System.out.println("Nama : " + mhs.getNama());
            System.out.println("Nilai: " + mhs.getNilai());
            System.out.println("Grade: " + mhs.getGrade());
            System.out.println("=================================================");
        }

        // --- FASE 3: REKAPITULASI ---
        if (daftarMahasiswa.isEmpty()) {
            System.out.println("Tidak ada data mahasiswa yang valid.");
            return; // Hentikan program jika tidak ada data
        }

        int totalNilai = 0;
        String detailHitungRata = ""; // Untuk mencetak "80+79+90+50"
        
        // Wadah untuk mengelompokkan nama-nama mahasiswa
        ArrayList<String> lulus = new ArrayList<>();
        ArrayList<String> tidakLulus = new ArrayList<>();
        ArrayList<String> gradeA = new ArrayList<>();
        ArrayList<String> gradeB = new ArrayList<>();
        ArrayList<String> gradeC = new ArrayList<>();
        ArrayList<String> gradeD = new ArrayList<>();
        ArrayList<String> gradeE = new ArrayList<>();

        // Memilah data mahasiswa ke masing-masing kelompok
        for (int i = 0; i < daftarMahasiswa.size(); i++) {
            Nilai mhs = daftarMahasiswa.get(i);
            String namaMhs = mhs.getNama();
            
            // Keperluan hitung rata-rata
            totalNilai += mhs.getNilai();
            detailHitungRata += mhs.getNilai();
            if (i < daftarMahasiswa.size() - 1) {
                detailHitungRata += "+";
            }

            // Pisahkan Lulus / Tidak Lulus
            if (mhs.isLulus()) {
                lulus.add(namaMhs);
            } else {
                tidakLulus.add(namaMhs);
            }

            // Pisahkan per Grade
            String grade = mhs.getGrade();
            if (grade.equals("A")) gradeA.add(namaMhs);
            else if (grade.equals("B")) gradeB.add(namaMhs);
            else if (grade.equals("C")) gradeC.add(namaMhs);
            else if (grade.equals("D")) gradeD.add(namaMhs);
            else if (grade.equals("E")) gradeE.add(namaMhs);
        }

        double rataRata = (double) totalNilai / daftarMahasiswa.size();

        // --- CETAK HASIL AKHIR ---
        System.out.println("Jumlah Mahasiswa : " + daftarMahasiswa.size());
        
        System.out.print("Jumlah Mahasiswa yg Lulus : " + lulus.size());
        if (!lulus.isEmpty()) System.out.print(" yaitu " + String.join(", ", lulus));
        System.out.println();

        System.out.print("Jumlah Mahasiswa yg Tidak Lulus : " + tidakLulus.size());
        if (!tidakLulus.isEmpty()) System.out.print(" yaitu " + String.join(", ", tidakLulus));
        System.out.println();

        // Cetak Grade secara dinamis (hanya jika ada orangnya)
        if (!gradeA.isEmpty()) System.out.println("Jumlah Mahasiswa dengan Nilai A = " + gradeA.size() + " yaitu " + String.join(", ", gradeA));
        if (!gradeB.isEmpty()) System.out.println("Jumlah Mahasiswa dengan Nilai B = " + gradeB.size() + " yaitu " + String.join(", ", gradeB));
        if (!gradeC.isEmpty()) System.out.println("Jumlah Mahasiswa dengan Nilai C = " + gradeC.size() + " yaitu " + String.join(", ", gradeC));
        if (!gradeD.isEmpty()) System.out.println("Jumlah Mahasiswa dengan Nilai D = " + gradeD.size() + " yaitu " + String.join(", ", gradeD));
        if (!gradeE.isEmpty()) System.out.println("Jumlah Mahasiswa dengan Nilai E = " + gradeE.size() + " yaitu " + String.join(", ", gradeE));

        String rataRataDiformat = String.format("%.2f", rataRata);
        System.out.println("Rata-rata nilai mahasiswa adalah : " + detailHitungRata + " / " + daftarMahasiswa.size() + " = " + rataRataDiformat);
    }
}