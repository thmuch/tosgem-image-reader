package de.snailshell.imageio.img;

import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import static de.snailshell.imageio.ImageAsserts.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GEMImageReaderTest {

    @Test
    void reads_monochrome_GEM_image() throws IOException {

        // Given

        var gemImageFile = new File("src/test/resources/images/original/tiger.img");
        var expected = ImageIO.read(new File("src/test/resources/images/expected/tiger.png"));

        // When

        var gemImage = ImageIO.read(gemImageFile);

        // Then

        assertNotNull(gemImage);
        assertEquals(expected, gemImage);
    }
}
