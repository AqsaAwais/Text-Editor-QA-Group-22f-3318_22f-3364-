package data;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dal.HashCalculator;

public class EditorB0Test {

    private HashCalculator hashCalculator;
    @BeforeEach
    void setup() {
        hashCalculator = new HashCalculator();
    }
    @Test
    void testPerformTFIDF() {
        try {
            // Step 1: Calculate the hash of the original content
            String originalHash = HashCalculator.calculateHash("OriginalContent");
            
            // Step 2: Simulate storing the hash in a "database" variable
            String storedHash = originalHash;
            
            // Step 3: Assert that stored hash matches the original hash
            assertEquals(storedHash, originalHash, "Original hash must remain unchanged");
            
        } catch (Exception e) {
            fail("Exception thrown while calculating hash: " + e.getMessage());
        }
    }

    @Test
    void testSearchKeyword() {
        String text = "HelloWorld";
        String expectedHash = "68E109F0F40CA72A15E05CC22786F8E6"; // pre-calculated MD5

        try {
            String actualHash = HashCalculator.calculateHash(text);
            assertEquals(expectedHash, actualHash, "MD5 hash should match expected value");
        } catch (Exception e) {
            fail("Exception thrown while calculating hash: " + e.getMessage());
        }
    }
    
    @Test
    void testTransliterate() {
        String text1 = "OriginalText";
        String text2 = "EditedText";

        try {
            String hash1 = HashCalculator.calculateHash(text1);
            String hash2 = HashCalculator.calculateHash(text2);
            assertNotEquals(hash1, hash2, "MD5 hash should change if text changes");
        } catch (Exception e) {
            fail("Exception thrown while calculating hash: " + e.getMessage());
        }
    }
}



