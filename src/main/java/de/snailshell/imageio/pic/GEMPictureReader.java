package de.snailshell.imageio.pic;

import de.snailshell.imageio.MonochromeReader;

import javax.imageio.ImageReadParam;
import javax.imageio.spi.ImageReaderSpi;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GEMPictureReader extends MonochromeReader {

    static final String DESCRIPTION = "ImageIO reader for monochrome GEM 32000 bytes 640x400 *.PIC (*.DOO) images";

    GEMPictureReader(ImageReaderSpi originatingProvider) {
        super(originatingProvider);
    }

    @Override
    public int getWidth(int imageIndex) {
        return GEMPicture.WIDTH;
    }

    @Override
    public int getHeight(int imageIndex) {
        return GEMPicture.HEIGHT;
    }

    @Override
    public BufferedImage read(int imageIndex, ImageReadParam param) throws IOException {
        return new GEMPicture().decode((ImageInputStream) input);
    }
}
