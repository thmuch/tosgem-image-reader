package de.snailshell.imageio;

import javax.imageio.ImageReader;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.spi.ImageReaderSpi;
import java.awt.image.DataBuffer;
import java.util.Iterator;
import java.util.List;

public abstract class MonochromeReader extends ImageReader {

    protected MonochromeReader(ImageReaderSpi originatingProvider) {
        super(originatingProvider);
    }

    @Override
    public int getNumImages(boolean allowSearch) {
        return 1;
    }

    @Override
    public Iterator<ImageTypeSpecifier> getImageTypes(int imageIndex) {

        var monochrome = ImageTypeSpecifier.createGrayscale(1, DataBuffer.TYPE_BYTE, false);

        return List.of(monochrome).iterator();
    }

    @Override
    public IIOMetadata getStreamMetadata() {
        return null;
    }

    @Override
    public IIOMetadata getImageMetadata(int imageIndex) {
        return null;
    }
}
