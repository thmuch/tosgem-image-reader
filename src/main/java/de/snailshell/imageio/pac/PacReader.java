package de.snailshell.imageio.pac;

import de.snailshell.imageio.MonochromeReader;

import javax.imageio.ImageReadParam;
import javax.imageio.spi.ImageReaderSpi;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PacReader extends MonochromeReader {

    static final String DESCRIPTION = "ImageIO reader for monochrome STAD-PAC files (*.PAC)";

    PacReader(ImageReaderSpi originatingProvider) {
        super(originatingProvider);
    }

    @Override
    public int getWidth(int imageIndex) {
        return PacImage.WIDTH;
    }

    @Override
    public int getHeight(int imageIndex) {
        return PacImage.HEIGHT;
    }

    @Override
    public BufferedImage read(int imageIndex, ImageReadParam param) throws IOException {
        return new PacImage().decode((ImageInputStream) input);
    }
}
