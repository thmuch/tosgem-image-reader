package de.snailshell.imageio.img;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import static de.snailshell.imageio.ImageAsserts.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GEMImageReaderTest {

    @ParameterizedTest
    @ValueSource(strings = {"tiger", "grafprog"})
    void reads_monochrome_GEM_image(String imageName) throws IOException {

        // Given

        var gemImageFile = new File("src/test/resources/images/original/img/" + imageName + ".img");
        var expected = ImageIO.read(new File("src/test/resources/images/expected/" + imageName + ".png"));

        // When

        var gemImage = ImageIO.read(gemImageFile);

        // Then

        assertNotNull(gemImage);
        assertEquals(expected, gemImage);
    }
}
