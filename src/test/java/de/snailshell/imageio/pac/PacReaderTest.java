package de.snailshell.imageio.pac;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import static de.snailshell.imageio.ImageAsserts.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PacReaderTest {

    @ParameterizedTest
    @ValueSource(strings = {"design", "shades"})
    void reads_STAD_PAC_horizontally_packed(String imageName) throws IOException {

        // Given

        var pacFile = new File("src/test/resources/images/original/pac/" + imageName + ".pac");
        var expected = ImageIO.read(new File("src/test/resources/images/expected/" + imageName + ".png"));

        // When

        var pac = ImageIO.read(pacFile);

        // Then

        assertNotNull(pac);
        assertEquals(expected, pac);
    }

    @Test
    void reads_STAD_PAC_vertically_packed() throws IOException {

        // Given

        var pacFile = new File("src/test/resources/images/original/pac/bemassng.pac");
        var expected = ImageIO.read(new File("src/test/resources/images/expected/bemassng.png"));

        // When

        var pac = ImageIO.read(pacFile);

        // Then

        assertNotNull(pac);
        assertEquals(expected, pac);
    }
}
