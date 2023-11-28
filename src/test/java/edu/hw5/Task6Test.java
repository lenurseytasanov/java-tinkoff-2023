package edu.hw5;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task6Test {
    @Test
    void subsequenceTest() {
        var actual1 = Task6.isSubsequence("abc", "abc");
        var actual2 = Task6.isSubsequence("abc", "atbtc");
        var actual3 = Task6.isSubsequence("abc", "ab");

        assertTrue(actual1);
        assertTrue(actual2);
        assertFalse(actual3);
    }

    @Test
    void substringTest() {
        var actual1 = Task6.isSubstring("abc", "achfdbaabgabcaabg");
        var actual2 = Task6.isSubstring("abc", "abc");
        var actual3 = Task6.isSubstring("abc", "atbtc");
        var actual4 = Task6.isSubstring("abc", "ab");

        assertTrue(actual1);
        assertTrue(actual2);
        assertFalse(actual3);
        assertFalse(actual4);
    }
}
