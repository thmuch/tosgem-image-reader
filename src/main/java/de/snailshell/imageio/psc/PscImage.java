package de.snailshell.imageio.psc;

import de.snailshell.imageio.PixelValue;

import java.util.Arrays;

class PscImage {

    private int width;
    private int pixels[];
    private int offset;

    PscImage(int width, int height) {
        this.width = width;
        this.pixels = new int[width * height];
        this.offset = 0;
    }

    public int[] getPixels() {
        return pixels;
    }

    public void addWhiteLine() {
        addLine(PixelValue.WHITE);
    }

    public void addBlackLine() {
        addLine(PixelValue.BLACK);
    }

    private void addLine(int pixelValue) {
        Arrays.fill(pixels, offset, offset + width, pixelValue);
        incOffset(width);
    }

    public void repeatLastLine(int count) {
        for (int c = 0; c < count; c++) {
            System.arraycopy(pixels, offset - width, pixels, offset + c * width, width);
        }
        incOffset(count * width);
    }

    public void addPatternLine(int patternBytes[]) {

        final int HI_BIT = 128;

        int mask = HI_BIT;
        int patternIndex = 0;
        int patternByte = patternBytes[patternIndex];

        for (int x = 0; x < width; x++) {

            if ((patternByte & mask) > 0) {
                pixels[offset] = PixelValue.BLACK;
            } else {
                pixels[offset] = PixelValue.WHITE;
            }

            incOffset(1);

            mask >>= 1;

            if (mask == 0) {
                mask = HI_BIT;

                patternIndex++;

                if (patternIndex >= patternBytes.length) {
                    patternIndex = 0;
                }

                patternByte = patternBytes[patternIndex];
            }
        }
    }

    private void incOffset(int n) {
        offset += n;
    }
}
