package edu.hw3;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;

public class Task3Test {
    @Test
    void standardTest1() {
        var expected = new HashMap<String, Integer>();
        expected.put("bb", 2);
        expected.put("a", 2);
        assertEquals(expected, Task3.freqDict(new String[] {"a", "bb", "a", "bb"}));
    }

    @Test
    void standardTest2() {
        var expected = new HashMap<String, Integer>();
        expected.put("that", 1);
        expected.put("and", 2);
        expected.put("this", 1);
        assertEquals(expected, Task3.freqDict(new String[] {"this", "and", "that", "and"}));
    }

    @Test
    void standardTest3() {
        var expected = new HashMap<String, Integer>();
        expected.put("код", 3);
        expected.put("bug", 1);
        assertEquals(expected, Task3.freqDict(new String[] {"код", "код", "код", "bug"}));
    }

    @Test
    void standardTest4() {
        var expected = new HashMap<Integer, Integer>();
        expected.put(1, 2);
        expected.put(2, 2);
        assertEquals(expected, Task3.freqDict(new Integer[] {1, 1, 2, 2}));
    }
}
