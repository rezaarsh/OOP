import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== SIMULASI SISTEM KAMPUS ===");

        // 1. Input Data Mahasiswa
        System.out.println("\n-- Registrasi Mahasiswa --");
        System.out.print("Masukkan Nama Mahasiswa: ");
        String studentName = scanner.nextLine();
        System.out.print("Masukkan Alamat Mahasiswa: ");
        String studentAddress = scanner.nextLine();
        Student student = new Student(studentName, studentAddress);

        // 2. Input Data Dosen
        System.out.println("\n-- Registrasi Dosen --");
        System.out.print("Masukkan Nama Dosen: ");
        String teacherName = scanner.nextLine();
        System.out.print("Masukkan Alamat Dosen: ");
        String teacherAddress = scanner.nextLine();
        Teacher teacher = new Teacher(teacherName, teacherAddress);

        // Menampilkan Info Dasar
        System.out.println("\n" + student.toString());
        System.out.println(teacher.toString());

        // 3. Simulasi Dosen Menambah Mata Kuliah
        System.out.println("\n-- Setup Mata Kuliah Dosen --");
        System.out.print("Masukkan Mata Kuliah yang diampu : ");
        String course1 = scanner.nextLine();
        System.out.print("Masukkan Mata Kuliah lain : ");
        String course2 = scanner.nextLine();

        System.out.println("Status tambah " + course1 + ": " + teacher.addCourse(course1));
        System.out.println("Status tambah " + course2 + ": " + teacher.addCourse(course2));
        
        // Tes tambah mata kuliah yang sama untuk mengetes fungsi boolean false
        System.out.println("Coba tambah " + course1 + " lagi: " + teacher.addCourse(course1) + " karena sudah ada");

        // 4. Simulasi Mahasiswa Mengambil Nilai
        System.out.println("\n-- Input Nilai Mahasiswa --");
        System.out.print("Masukkan nilai untuk " + course1 + ": ");
        int grade1 = scanner.nextInt();
        student.addCourseGrade(course1, grade1);
        
        System.out.print("Masukkan nilai untuk " + course2 + ": ");
        int grade2 = scanner.nextInt();
        student.addCourseGrade(course2, grade2);

        // 5. Menampilkan Hasil Akhir (Sesuai Syarat Kelengkapan Fungsi)
        System.out.println("\n=== HASIL AKHIR MAHASISWA ===");
        student.printGrades();
        System.out.println("Rata-rata Nilai: " + student.getAverageGrade());

        System.out.println("\n=== SIMULASI HAPUS MATKUL DOSEN ===");
        System.out.println("Status hapus " + course2 + ": " + teacher.removeCourse(course2));
        System.out.println("Status hapus matkul ngawur: " + teacher.removeCourse("Matkul"));

        scanner.close();
    }
}