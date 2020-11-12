package de.snailshell.imageio.psc;

import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import static de.snailshell.imageio.ImageAsserts.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PSCompressedReaderTest {

    @Test
    void reads_PaintShop_Compressed_image() throws IOException {

        // Given

        var pscFile = new File("src/test/resources/images/original/poster.psc");
        var expected = ImageIO.read(new File("src/test/resources/images/expected/poster.png"));

        // When

        var psc = ImageIO.read(pscFile);

        // Then

        assertNotNull(psc);
        assertEquals(expected, psc);
    }
}
