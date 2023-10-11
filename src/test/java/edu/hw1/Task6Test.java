package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Task6Test {
    @Test
    void mainTest() {
        assertEquals(3, Task6.countK(3524));
        assertEquals(5, Task6.countK(6621));
        assertEquals(4, Task6.countK(6554));
        assertEquals(3, Task6.countK(1234));
    }

    @Test
    void invalidInputTest() {
        assertEquals(-1, Task6.countK(200));
        assertEquals(-1, Task6.countK(-2000));
        assertEquals(-1, Task6.countK(2222));
        assertEquals(-1, Task6.countK(1000000));
        assertEquals(-1, Task6.countK(54323));
    }
}
