package de.snailshell.imageio.psc;

import javax.imageio.ImageReader;
import javax.imageio.spi.ImageReaderSpi;
import javax.imageio.stream.ImageInputStream;
import java.io.IOException;
import java.util.Locale;

public class PSCompressedReaderSpi extends ImageReaderSpi {

    private static final String VENDOR_NAME = "Thomas Much";
    private static final String VERSION = "2020-11-13";
    private static final String READER_CLASS_NAME = PSCompressedReader.class.getName();
    private static final String[] NAMES = {"PaintShop Compressed"};
    private static final String[] SUFFIXES = {"psc"};
    private static final String[] MIME_TYPES = {"image/x-paintshop-compressed"};
    private static final String[] WRITER_SPI_NAMES = {};

    public PSCompressedReaderSpi() {
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

        byte[] header = new byte[10];

        stream.readFully(header);

        stream.reset();

        return (header[0] == 't') && (header[1] == 'm') && (header[2] == '8') && (header[3] == '9') && (header[8] == 2) && (header[9] >= 1);
    }

    @Override
    public ImageReader createReaderInstance(Object extension) {
        return new PSCompressedReader(this);
    }

    @Override
    public String getDescription(Locale locale) {
        return PSCompressedReader.DESCRIPTION;
    }
}
