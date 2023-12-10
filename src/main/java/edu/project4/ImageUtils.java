package edu.project4;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import javax.imageio.ImageIO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public final class ImageUtils {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final int BITS_OFFSET_1 = 24;

    private static final int BITS_OFFSET_2 = 16;

    private static final int BITS_OFFSET_3 = 8;

    private static final int DEFAULT_VALUE = 0xFF;

    private ImageUtils() { }

    public static void save(@NotNull FractalImage canvas, @NotNull Path path) {
        BufferedImage image = new BufferedImage(canvas.width(), canvas.height(), BufferedImage.TYPE_INT_RGB);

        var pixels = Arrays.stream(canvas.data())
            .mapToInt(pixel ->
                (DEFAULT_VALUE << BITS_OFFSET_1) | (pixel.red() << BITS_OFFSET_2)
                    | (pixel.green() << BITS_OFFSET_3) | pixel.blue())
            .toArray();
        image.setRGB(0, 0, canvas.width(), canvas.height(), pixels, 0, canvas.width());

        try {
            ImageIO.write(image, "png", path.toFile());
        } catch (IOException e) {
            LOGGER.error("error:", e);
        }
    }
}
