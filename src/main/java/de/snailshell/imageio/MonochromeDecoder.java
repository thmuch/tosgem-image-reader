package de.snailshell.imageio;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;

public abstract class MonochromeDecoder {

    protected static final int BLACK = 0xff000000;
    protected static final int WHITE = 0xffffffff;

    protected BufferedImage bufferedImageFrom(int width, int height, int[] pix) {

        var memoryImageSource = new MemoryImageSource(width, height, pix, 0, width);

        var image = Toolkit.getDefaultToolkit().createImage(memoryImageSource);

        var bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);

        bufferedImage.getGraphics().drawImage(image, 0, 0, null);

        return bufferedImage;
    }
}
