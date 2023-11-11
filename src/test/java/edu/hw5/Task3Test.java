package edu.hw5;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static edu.hw5.Task3.Main.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task3Test {
    @Test
    void standardTest() {
        assertEquals(LocalDate.of(2020, 10, 10), parseDate("2020-10-10").orElseThrow());
        assertEquals(LocalDate.of(2020, 12, 2), parseDate("2020-12-2").orElseThrow());
        assertEquals(LocalDate.of(1976, 3, 1), parseDate("1/3/1976").orElseThrow());
        assertEquals(LocalDate.of(2020, 3, 1), parseDate("1/3/20").orElseThrow());
    }

    @Test
    void specialFormatTest() {
        assertEquals(LocalDate.now().minusDays(1), parseDate("yesterday").orElseThrow());
        assertEquals(LocalDate.now(), parseDate("today").orElseThrow());
        assertEquals(LocalDate.now().plusDays(1), parseDate("tomorrow").orElseThrow());
        assertEquals(LocalDate.now().minusDays(1), parseDate("1 day ago").orElseThrow());
        assertEquals(LocalDate.now().minusDays(2234), parseDate("2234 days ago").orElseThrow());
    }

    @Test
    void illegalFormatTest() {
        assertTrue(parseDate("last week").isEmpty());
    }
}
