package de.snailshell.imageio.pic;

import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GEMPictureReaderTest {

    @Test
    void reads_monochrome_GEM_picture() throws IOException {

        // Given

        File gemPictureFile = new File("src/test/resources/images/poster.pic");

        // When

        BufferedImage gemPicture = ImageIO.read(gemPictureFile);

        // Then

        assertNotNull(gemPicture);
    }
}
