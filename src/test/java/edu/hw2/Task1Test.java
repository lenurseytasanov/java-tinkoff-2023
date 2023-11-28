package edu.hw2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import edu.hw2.Task1.Expr.*;

public class Task1Test {
    public final static double DELTA = 0.01;

    @Test
    public void test1() {
        var two = new Constant(2);
        var four = new Constant(4);
        var negOne = new Negate(new Constant(1));
        var sumTwoFour = new Addition(two, four);
        var mult = new Multiplication(sumTwoFour, negOne);
        var exp = new Exponent(mult, 2);
        var res = new Addition(exp, new Constant(1));
        assertEquals(37, res.evaluate(), DELTA);
    }

    @Test
    public void test2() {
        assertEquals(2 + 2 * 2, new Addition(2, new Multiplication(2, 2)).evaluate(), DELTA);
        assertEquals(
            23.5 * (5.3 - 3.2) + Math.pow(3.2, 1.2),
            new Addition(new Multiplication(23.5, new Negate(5.3, 3.2)), new Exponent(3.2, 1.2)).evaluate(),
            DELTA);
    }

    @Test
    public void test3() {
        assertEquals(-1, new Negate(1).evaluate(), DELTA);
        assertEquals(0, new Negate(1, new Constant(1)).evaluate(), DELTA);
        assertEquals(0, new Negate(new Constant(1), 1).evaluate(), DELTA);
        assertEquals(4, new Exponent(2, new Constant(2)).evaluate(), DELTA);
        assertEquals(4, new Addition(2, 2).evaluate(), DELTA);
        assertEquals(4, new Addition(new Constant(2), 2).evaluate(), DELTA);
        assertEquals(4, new Multiplication(new Constant(2), 2).evaluate(), DELTA);
    }
}
