package edu.hw7;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import java.util.function.Function;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task1Test {
    @Test
    void multithreadingTest() {
        // Act
        var actual1 = Task1.incrementMultithreaded(100, 7);
        Task1.resetCounter();
        var actual2 = Task1.incrementMultithreaded(100_000, 1);
        Task1.resetCounter();

        // Assert
        assertEquals(100, actual1);
        assertEquals(100_000, actual2);
    }

    @Test
    void incrementTest() {
        // Act
        var actual = Task1.increment(100);
        Task1.resetCounter();

        // Arrange
        assertEquals(100, actual);
    }

    @Test
    void illegalArgumentTest() {
        // Arrange
        Executable lambda1 = () -> Task1.increment(-1);
        Executable lambda2 = () -> Task1.incrementMultithreaded(-1, -5);

        // Assert
        assertThrows(IllegalArgumentException.class, lambda1);
        assertThrows(IllegalArgumentException.class, lambda2);
    }
}
