import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class AutoSaveTest {

    private AutoSave autoSave;
    private final String testFile = "autosave_test_file.txt";

    @BeforeEach
    public void setup() {
        autoSave = new AutoSave(testFile);
    }

    @AfterEach
    public void cleanup() {
        
        File file = new File(testFile);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testSaveAndReadSimpleContent() throws IOException {
        boolean saved = autoSave.save("Hello JUnit 5 Test!");
        assertTrue(saved, "File should be saved successfully");

        String content = autoSave.read();
        assertEquals("Hello JUnit 5 Test!", content, "Read content should match saved content");
    }

    @Test
    public void testOverwriteContent() throws IOException {
        autoSave.save("First Content");
        autoSave.save("Overwritten Content");

        String content = autoSave.read();
        assertEquals("Overwritten Content", content, "Content should be overwritten with new text");
    }

    @Test
    public void testLargeContent() throws IOException {
       
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 600; i++) {
            sb.append("word").append(i).append(" ");
        }
        String largeContent = sb.toString();

        boolean saved = autoSave.save(largeContent);
        assertTrue(saved, "Large content should be saved successfully");

        String readBack = autoSave.read();
        assertEquals(largeContent, readBack, "Read content should match large saved content");
    }

    @Test
    public void testIOExceptionHandling() {
        
        AutoSave badAutoSave = new AutoSave("/invalid_path/test.txt");
        boolean saved = badAutoSave.save("Some content");
        assertFalse(saved, "Saving to invalid path should fail");
    }

    @Test
    public void testReadNonExistentFile() {
        AutoSave nonExistent = new AutoSave("non_existent_file.txt");
        assertThrows(IOException.class, nonExistent::read, "Reading non-existent file should throw IOException");
    }
}
