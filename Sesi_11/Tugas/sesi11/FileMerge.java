import java.io.*;

public class FileMerge {
    public static void main(String[] args) {
        String[] files = {"file1.txt", "file2.txt"};
        String mergedFile = "merge.txt";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(mergedFile))) {
            for (String file : files) {
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        bw.write(line);
                        bw.newLine();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
