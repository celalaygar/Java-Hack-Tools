import java.io.IOException;
import java.nio.file.*;

public class USBWatcher {

    public static void main(String[] args) {
        // İzlemek istediğiniz dizini belirleyin. Bu örnekte, "E:\\" sürücüsünü izliyoruz.
        // USB takıldığında mount edilen sürücüyü belirtmelisiniz.
        Path path = Paths.get("E:\\"); 

        try (WatchService watchService = FileSystems.getDefault().newWatchService()) {
            path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

            System.out.println("USB izleyici başlatıldı. 'E:\\' sürücüsü izleniyor...");

            WatchKey key;
            while ((key = watchService.take()) != null) {
                for (WatchEvent<?> event : key.pollEvents()) {
                    System.out.println("Hello World! " + event.context() + " USB'ye takıldı.");
                }
                key.reset();
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
