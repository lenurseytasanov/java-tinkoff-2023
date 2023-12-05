package edu.hw5;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task7Test {

    @Test
    void test1() {
        var actual1 = Task7.validate1("110");
        var actual2 = Task7.validate1("1001");
        var actual3 = Task7.validate1("1");

        assertTrue(actual1);
        assertTrue(actual2);
        assertFalse(actual3);
    }

    @Test
    void test2() {
        var actual1 = Task7.validate2("00");
        var actual2 = Task7.validate2("110101");
        var actual3 = Task7.validate2("10");

        assertTrue(actual1);
        assertTrue(actual2);
        assertFalse(actual3);
    }

    @Test
    void test3() {
        var actual1 = Task7.validate3("101");
        var actual2 = Task7.validate3("11");
        var actual3 = Task7.validate3("1");
        var actual4 = Task7.validate3("1111");

        assertTrue(actual1);
        assertTrue(actual2);
        assertTrue(actual3);
        assertFalse(actual4);
    }
}
