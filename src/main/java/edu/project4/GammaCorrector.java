package edu.project4;

import java.util.Arrays;
import java.util.Comparator;
import org.jetbrains.annotations.NotNull;

public class GammaCorrector {

    private final double gamma = 2.2;

    public FractalImage correct(@NotNull FractalImage image) {
        double max = Math.log10(Arrays.stream(image.data())
            .max(Comparator.comparingInt(Pixel::hitCount))
            .orElseThrow().hitCount());

        Arrays.stream(image.data()).forEach(pixel -> {
            double normal = Math.log10(pixel.hitCount()) / max;
            image.setPixel(new Pixel(
                pixel.x(), pixel.y(),
                (int) (pixel.red() * Math.pow(normal, (1.0 / gamma))),
                (int) (pixel.green() * Math.pow(normal, (1.0 / gamma))),
                (int) (pixel.blue() * Math.pow(normal, (1.0 / gamma))),
                pixel.hitCount()
            ));
        });
        return image;
    }
}
