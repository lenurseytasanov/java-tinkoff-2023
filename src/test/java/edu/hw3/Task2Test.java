package edu.hw3;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class Task2Test {
    @Test
    void standardTest1() {
        var expected = new ArrayList<String>();
        expected.add("()");
        expected.add("()");
        expected.add("()");
        assertEquals(expected, Task2.clusterize("()()()"));
    }

    @Test
    void standardTest2() {
        var expected = new ArrayList<String>();
        expected.add("((()))");
        assertEquals(expected, Task2.clusterize("((()))"));
    }

    @Test
    void standardTest3() {
        var expected = new ArrayList<String>();
        expected.add("((())())");
        expected.add("(()(()()))");
        assertEquals(expected, Task2.clusterize("((())())(()(()()))"));
    }

    @Test
    void notBalancedStringTest() {
        var expected = new ArrayList<String>();
        expected.add("()");
        expected.add("()");
        assertEquals(expected, Task2.clusterize("()())"));
    }

    @Test
    void illegalStringTest() {
        assertThrows(IllegalArgumentException.class, () -> Task2.clusterize("abc()123"));
    }
}
