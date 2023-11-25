package edu.hw7;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task1Test {
    @Test
    void multithreadingTest() {
        assertEquals(100, Task1.incrementMultithreaded(100, 7));
        Task1.resetCounter();

        assertEquals(100_000, Task1.incrementMultithreaded(100_000, 1));
        Task1.resetCounter();
    }

    @Test
    void incrementTest() {
        assertEquals(100, Task1.increment(100));
        Task1.resetCounter();
    }

    @Test
    void illegalArgumentTest() {
        assertThrows(IllegalArgumentException.class, () -> Task1.increment(-1));
        assertThrows(IllegalArgumentException.class, () -> Task1.incrementMultithreaded(-1, -5));
    }
}
