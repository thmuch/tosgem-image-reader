package de.snailshell.imageio.psc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import static de.snailshell.imageio.ImageAsserts.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PSCompressedReaderTest {

    @ParameterizedTest
    @ValueSource(strings = {"poster", "label", "montag", "pfeile"})
    void reads_PaintShop_Compressed_image(String imageName) throws IOException {

        // Given

        var pscFile = new File("src/test/resources/images/original/psc/" + imageName + ".psc");
        var expected = ImageIO.read(new File("src/test/resources/images/expected/" + imageName + ".png"));

        // When

        var psc = ImageIO.read(pscFile);

        // Then

        assertNotNull(psc);
        assertEquals(expected, psc);
    }

    @Test
    void reads_uncompressed_PSC_image() throws IOException {

        // Given

        var pscFile = new File("src/test/resources/images/original/psc/titlbild.psc");
        var expected = ImageIO.read(new File("src/test/resources/images/expected/titlbild.png"));

        // When

        var psc = ImageIO.read(pscFile);

        // Then

        assertNotNull(psc);
        assertEquals(expected, psc);
    }
}
