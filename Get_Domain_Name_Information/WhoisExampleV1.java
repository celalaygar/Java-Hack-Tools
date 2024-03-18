import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class WhoisExample {
    public static void main(String[] args) {
        String domainName = "example.com"; // Sorgulamak istediğiniz domain adı
        
        try {
            // WHOIS sunucusuna bağlantı yapılıyor
            Socket socket = new Socket("whois.verisign-grs.com", 43);
            
            // WHOIS sunucusuna sorgu gönderiliyor
            String query = domainName + "\r\n";
            socket.getOutputStream().write(query.getBytes("UTF-8"));
            
            // Sunucudan gelen yanıtı okuyoruz
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;
            
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            
            // Kaynakları temizle
            reader.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
