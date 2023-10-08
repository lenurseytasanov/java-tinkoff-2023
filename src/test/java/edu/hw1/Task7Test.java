package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Task7Test {
    @Test
    void mainTest() {
        assertEquals(4, Task7.rotateRight(8, 1));
        assertEquals(1, Task7.rotateLeft(16, 1));
        assertEquals(6, Task7.rotateLeft(17, 2));
        assertEquals(5, Task7.rotateRight(5, 0));
    }
}
