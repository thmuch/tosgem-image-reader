package de.snailshell.imageio.pic;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import static de.snailshell.imageio.ImageAsserts.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GEMPictureReaderTest {

    @ParameterizedTest
    @ValueSource(strings = {"poster", "shuttle"})
    void reads_monochrome_GEM_picture(String imageName) throws IOException {

        // Given

        var gemPictureFile = new File("src/test/resources/images/original/pic/" + imageName + ".pic");
        var expected = ImageIO.read(new File("src/test/resources/images/expected/" + imageName + ".png"));

        // When

        var gemPicture = ImageIO.read(gemPictureFile);

        // Then

        assertNotNull(gemPicture);
        assertEquals(expected, gemPicture);
    }
}
