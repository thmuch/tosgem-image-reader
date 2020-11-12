package de.snailshell.imageio.img;

import javax.imageio.ImageReader;
import javax.imageio.spi.ImageReaderSpi;
import javax.imageio.stream.ImageInputStream;
import java.io.IOException;
import java.util.Locale;

public class GEMImageReaderSpi extends ImageReaderSpi {

    private static final String VENDOR_NAME = "Thomas Much";
    private static final String VERSION = "1996-08-26";
    private static final String READER_CLASS_NAME = GEMImageReader.class.getName();
    private static final String[] NAMES = {"GEM image"};
    private static final String[] SUFFIXES = {"img"};
    private static final String[] MIME_TYPES = {"image/x-gemimage"};
    private static final String[] WRITER_SPI_NAMES = {};

    public GEMImageReaderSpi() {
        super(VENDOR_NAME,
                VERSION,
                NAMES,
                SUFFIXES,
                MIME_TYPES,
                READER_CLASS_NAME,
                STANDARD_INPUT_TYPE,
                WRITER_SPI_NAMES,
                false,
                null,
                null,
                null,
                null,
                false,
                null,
                null,
                null,
                null);
    }

    @Override
    public boolean canDecodeInput(Object source) throws IOException {

        if (!(source instanceof ImageInputStream)) {
            return false;
        }

        ImageInputStream stream = (ImageInputStream) source;

        stream.mark();

        int version = stream.read() * 256 + stream.read();
        int headlen = stream.read() * 256 + stream.read();
        int planes = stream.read() * 256 + stream.read();

        stream.reset();

        return (version == 1) && (headlen >= 8) && (planes == 1);
    }

    @Override
    public ImageReader createReaderInstance(Object extension) {
        return new GEMImageReader(this);
    }

    @Override
    public String getDescription(Locale locale) {
        return GEMImageReader.DESCRIPTION;
    }
}
