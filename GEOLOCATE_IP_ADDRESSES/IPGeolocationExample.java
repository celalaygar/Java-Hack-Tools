import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

public class IPGeolocationExample {

    public static void main(String[] args) {
        try {
           /*
           DatabaseReader sınıfının bir örneğini oluşturun ve GeoIP2 veritabanı dosyasının yolunu sağlayın. 
           GeoIP2 veritabanı dosyasını da MaxMind web sitesinden indirebilirsiniz.
           */
            // GeoIP2 veritabanı dosyasını yükleyin
            File database = new File("path/to/GeoIP2-City.mmdb");
            DatabaseReader reader = new DatabaseReader.Builder(database).build();

            // Coğrafya bilgilerini almak istediğiniz IP adresini belirleyin
            String ipAddress = "123.45.67.89";

            // IP adresini InetAddress sınıfına dönüştürün
            InetAddress inetAddress = InetAddress.getByName(ipAddress);

            // GeoIP2 veritabanını sorgulayın
            CityResponse response = reader.city(inetAddress);

            // Coğrafya bilgilerini alın
            String countryName = response.getCountry().getName();
            String cityName = response.getCity().getName();

            // Bilgileri yazdırın
            System.out.println("IP Adresi: " + ipAddress);
            System.out.println("Ülke: " + countryName);
            System.out.println("Şehir: " + cityName);

        } catch (IOException | GeoIp2Exception e) {
            e.printStackTrace();
        }
    }
}
