package de.snailshell.imageio.pic;

import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.spi.ImageReaderSpi;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class GEMPictureReader extends ImageReader {

    static final String DESCRIPTION = "ImageIO reader for monochrome GEM 32000 bytes 640x400 *.PIC (*.DOO) images";

    GEMPictureReader(ImageReaderSpi originatingProvider) {
        super(originatingProvider);
    }

    @Override
    public int getNumImages(boolean allowSearch) throws IOException {
        return 1;
    }

    @Override
    public int getWidth(int imageIndex) throws IOException {
        return GEMPicture.WIDTH;
    }

    @Override
    public int getHeight(int imageIndex) throws IOException {
        return GEMPicture.HEIGHT;
    }

    @Override
    public Iterator<ImageTypeSpecifier> getImageTypes(int imageIndex) throws IOException {

        var monochrome = ImageTypeSpecifier.createGrayscale(1, DataBuffer.TYPE_BYTE, false);

        return List.of(monochrome).iterator();
    }

    @Override
    public IIOMetadata getStreamMetadata() throws IOException {
        return null;
    }

    @Override
    public IIOMetadata getImageMetadata(int imageIndex) throws IOException {
        return null;
    }

    @Override
    public BufferedImage read(int imageIndex, ImageReadParam param) throws IOException {
        return new GEMPicture().decode((ImageInputStream) input);
    }
}
