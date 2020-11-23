package de.snailshell.imageio.pac;

import javax.imageio.ImageReader;
import javax.imageio.spi.ImageReaderSpi;
import javax.imageio.stream.ImageInputStream;
import java.io.IOException;
import java.util.Locale;

public class PacReaderSpi extends ImageReaderSpi {

    private static final String VENDOR_NAME = "Thomas Much";
    private static final String VERSION = "2020-11-23";
    private static final String READER_CLASS_NAME = PacReader.class.getName();
    private static final String[] NAMES = {"STAD-PAC"};
    private static final String[] SUFFIXES = {"pac"};
    private static final String[] MIME_TYPES = {"image/x-stad-pac"};
    private static final String[] WRITER_SPI_NAMES = {};

    public PacReaderSpi() {
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

        byte[] header = new byte[4];

        stream.readFully(header);

        stream.reset();

        return (header[0] == 'p') && (header[1] == 'M') && (header[2] == '8')
                && ((header[3] == PacImage.HORIZONTALLY_PACKED) || (header[3] == PacImage.VERTICALLY_PACKED));
    }

    @Override
    public ImageReader createReaderInstance(Object extension) {
        return new PacReader(this);
    }

    @Override
    public String getDescription(Locale locale) {
        return PacReader.DESCRIPTION;
    }
}
