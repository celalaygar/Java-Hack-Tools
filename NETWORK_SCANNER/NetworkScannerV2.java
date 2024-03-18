import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/*
Java'nın InetAddress, NetworkInterface ve HardwareAddress gibi sınıflarını kullanarak 
ağ taraması yapar ve bağlı cihazların IP ve MAC adreslerini bulur.


NetworkInterface.getNetworkInterfaces() ile mevcut ağ arayüzlerini alır ve 
her bir arayüz için IP ve MAC adreslerini alır. Aldığı bilgileri ekrana basar.

*/



public class NetworkScanner {

    public static void main(String[] args) {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                if (!networkInterface.isLoopback() && networkInterface.isUp()) {
                    System.out.println("Interface: " + networkInterface.getName());
                    Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                    while (inetAddresses.hasMoreElements()) {
                        InetAddress inetAddress = inetAddresses.nextElement();
                        System.out.println("  IP Address: " + inetAddress.getHostAddress());
                        byte[] mac = networkInterface.getHardwareAddress();
                        if (mac != null) {
                            StringBuilder macAddress = new StringBuilder();
                            for (int i = 0; i < mac.length; i++) {
                                macAddress.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? ":" : ""));
                            }
                            System.out.println("  MAC Address: " + macAddress.toString());
                        } else {
                            System.out.println("  MAC Address: Not found");
                        }
                    }
                }
            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
    }
}
