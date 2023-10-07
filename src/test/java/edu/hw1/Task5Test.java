package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Task5Test {
    @Test
    void correctPalindromeTest() {
        assertTrue(Task5.isPalindromeDescendant(123312));
        assertTrue(Task5.isPalindromeDescendant(13001120));
        assertTrue(Task5.isPalindromeDescendant(23336014));
        assertTrue(Task5.isPalindromeDescendant(11));
    }

    @Test
    void incorrectPalindromeTest() {
        assertFalse(Task5.isPalindromeDescendant(-234));
        assertFalse(Task5.isPalindromeDescendant(0));
        assertFalse(Task5.isPalindromeDescendant(1));
        assertFalse(Task5.isPalindromeDescendant(156879534));
    }
}
