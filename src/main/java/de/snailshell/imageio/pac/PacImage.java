package de.snailshell.imageio.pac;

import de.snailshell.imageio.MonochromeDecoder;
import de.snailshell.imageio.PixelValue;

import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PacImage extends MonochromeDecoder {

    static final int WIDTH = 640;
    static final int HEIGHT = 400;

    static final int HORIZONTALLY_PACKED = '5';
    static final int VERTICALLY_PACKED = '6';

    private static final int TOTAL_PIXEL_COUNT = WIDTH * HEIGHT;

    private final int[] pixels = new int[TOTAL_PIXEL_COUNT];

    private int offset = 0;
    private int orientation;

    BufferedImage decode(ImageInputStream inputStream) throws IOException {

        inputStream.skipBytes(3);

        this.orientation = inputStream.read();

        final int idByte = inputStream.read();
        final int packByte = inputStream.read();
        final int specialByte = inputStream.read();

        // we follow the algorithm as described on http://www.fileformat.info/format/atari/egff.htm
        // (with the correction that we also repeat the "special byte" RunCount_+_1_ times)

        while (true) {
            int nextByte = inputStream.read();

            if (nextByte == -1) {

                return bufferedImageFrom(WIDTH, HEIGHT, pixels);

            } else if (nextByte == idByte) {

                int runCount = inputStream.read();

                for (int i = 0; i <= runCount; i++) {
                    byte2pix(packByte);
                }

            } else if (nextByte == specialByte) {

                int runValue = inputStream.read();
                int runCount = inputStream.read();

                for (int i = 0; i <= runCount; i++) {
                    byte2pix(runValue);
                }

            } else {
                byte2pix(nextByte);
            }
        }
    }

    private void byte2pix(int bits) {
        int mask = 128;
        int pixelOffset = offset;

        while (mask > 0) {
            if ((bits & mask) > 0) {
                pixels[pixelOffset] = PixelValue.BLACK;
            } else {
                pixels[pixelOffset] = PixelValue.WHITE;
            }

            pixelOffset++;
            mask >>= 1;
        }

        incOffset();
    }

    private void incOffset() {
        if (packedHorizontally()) {
            shiftOffsetByColumns();
        } else {
            shiftOffsetByRow();
        }
    }

    private void shiftOffsetByColumns() {
        offset += 8;
    }

    private void shiftOffsetByRow() {
        offset += WIDTH;

        if (offset >= TOTAL_PIXEL_COUNT) {
            offset %= TOTAL_PIXEL_COUNT;
            shiftOffsetByColumns();
        }
    }

    private boolean packedHorizontally() {
        return (orientation != VERTICALLY_PACKED);
    }
}

