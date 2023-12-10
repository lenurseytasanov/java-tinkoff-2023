package edu.project4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {
    @Test
    void coeffsGenerateTest() {
        // Arrange
        var length = 10;

        // Act
        var actual = AffinCoeff.generate(length);

        // Assert
        assertEquals(length, actual.size());
    }

    @Test
    void imageTest() {
        // Arrange
        FractalImage canvas;
        Pixel pixel = new Pixel(5, 5, 0, 0, 0, 0);

        // Act
        canvas = FractalImage.create(10, 10);
        canvas.setPixel(pixel);
        Pixel actual = canvas.getPixel(5, 5);

        // Assert
        assertEquals(10, canvas.height());
        assertEquals(10, canvas.width());
        assertEquals(pixel, actual);
    }

    @Test
    void rectangleTest() {
        // Arrange
        Rectangle rect;
        Point point1 = new Point(-5, -5);
        Point point2 = new Point(5, 5);

        // Act
        rect = new Rectangle(0, 0, 10, 10);

        // Assert
        assertTrue(rect.contains(point2));
        assertFalse(rect.contains(point1));
    }

    @Test
    void gammaCorrectorTest() {
        // Arrange
        FractalImage canvas0;
        FractalImage canvas1;
        GammaCorrector corrector = new GammaCorrector();

        // Act
        canvas0 = FractalImage.create(10, 10);
        canvas1 = corrector.correct(canvas0);

        // Assert
        assertEquals(canvas0.width(), canvas1.width());
        assertEquals(canvas0.height(), canvas1.height());
    }
}
