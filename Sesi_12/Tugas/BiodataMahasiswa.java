import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Class mengimplementasikan ActionListener untuk menangani event klik
public class BiodataMahasiswa extends JFrame implements ActionListener {
    
    // Deklarasi Komponen UI
    private JTextField txtNim, txtNama, txtProdi;
    private JButton btnTampilkan, btnReset;
    private JTextArea txtOutput;

    public BiodataMahasiswa() {
        // 1. Inisialisasi Frame Utama
        super("Aplikasi Biodata Mahasiswa"); // Judul Frame
        setSize(550, 450); // Mengatur ukuran frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Agar program berhenti saat disilang
        setLocationRelativeTo(null); // Menempatkan frame di tengah layar
        setLayout(new BorderLayout(10, 10)); // Membagi area menjadi 5 bagian

        // 2. Panel Input Data (Menggunakan GridLayout)
        // Disusun dalam bentuk matriks 3 baris, 2 kolom
        JPanel panelInput = new JPanel(new GridLayout(3, 2, 5, 10)); 
        panelInput.setBorder(BorderFactory.createTitledBorder("Input Data"));

        panelInput.add(new JLabel("NIM"));
        txtNim = new JTextField();
        panelInput.add(txtNim);

        panelInput.add(new JLabel("Nama"));
        txtNama = new JTextField();
        panelInput.add(txtNama);

        panelInput.add(new JLabel("Program Studi"));
        txtProdi = new JTextField();
        panelInput.add(txtProdi);

        // 3. Panel Tombol (Menggunakan FlowLayout)
        // Secara default, komponen diletakkan mengalir dari kiri ke kanan (tengah)
        JPanel panelTombol = new JPanel(new FlowLayout()); 
        btnTampilkan = new JButton("Tampilkan");
        btnReset = new JButton("Reset");

        // Registrasi Listener agar tombol bereaksi saat diklik
        btnTampilkan.addActionListener(this);
        btnReset.addActionListener(this);

        panelTombol.add(btnTampilkan);
        panelTombol.add(btnReset);

        // Menggabungkan Panel Input dan Tombol di bagian atas
        JPanel panelAtas = new JPanel(new BorderLayout());
        panelAtas.add(panelInput, BorderLayout.CENTER);
        panelAtas.add(panelTombol, BorderLayout.SOUTH);

        // 4. Panel Output
        JPanel panelOutput = new JPanel(new BorderLayout());
        panelOutput.setBorder(BorderFactory.createTitledBorder("Output"));
        txtOutput = new JTextArea(10, 30);
        txtOutput.setEditable(false); // Output tidak boleh diedit manual oleh user
        txtOutput.setFont(new Font("Monospaced", Font.PLAIN, 12)); // Font agar sejajar
        
        // Menambahkan Scrollbar jika teks terlalu panjang
        panelOutput.add(new JScrollPane(txtOutput), BorderLayout.CENTER); 

        // 5. Menyusun semua panel ke dalam Frame
        add(panelAtas, BorderLayout.NORTH);
        add(panelOutput, BorderLayout.CENTER);

        // Menampilkan frame pada layar monitor
        setVisible(true); 
    }

    // 6. Method untuk mengendalikan ActionEvent yang terjadi
    @Override
    public void actionPerformed(ActionEvent e) {
        // Jika tombol Tampilkan diklik
        if (e.getSource() == btnTampilkan) {
            String nim = txtNim.getText();
            String nama = txtNama.getText();
            String prodi = txtProdi.getText();

            // Format string output
            String output = "========== BIODATA MAHASISWA ==========\n\n" +
                            "NIM\t\t: " + nim + "\n" +
                            "Nama\t\t: " + nama + "\n" +
                            "Program Studi\t: " + prodi + "\n";
                            
            txtOutput.setText(output);
        } 
        // Jika tombol Reset diklik
        else if (e.getSource() == btnReset) {
            txtNim.setText("");
            txtNama.setText("");
            txtProdi.setText("");
            txtOutput.setText("");
            txtNim.requestFocus(); // Mengembalikan kursor ke kolom NIM
        }
    }

    // Main Method
    public static void main(String[] args) {
        // Menjalankan aplikasi
        new BiodataMahasiswa();
    }
}