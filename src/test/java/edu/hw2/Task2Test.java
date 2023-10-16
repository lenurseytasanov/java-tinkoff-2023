package edu.hw2;

import edu.hw2.Task2.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    static Arguments[] rectangles() {
        return new Arguments[]{
            Arguments.of(new Rectangle()),
            Arguments.of(new Square())
        };
    }

    @ParameterizedTest
    @MethodSource("rectangles")
    void rectangleArea(Rectangle rect) {
        var s = rect.setWidth(20).setHeight(10);
        assertThat(s.area()).isEqualTo(200.0);
    }

    @Test
    public void areaTest() {
        var sqr = new Square(2);
        var rect1 = sqr.setWidth(5);
        var rect2 = sqr.setHeight(5);
        assertThat(sqr.area()).isEqualTo(2 * 2);
        assertThat(rect1.area()).isEqualTo(5 * 2);
        assertThat(rect2.area()).isEqualTo(2 * 5);
    }

}
