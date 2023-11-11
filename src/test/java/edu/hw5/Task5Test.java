package edu.hw5;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task5Test {
    @Test
    void mainTest() {
        assertTrue(Task5.validateNumber("А123ВЕ777"));
        assertTrue(Task5.validateNumber("О777ОО177"));
        assertFalse(Task5.validateNumber("123АВЕ777"));
        assertFalse(Task5.validateNumber("А123ВГ77"));
        assertFalse(Task5.validateNumber("А123ВЕ7777"));
    }
}
