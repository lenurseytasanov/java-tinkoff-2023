package edu.hw5;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task6Test {
    @Test
    void mainTest() {
        assertTrue(Task6.isSubstring("abc", "achfdbaabgabcaabg"));
        assertTrue(Task6.isSubstring("abc", "abc"));
        assertTrue(Task6.isSubsequence("abc", "abc"));

        assertFalse(Task6.isSubstring("abc", "atbtc"));
        assertTrue(Task6.isSubsequence("abc", "atbtc"));

        assertFalse(Task6.isSubsequence("abc", "ab"));
        assertFalse(Task6.isSubstring("abc", "ab"));
    }
}
