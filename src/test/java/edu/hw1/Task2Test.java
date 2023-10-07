package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Task2Test {
    @Test
    void mainTest() {
        assertEquals(4, Task2.countDigits(1234));
        assertEquals(3, Task2.countDigits(-123));
        assertEquals(1, Task2.countDigits(0));
    }
}
