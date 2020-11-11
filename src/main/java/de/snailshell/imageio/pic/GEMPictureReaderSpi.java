package de.snailshell.imageio.pic;

import javax.imageio.ImageReader;
import javax.imageio.spi.ImageReaderSpi;
import java.io.IOException;
import java.util.Locale;

public class GEMPictureReaderSpi extends ImageReaderSpi {

    @Override
    public boolean canDecodeInput(Object source) throws IOException {
        return false;
    }

    @Override
    public ImageReader createReaderInstance(Object extension) throws IOException {
        return null;
    }

    @Override
    public String getDescription(Locale locale) {
        return null;
    }
}
