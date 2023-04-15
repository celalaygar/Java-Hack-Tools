import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keylogger implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {
        // Klavye tuşuna basıldığında çalışacak kod
        System.out.println("Tuş basıldı: " + e.getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Klavye tuşuna basıldığında çalışacak kod
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Klavye tuşundan el çekildiğinde çalışacak kod
    }

    public static void main(String[] args) {
        Keylogger keylogger = new Keylogger();
        // Klavye tuşlarını dinlemek istediğimiz bileşeni eklemek (Örneğin bir JFrame veya bir JPanel)
        // Bu örnekte, sadece keylogger'ın dinlemesi için kendi kendine yeterli bir bileşen oluşturduk.
        // Gerçek bir uygulamada, kullanıcı arabiriminizi veya özel bileşenlerinizi dinlemek isteyebilirsiniz.
        javax.swing.JFrame frame = new javax.swing.JFrame();
        javax.swing.JTextField textField = new javax.swing.JTextField();
        frame.add(textField);
        textField.addKeyListener(keylogger);
        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setVisible(true);
    }
}
