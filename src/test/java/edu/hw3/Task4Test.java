package edu.hw3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Task4Test {
    @Test
    void standardTest() {
        assertEquals("II", Task4.convertToRoman(2));
        assertEquals("XII", Task4.convertToRoman(12));
        assertEquals("XVI", Task4.convertToRoman(16));
        assertEquals("MMMCMXCIX", Task4.convertToRoman(3999));
        assertEquals("MDXCIII", Task4.convertToRoman(1593));
    }
}

