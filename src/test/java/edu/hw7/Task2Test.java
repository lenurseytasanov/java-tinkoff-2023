package edu.hw7;

import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task2Test {
    @Test
    void test() {
        assertEquals(BigInteger.ONE, Task2.getFactorialParallel(0));

        assertEquals(
            new BigInteger("265252859812191058636308480000000"),
            Task2.getFactorialParallel(30)
        );

        assertThrows(IllegalArgumentException.class, () -> Task2.getFactorialParallel(-10));
    }
}
