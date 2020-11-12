package de.snailshell.imageio.pic;

import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GEMPictureReaderTest {

    @Test
    void reads_monochrome_GEM_picture() throws IOException {

        // Given

        var gemPictureFile = new File("src/test/resources/images/original/poster.pic");
        var expected = ImageIO.read(new File("src/test/resources/images/expected/poster.png"));

        // When

        var gemPicture = ImageIO.read(gemPictureFile);

        // Then

        assertNotNull(gemPicture);
        assertEquals(expected, gemPicture);
    }

    private void assertEquals(BufferedImage expected, BufferedImage actual) throws IOException {

        var expectedBytes = new ByteArrayOutputStream();
        var actualBytes = new ByteArrayOutputStream();

        ImageIO.write(expected, "png", expectedBytes);
        ImageIO.write(actual, "png", actualBytes);

        assertArrayEquals(expectedBytes.toByteArray(), actualBytes.toByteArray());
    }
}
