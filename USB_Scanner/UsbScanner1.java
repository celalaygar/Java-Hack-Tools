import javax.usb.*;
import java.util.List;

/*
Detects USB devices plugged into your computer. 
This code lists USB devices using the javax.usb library.

javax.usb provides the Java USB API and is often distributed as javax-usb-api.jar 
and javax-usb-ri.jar files. We can find USB devices using these libraries.

This code defines a method called listDevices to scan for USB devices 
using the javax.usb library. In the main method, it starts 
the USB connection manager and gets the root USB hub. It then passes 
the root hub to scan for connected USB devices using the listDevices method.


*/


public class USBScanner {

    public static void main(String[] args) {
        try {
            // USB bağlantı yöneticisini başlat
            UsbServices services = UsbHostManager.getUsbServices();

            // Bağlı USB aygıtlarının kök düğümünü al
            UsbHub rootHub = services.getRootUsbHub();

            // USB aygıtlarını taramak için özel bir metodu çağır
            listDevices(rootHub);
        } catch (UsbException e) {
            e.printStackTrace();
        }
    }

    public static void listDevices(UsbHub hub) throws UsbException {
        // Bağlı aygıt listesini al
        List<UsbDevice> devices = hub.getAttachedUsbDevices();

        // Her bir aygıt için işlem yap
        for (UsbDevice device : devices) {
            System.out.println("USB Device: " + device);
            if (device.isUsbHub()) {
                // Eğer bir USB hub ise, bu hub'ın altındaki aygıtları kontrol et
                listDevices((UsbHub) device);
            }
        }
    }
}
