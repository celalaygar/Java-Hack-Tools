import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetworkScanner {

    public static void main(String[] args) {
        String subnet = "192.168.1"; // Taranacak ağın alt ağı (subnet) adresi

        // Belirtilen subnet üzerindeki IP adreslerini tarıyoruz
        for (int i = 1; i <= 255; i++) {
            String host = subnet + "." + i;
            try {
                InetAddress inetAddress = InetAddress.getByName(host);
                if (inetAddress.isReachable(1000)) { // 1 saniye (1000 ms) içinde erişilebilir ise
                    System.out.println("Erişilebilir: " + host);
                }
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
