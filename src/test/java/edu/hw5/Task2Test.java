package edu.hw5;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class Task2Test {
    @Test
    void list1Test() {
        var actual = Task2.getAllFridays13th(1925);
        var expect = List.of(
            "1925-02-13",
            "1925-03-13",
            "1925-11-13"
        );
        assertEquals(expect, actual);
    }

    @Test
    void list2Test() {
        var actual = Task2.getAllFridays13th(2024);
        var expect = List.of(
            "2024-09-13",
            "2024-12-13"
        );
        assertEquals(expect, actual);
    }

    @Test
    void next1Test() {
        var actual = Task2.getNextFriday13th("2024-09-13");
        assertEquals("2024-12-13", actual);
    }

    @Test
    void next2Test() {
        var actual = Task2.getNextFriday13th("2024-09-12");
        assertEquals("2024-09-13", actual);
    }

    @Test
    void next3Test() {
        var actual = Task2.getNextFriday13th("2024-12-13");
        assertEquals("2025-06-13", actual);
    }

    @Test
    void illegalArgumentTest() {
        var actual = Task2.getNextFriday13th("2024-December-Monday");
        assertNull(actual);
    }
}
