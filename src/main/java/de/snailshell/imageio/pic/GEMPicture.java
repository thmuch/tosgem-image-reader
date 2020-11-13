package de.snailshell.imageio.pic;

import de.snailshell.imageio.MonochromeDecoder;
import de.snailshell.imageio.PixelValue;

import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GEMPicture extends MonochromeDecoder {

    static final int FILE_LENGTH_BYTES = 32000;

    static final int WIDTH = 640;
    static final int HEIGHT = 400;

    BufferedImage decode(ImageInputStream inputStream) throws IOException {

        int pix[] = new int[WIDTH * HEIGHT];
        int offs = 0;

        for (int i = 0; i < FILE_LENGTH_BYTES; i++) {
            int bits = inputStream.read();

            int mask = 128;

            while (mask > 0) {
                if ((bits & mask) > 0) {
                    pix[offs++] = PixelValue.BLACK;
                } else {
                    pix[offs++] = PixelValue.WHITE;
                }

                mask >>= 1;
            }
        }

        return bufferedImageFrom(WIDTH, HEIGHT, pix);
    }
}
