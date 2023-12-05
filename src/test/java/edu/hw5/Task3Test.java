package edu.hw5;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static edu.hw5.Task3.Main.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task3Test {
    @Test
    void standardTest() {
        var actual1 = parseDate("2020-10-10").orElseThrow();
        var actual2 = parseDate("2020-12-2").orElseThrow();
        var actual3 = parseDate("1/3/1976").orElseThrow();
        var actual4 = parseDate("1/3/20").orElseThrow();

        assertEquals(LocalDate.of(2020, 10, 10), actual1);
        assertEquals(LocalDate.of(2020, 12, 2), actual2);
        assertEquals(LocalDate.of(1976, 3, 1), actual3);
        assertEquals(LocalDate.of(2020, 3, 1), actual4);
    }

    @Test
    void specialFormatTest() {
        var actual1 = parseDate("yesterday").orElseThrow();
        var actual2 = parseDate("today").orElseThrow();
        var actual3 = parseDate("tomorrow").orElseThrow();
        var actual4 = parseDate("1 day ago").orElseThrow();
        var actual5 = parseDate("2234 days ago").orElseThrow();

        assertEquals(LocalDate.now().minusDays(1), actual1);
        assertEquals(LocalDate.now(), actual2);
        assertEquals(LocalDate.now().plusDays(1), actual3);
        assertEquals(LocalDate.now().minusDays(1), actual4);
        assertEquals(LocalDate.now().minusDays(2234), actual5);
    }

    @Test
    void illegalFormatTest() {
        var condition = parseDate("last week").isEmpty();

        assertTrue(condition);
    }
}
