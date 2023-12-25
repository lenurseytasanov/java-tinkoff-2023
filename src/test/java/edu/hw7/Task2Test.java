package edu.hw7;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import java.math.BigInteger;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task2Test {
    @Test
    void test() {
        // Arrange =
        var expected1 = BigInteger.ONE;
        var expected2 = new BigInteger("265252859812191058636308480000000");
        Executable lambda = () -> Task2.getFactorialParallel(-10);

        // Act
        var actual1 = Task2.getFactorialParallel(0);
        var actual2 = Task2.getFactorialParallel(30);

        // Assert
        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
        assertThrows(IllegalArgumentException.class, lambda);
    }
}
