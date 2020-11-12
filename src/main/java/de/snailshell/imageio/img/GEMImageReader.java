package de.snailshell.imageio.img;

import de.snailshell.imageio.MonochromeReader;

import javax.imageio.ImageReadParam;
import javax.imageio.spi.ImageReaderSpi;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GEMImageReader extends MonochromeReader {

    static final String DESCRIPTION = "ImageIO reader for monochrome GEM image files (*.IMG)";

    GEMImageReader(ImageReaderSpi originatingProvider) {
        super(originatingProvider);
    }

    @Override
    public int getWidth(int imageIndex) throws IOException {

        ImageInputStream stream = (ImageInputStream) input;

        stream.mark();
        stream.skipBytes(12);

        int sl_width = stream.read() * 256 + stream.read();

        stream.reset();

        return sl_width;
    }

    @Override
    public int getHeight(int imageIndex) throws IOException {

        ImageInputStream stream = (ImageInputStream) input;

        stream.mark();
        stream.skipBytes(14);

        int sl_height = stream.read() * 256 + stream.read();

        stream.reset();

        return sl_height;
    }

    @Override
    public BufferedImage read(int imageIndex, ImageReadParam param) throws IOException {
        return new GEMImage().decode((ImageInputStream) input);
    }
}
