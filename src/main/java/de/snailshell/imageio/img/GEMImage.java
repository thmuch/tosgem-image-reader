package de.snailshell.imageio.img;

import de.snailshell.imageio.MonochromeDecoder;
import de.snailshell.imageio.PixelValue;

import javax.imageio.IIOException;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GEMImage extends MonochromeDecoder {

    private static final int MAX_PATTERNS = 8;

    BufferedImage decode(ImageInputStream inputStream) throws IOException {

        int version = inputStream.read() * 256 + inputStream.read();
        int headlen = inputStream.read() * 256 + inputStream.read();
        int planes = inputStream.read() * 256 + inputStream.read();

        if ((version != 1) || (headlen < 8) || (planes != 1)) {
            throw new IIOException("Can read monochrome GEM IMGs only");
        }
        ;

        int pat_run = inputStream.read() * 256 + inputStream.read();

        int px_width = inputStream.read() * 256 + inputStream.read();
        int px_height = inputStream.read() * 256 + inputStream.read();

        int sl_width = inputStream.read() * 256 + inputStream.read();
        int sl_height = inputStream.read() * 256 + inputStream.read();

        if (headlen > 8) inputStream.skipBytes(headlen * 2 - 16);

        int pix[] = new int[sl_width * sl_height];
        int pattern[] = new int[MAX_PATTERNS];

        int l_buflen = (sl_width + 7) >> 3;
        int max_pat = (pat_run < MAX_PATTERNS) ? pat_run : MAX_PATTERNS;
        int zeile = 0;
        int dst = 0;

        while (zeile < sl_height) {
            int vrc = 1;
            int bytecols = l_buflen;
            int line_ptr = dst;

            while (bytecols > 0) {
                int wert = inputStream.read();
                switch (wert) {
                    case 0:
                        wert = inputStream.read();
                        if (wert == 0) {
                            wert = inputStream.read();
                            if (wert == 0xff) vrc = inputStream.read();
                        } else {
                            bytecols -= wert * pat_run;
                            for (int i = 0; i < max_pat; i++) pattern[i] = inputStream.read();

                            while (wert > 0) {
                                for (int i = 0; i < max_pat; i++) {
                                    byte2pix(pix, pattern[i], line_ptr);
                                    line_ptr += 8;
                                }
                                wert--;
                            }
                        }
                        break;

                    case 0x80:
                        wert = inputStream.read();
                        bytecols -= wert;
                        while (wert > 0) {
                            byte2pix(pix, inputStream.read(), line_ptr);
                            line_ptr += 8;
                            wert--;
                        }
                        break;

                    default:
                        int len = wert & 0x7f;
                        if (len > 0) {
                            bytecols -= len;
                            wert = ((wert & 0x80) > 0) ? PixelValue.BLACK : PixelValue.WHITE;
                            for (int i = 0; i < (8 * len); i++) pix[line_ptr++] = wert;
                        }
                        break;
                }
            }

            if (vrc > 1) {
                for (int cnt = 1; cnt < vrc; cnt++) {
                    line_ptr = dst;
                    int rast_ptr = dst + cnt * sl_width;

                    for (int i = 0; i < sl_width; i++) pix[rast_ptr++] = pix[line_ptr++];
                }
            }

            dst += vrc * sl_width;
            zeile += vrc;
        }

        int outputWidth = sl_width;
        int outputHeight = (int) Math.round(px_height * sl_height / (double) px_width);

        return bufferedImageFrom(sl_width, sl_height, outputWidth, outputHeight, pix);
    }


    private void byte2pix(int pix[], int bits, int offs) {
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
}
