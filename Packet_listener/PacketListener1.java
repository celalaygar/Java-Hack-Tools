import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class PacketListener {

    public static void main(String[] args) {
        try {
            // Paket dinlemek istediğimiz port numarası
            int port = 80;

            // Yeni bir soket oluşturuyoruz
            Socket socket = new Socket(InetAddress.getLocalHost(), port);

            // Soketten gelen verileri okuyan bir Scanner nesnesi oluşturuyoruz
            Scanner scanner = new Scanner(socket.getInputStream());

            System.out.println("HTTP Paket Dinleyici Başlatıldı...");

            // Paketleri dinlemek için sonsuz bir döngü başlatıyoruz
            while (true) {
                // Soketten gelen verileri satır satır okuyoruz
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    System.out.println("Paket: " + line); // Paketi ekrana yazdırıyoruz
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
