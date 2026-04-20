package app;

import interfaces.Laptop;
import model.LaptopUser;
import model.Lenovo;
import model.MacBook; 
import model.Toshiba;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        Laptop lenovo = new Lenovo();
        Laptop macbook = new MacBook();
        Laptop toshiba = new Toshiba();

        // Pilih/ganti Argument/Parameter sesuai kebutuhan
        LaptopUser andri = new LaptopUser(macbook);

        boolean isRunning = true;

        System.out.println("=== KONTROL LAPTOP ===");
        System.out.println("Ketik 'ON'   : Menyalakan laptop");
        System.out.println("Ketik 'OFF'  : Mematikan laptop");
        System.out.println("Ketik 'UP'   : Menambah volume");
        System.out.println("Ketik 'DOWN' : Mengurangi volume");
        System.out.println("Ketik 'EXIT' : Keluar dari program");
        System.out.println("======================");

        while (isRunning) {
            System.out.print("\nMasukkan perintah: ");
            String input = scanner.nextLine().toUpperCase();

            switch (input) {
                case "ON":
                    andri.turnOnLaptop();
                    break;
                case "OFF":
                    andri.turnOffLaptop();
                    break;
                case "UP":
                    andri.makeLaptopLouder();
                    break;
                case "DOWN":
                    andri.makeLaptopSilence();
                    break;
                case "EXIT":
                    System.out.println("Program dihentikan.");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Perintah tidak dikenali. Silakan coba lagi.");
                    break;
            }
        }
        
        scanner.close();
    }
}