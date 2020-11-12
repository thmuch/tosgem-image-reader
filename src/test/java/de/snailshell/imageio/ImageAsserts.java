package de.snailshell.imageio;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ImageAsserts {

    public static void assertEquals(BufferedImage expected, BufferedImage actual) throws IOException {

        var expectedBytes = new ByteArrayOutputStream();
        var actualBytes = new ByteArrayOutputStream();

        ImageIO.write(expected, "png", expectedBytes);
        ImageIO.write(actual, "png", actualBytes);

        assertArrayEquals(expectedBytes.toByteArray(), actualBytes.toByteArray());
    }
}
