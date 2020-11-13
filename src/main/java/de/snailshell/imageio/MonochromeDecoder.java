package de.snailshell.imageio;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;

public abstract class MonochromeDecoder {

    public static final int BLACK = 0xff000000;
    public static final int WHITE = 0xffffffff;

    protected BufferedImage bufferedImageFrom(int width, int height, int[] pix) {
        return bufferedImageFrom(width, height, width, height, pix);
    }

    protected BufferedImage bufferedImageFrom(int srcWidth, int srcHeight, int outputWidth, int outputHeight, int[] pix) {

        var memoryImageSource = new MemoryImageSource(srcWidth, srcHeight, pix, 0, srcWidth);

        var image = Toolkit.getDefaultToolkit().createImage(memoryImageSource);

        var bufferedImage = new BufferedImage(outputWidth, outputHeight, BufferedImage.TYPE_BYTE_BINARY);

        bufferedImage.getGraphics().drawImage(image, 0, 0, outputWidth, outputHeight, null);

        return bufferedImage;
    }
}
