package de.snailshell;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class TosGemConverter {

    public static void main(String... args) {

        if (args.length < 2) {
            System.out.println("Usage: java -jar tosgem-image-reader.jar <output-format> <input-file>");
            System.out.println("Example: java -jar tosgem-image-reader.jar png oldatari.img");
            return;
        }

        var outputFormat = args[0];
        var inputFile = new File(args[1]);
        var outputFile = new File(inputFile + "." + outputFormat);

        try {
            var inputImage = ImageIO.read(inputFile);

            var success = ImageIO.write(inputImage, outputFormat, outputFile);

            if (!success) {
                System.err.printf("Could not convert to format '%s' - no suitable ImageIO writer found.\n",
                        outputFormat);
            }

        } catch (IOException e) {
            System.err.printf("Error converting %s to %s: %s\n", inputFile, outputFormat, e.getMessage());
        }
    }
}
