import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class PortScanner {
    public static void main(String[] args) {
        String ipAddress = "127.0.0.1"; // Hedef IP adresi
        int startPort = 1; // Başlangıç port numarası
        int endPort = 65535; // Bitiş port numarası

        System.out.println("Port Scanner Başlatıldı...");
        System.out.println("Hedef IP: " + ipAddress);
        System.out.println("Başlangıç Port: " + startPort);
        System.out.println("Bitiş Port: " + endPort);

        for (int port = startPort; port <= endPort; port++) {
            try {
                // Hedef IP ve port numarası ile bir soket oluştur
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(ipAddress, port), 1000); // Timeout: 1 saniye

                // Port açıksa ekrana yazdır
                System.out.println("Port " + port + " açık");
                socket.close();
            } catch (IOException e) {
                // Port kapalıysa hata alınacak, bu normaldir
            }
        }

        System.out.println("Port Scanner Tamamlandı.");
    }
}




