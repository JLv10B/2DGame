import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.BufferedImage;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.practice.GameBlah;

public class exampleTest {
    
    @Test
    @DisplayName("Loaded player sprite should have correct dimensions")
    void testImportImg() {
        String testPath = "/Sprites/1-Player-Bomb Guy/1-Idle/1.png";
        GameBlah gamePanel = new GameBlah(testPath);
        BufferedImage result = gamePanel.getImg();

        assertNotNull(result, "Buffered Image should not be null");

        int expectedHeight = 58;
        int expectedWidth = 58;
        assertEquals(expectedHeight, result.getHeight(), "Image should be " + expectedHeight);
        assertEquals(expectedWidth, result.getWidth(), "Image should be " + expectedWidth);

    }

}