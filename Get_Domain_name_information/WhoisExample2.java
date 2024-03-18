import java.net.InetAddress;
import java.net.UnknownHostException;


/*
Ayrıca
- Aşşağıdaki kod parçasında, "example.com" domain adının IP adresi, 
host adı ve tam domain adı alınmaktadır. 
- InetAddress.getByName() yöntemi kullanılarak domain adına karşılık gelen 
InetAddress nesnesi alınır.
- Ardından, getHostAddress(), getHostName() ve getCanonicalHostName() 
yöntemleriyle ilgili bilgiler alınır ve konsola yazdırılır.
*/


public class DomainInfoExample {
    public static void main(String[] args) {
        String domainName = "example.com"; // Bilgilerini almak istediğiniz domain adı
        
        try {
            InetAddress address = InetAddress.getByName(domainName);
            
            // IP adresini almak
            String ipAddress = address.getHostAddress();
            System.out.println("IP Adresi: " + ipAddress);
            
            // Host adını almak
            String hostName = address.getHostName();
            System.out.println("Host Adı: " + hostName);
            
            // Tam domain adını almak
            String canonicalHostName = address.getCanonicalHostName();
            System.out.println("Tam Domain Adı: " + canonicalHostName);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
