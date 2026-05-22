import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessRevisi {
    public static void main(String[] args) {
        String bookList[] = {"Satu", "Dua", "Tiga"};
        int yearList[] = {1920, 1230, 1940};

        try{
            // membuka/membuat file books.txt
            // mode rw = read dan write
            RandomAccessFile books = new RandomAccessFile("books.txt", "rw");

            for (int i=0; i<3; i++){
                books.writeUTF(bookList[i]); // menulis string ke file
                books.writeInt(yearList[i]); // menulis integer ke file
            }
            books.seek(0); // memindahkan pointer ke awal file
            System.out.println(books.readUTF()+" "+books.readInt());
            System.out.println(books.readUTF()+ " "+books.readInt());
            books.close();
        }
        catch(IOException e){
            System.out.println("Indeks melebihi batas");
        }
        System.out.println("tes");
    }
}
