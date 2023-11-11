package edu.hw5;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task4Test {
    @Test
    void mainTest() {
        for (var ch : "~!@#$%^&*|".toCharArray()) {
            assertTrue(Task4.validatePassword(String.format("abc%c123", ch)));
        }
    }
}
