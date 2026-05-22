// SUPER CLASS
class Bank {
    protected String namaBank;

    // Constructor Super Class
    public Bank(String namaBank) {
        this.namaBank = namaBank;
    }

    // --- BONUS CHALLENGE: Fitur menghitung biaya transfer ---
    protected int hitungBiayaTransfer(String bankTujuan) {
        if (this.namaBank.equalsIgnoreCase(bankTujuan)) {
            return 0; // Sesama bank gratis
        } else {
            return 6500; // Beda bank kena biaya admin
        }
    }

    // Overload 1: Transfer ke rekening lain (2 parameter)
    public void transferUang(int jumlah, String rekeningTujuan) {
        System.out.println("Transfer Rp " + jumlah + " | Ke Rekening " + rekeningTujuan);
    }

    // Overload 2: Transfer ke rekening lain di bank berbeda (3 parameter)
    public void transferUang(int jumlah, String rekeningTujuan, String bankTujuan) {
        int biaya = hitungBiayaTransfer(bankTujuan);
        System.out.println("Transfer Rp " + jumlah + " | Ke Rekening " + rekeningTujuan + " (" + bankTujuan + ")" + " | Biaya Admin: Rp" + biaya);
    }

    // Overload 3: Transfer dengan tambahan berita (4 parameter)
    public void transferUang(int jumlah, String rekeningTujuan, String bankTujuan, String berita) {
        int biaya = hitungBiayaTransfer(bankTujuan);
        System.out.println("Transfer Rp " + jumlah + " | Ke Rekening " + rekeningTujuan + " (" + bankTujuan + ") " + "    | " + berita + " | Biaya Admin: Rp" + biaya);
    }

    // Method biasa (akan di override)
    public void sukuBunga() {
        System.out.println("Suku Bunga standar adalah 3%");
    }
}

// SUB CLASS 1
class BankBNI extends Bank {
    
    // Constructor Sub Class 1
    public BankBNI() {
        super("BNI"); 
    }

    @Override 
    public void sukuBunga() {
        System.out.println("Suku Bunga dari BNI adalah : 4%");
    }

    @Override 
    public void transferUang(int jumlah, String rekeningTujuan, String bankTujuan) {
        bankTujuan = "BNI"; // Memaksa bank tujuan menjadi BNI
        // Mengambil method dari Super Class
        super.transferUang(jumlah, rekeningTujuan, bankTujuan); 
    }
}

// SUB CLASS 2
class BankBCA extends Bank {
    
    // Constructor Sub Class 2
    public BankBCA() {
        super("BCA"); 
    }

    @Override 
    public void sukuBunga() {
        System.out.println("Suku Bunga dari BCA adalah : 4.5%");
    }

    @Override 
    public void transferUang(int jumlah, String rekeningTujuan, String bankTujuan) {
        bankTujuan = "BCA"; // Memaksa bank tujuan menjadi BCA
        // Mengambil method dari Super Class
        super.transferUang(jumlah, rekeningTujuan, bankTujuan);
    }
}

// PROGRAM UTAMA
public class DemoBank {
    public static void main(String[] args) {
        
        System.out.println("=== PENGUJIAN OVERLOADING & BONUS CHALLENGE BIAYA TRANSFER (SUPER CLASS) ===");
        Bank bank = new Bank("Bank Umum");
        bank.transferUang(1000000, "123456"); // Overload 1

        // Transfer ke bank berbeda dikenakan biaya admin Rp 6500
        bank.transferUang(2000000, "789012", "Mandiri"); // Overload 2
        bank.transferUang(3000000, "345678", "BRI", "Bayar SPP"); // Overload 3
        bank.sukuBunga();

        System.out.println("\n=== PENGUJIAN OVERRIDING (SUB CLASS) ===");
        BankBNI bni = new BankBNI();
        // Default value bankTujuan di override jadi BNI maka apapun inputnya bakal di ubah jadi BNI
        // karena transfer ke sesama bank biaya admin jadi Rp0
        bni.transferUang(200000, "112233", "Mandiri"); 
        bni.sukuBunga();

        System.out.println();

        BankBCA bca = new BankBCA();
        // Default value bankTujuan di override jadi BCA maka apapun inputnya bakal di ubah jadi BCA
        // karena transfer ke sesama bank biaya admin jadi Rp0
        bca.transferUang(300000, "445566", "BRI"); 
        bca.sukuBunga();
    }
}