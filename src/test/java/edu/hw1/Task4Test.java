package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Task4Test {
    @Test
    void evenCharNumberTest() {
        assertEquals("212121", Task4.fixString("121212"));
    }

    @Test
    void oddCharNumberTest() {
        assertEquals("21211", Task4.fixString("12121"));
    }

    @Test
    void emptyStringTest() {
        assertEquals("", Task4.fixString(""));
    }
}
