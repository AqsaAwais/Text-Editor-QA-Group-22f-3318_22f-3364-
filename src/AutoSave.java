import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AutoSave {

    private String filePath;

    public AutoSave(String filePath) {
        this.filePath = filePath;
    }

    public boolean save(String content) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(content);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    
    public String read() throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }
}
