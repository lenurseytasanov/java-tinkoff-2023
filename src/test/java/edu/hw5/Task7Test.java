package edu.hw5;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task7Test {
    @Test
    void mainTest() {
        assertTrue(Task7.validate1("110"));
        assertTrue(Task7.validate1("1001"));
        assertFalse(Task7.validate1("1"));

        assertTrue(Task7.validate2("00"));
        assertTrue(Task7.validate2("110101"));
        assertFalse(Task7.validate2("10"));

        assertTrue(Task7.validate3("101"));
        assertTrue(Task7.validate3("11"));
        assertTrue(Task7.validate3("1"));
        assertFalse(Task7.validate3("1111"));
    }
}
