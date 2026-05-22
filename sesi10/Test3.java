import java.io.*;

public class Test3 {
    public void methodA() {
        System.out.println("Method A");
    }

    public void methodB() throws IOException { // method ini mendeklarasikan throws IOException
        System.out.println(20 / 0); // terjadi ArithmeticException karena pembagian dengan nol
        System.out.println("Method B"); // unreachable code karena baris sebelumnya pasti error
    }
}

class Utama {
    public static void main(String[] args) {
        Test3 o = new Test3(); // membuat object dari class Test3
        o.methodA(); // memanggil methodA()
        try {
            o.methodB(); // memanggil methodB()
        } catch (Exception e) {
            System.out.println("Error di Method B"); // menampilkan pesan jika terjadi error
        } finally {
            System.out.println("Ini selalu dicetak"); // pesan ini akan selalu tampil
        };
    }
}