package de.snailshell.imageio.psc;

import de.snailshell.imageio.MonochromeDecoder;

import javax.imageio.IIOException;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PSCompressed extends MonochromeDecoder {

    BufferedImage decode(ImageInputStream inputStream) throws IOException {

        inputStream.skipBytes(9);

        int headerWords = inputStream.read();

        int width = inputStream.readShort() + 1;
        int height = inputStream.readShort() + 1;

        if (headerWords > 1) {
            inputStream.skipBytes((headerWords - 1) * 2);
        }

        int pixels[] = readPixelsFrom(inputStream, width, height);

        return bufferedImageFrom(width, height, pixels);
    }

    private int[] readPixelsFrom(ImageInputStream inputStream, int width, int height) throws IOException {

        PscImage pscImage = new PscImage(width, height);

        int dataBytesPerLine = (width + 7) / 8;

        while (true) {
            int controlByte = inputStream.read();

            switch (controlByte) {
                case -1 -> throw new IIOException("Unexpected end of file");

                case 0 -> pscImage.addWhiteLine();

                case 10 -> {
                    int count = inputStream.read();
                    pscImage.repeatLastLine(count + 1);
                }

                case 12 -> {
                    int count = inputStream.read();
                    pscImage.repeatLastLine(count + 256);
                }

                case 99 -> {
                    int linePattern[] = new int[dataBytesPerLine];

                    for (int y = 0; y < height; y++) {

                        for (int i = 0; i < dataBytesPerLine; i++) {
                            linePattern[i] = inputStream.read();
                        }

                        pscImage.addPatternLine(linePattern);
                    }
                }

                case 100 -> {
                    int bytePattern[] = new int[]{inputStream.read()};
                    pscImage.addPatternLine(bytePattern);
                }

                case 102 -> {
                    int wordPattern[] = new int[]{inputStream.read(), inputStream.read()};
                    pscImage.addPatternLine(wordPattern);
                }

                case 110 -> {
                    int linePattern[] = new int[dataBytesPerLine];

                    for (int i = 0; i < dataBytesPerLine; i++) {
                        linePattern[i] = inputStream.read();
                    }

                    pscImage.addPatternLine(linePattern);
                }

                case 200 -> pscImage.addBlackLine();

                case 255 -> {
                    return pscImage.getPixels();
                }

                default -> throw new IIOException("Unexpected control byte " + controlByte);
            }
        }
    }
}
