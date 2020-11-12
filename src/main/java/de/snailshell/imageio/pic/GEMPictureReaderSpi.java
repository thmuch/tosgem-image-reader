package de.snailshell.imageio.pic;

import javax.imageio.ImageReader;
import javax.imageio.spi.ImageReaderSpi;
import javax.imageio.stream.ImageInputStream;
import java.io.IOException;
import java.util.Locale;

public class GEMPictureReaderSpi extends ImageReaderSpi {

    private static final String VENDOR_NAME = "Thomas Much";
    private static final String VERSION = "1996-08-27";
    private static final String READER_CLASS_NAME = GEMPictureReader.class.getName();
    private static final String[] NAMES = {"GEM picture"};
    private static final String[] SUFFIXES = {"pic", "doo"};
    private static final String[] MIME_TYPES = {"image/x-gempicture"};
    private static final String[] WRITER_SPI_NAMES = {};

    public GEMPictureReaderSpi() {
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

        return (stream.length() == GEMPicture.FILE_LENGTH_BYTES);
    }

    @Override
    public ImageReader createReaderInstance(Object extension) {
        return new GEMPictureReader(this);
    }

    @Override
    public String getDescription(Locale locale) {
        return GEMPictureReader.DESCRIPTION;
    }
}
