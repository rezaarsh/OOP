import java.util.ArrayList;
import java.util.ArrayDeque;

public class Collection {
    public static void main(String[] args) {
        
        System.out.println("=== 1. Contoh ArrayList ===");
        ArrayList<String> daftarBarang = new ArrayList<>();
        daftarBarang.add("Buku");
        daftarBarang.add("Pensil");
        daftarBarang.add("Penggaris");

        System.out.println("Isi ArrayList: " + daftarBarang);
        System.out.println("Ambil barang urutan pertama: " + daftarBarang.get(0));


        System.out.println("\n=== 2. Contoh ArrayDeque ===");
        ArrayDeque<String> antrean = new ArrayDeque<>();
        antrean.addLast("Pasien A"); // Masuk antrean dari belakang
        antrean.addLast("Pasien B");
        antrean.addFirst("Pasien Gawat Darurat"); // Nyelip langsung di paling depan

        System.out.println("Isi ArrayDeque: " + antrean);
        System.out.println("Panggil pasien terdepan: " + antrean.pollFirst());
        System.out.println("Sisa antrean sekarang: " + antrean);
    }
}