package de.snailshell.imageio.psc;

import de.snailshell.imageio.MonochromeReader;

import javax.imageio.ImageReadParam;
import javax.imageio.spi.ImageReaderSpi;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PSCompressedReader extends MonochromeReader {

    static final String DESCRIPTION = "ImageIO reader for monochrome PaintShop Compressed files (*.PSC)";

    PSCompressedReader(ImageReaderSpi originatingProvider) {
        super(originatingProvider);
    }

    @Override
    public int getWidth(int imageIndex) throws IOException {

        ImageInputStream stream = (ImageInputStream) input;

        stream.mark();
        stream.skipBytes(10);

        int width = stream.readShort() + 1;

        stream.reset();

        return width;
    }

    @Override
    public int getHeight(int imageIndex) throws IOException {

        ImageInputStream stream = (ImageInputStream) input;

        stream.mark();
        stream.skipBytes(12);

        int height = stream.readShort() + 1;

        stream.reset();

        return height;
    }

    @Override
    public BufferedImage read(int imageIndex, ImageReadParam param) throws IOException {
        return new PSCompressed().decode((ImageInputStream) input);
    }
}
