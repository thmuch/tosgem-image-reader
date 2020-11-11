package de.snailshell.imageio.img;

import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GEMImageReaderTest {

    @Test
    void reads_monochrome_GEM_image() throws IOException {

        // Given

        File gemImageFile = new File("src/test/resources/images/tiger.img");

        // When

        BufferedImage gemImage = ImageIO.read(gemImageFile);

        // Then

        assertNotNull(gemImage);
    }
}
