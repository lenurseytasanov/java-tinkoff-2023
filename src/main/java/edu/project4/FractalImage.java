package edu.project4;

import org.jetbrains.annotations.NotNull;

public record FractalImage(Pixel[] data, int width, int height) {
    public static FractalImage create(int width, int height) {
        var data = new Pixel[width * height];
        for (var x = 0; x < width; x++) {
            for (var y = 0; y < height; y++) {
                data[x + y * width] = new Pixel(x, y, 0, 0, 0, 0);
            }
        }
        return new FractalImage(data, width, height);
    }

    public Pixel getPixel(int x, int y) {
        return data[x + y * width];
    }

    public void setPixel(@NotNull Pixel pixel) {
        data[pixel.x() + pixel.y() * width] = pixel;
    }
}
