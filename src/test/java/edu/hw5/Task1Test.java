package edu.hw5;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class Task1Test {
    @Test
    void standardTest() {
        var sessions = List.of(
            "2022-03-12, 20:20 - 2022-03-12, 23:50",
            "2022-04-01, 21:30 - 2022-04-02, 01:20"
        );
        assertEquals("3ч 40м", Task1.getAvgDuration(sessions));
    }

    @Test
    void onlyMinutesTest() {
        var sessions = List.of(
            "2022-03-12, 20:20 - 2022-03-12, 20:50",
            "2022-04-01, 23:40 - 2022-04-02, 00:10"
        );
        assertEquals("0ч 30м", Task1.getAvgDuration(sessions));
    }

    @Test
    void bigValueTest() {
        var sessions = List.of(
            "2022-03-12, 20:20 - 2022-03-16, 20:50",
            "2022-04-01, 23:40 - 2022-04-07, 00:10"
        );
        assertEquals("108ч 30м", Task1.getAvgDuration(sessions));
    }

    @Test
    void illegalArgumentTest() {
        assertNull(Task1.getAvgDuration(List.of("2022-03-12, 20:20 2022-03-16, 20:50")));
        assertNull(Task1.getAvgDuration(List.of("2022-03-12, 20:20 - hello")));
        assertNull(Task1.getAvgDuration(List.of("2022-03-12 - 2022-03-16, 20:50")));
    }

    @Test
    void emptyListTest() {
        assertEquals("0ч 0м", Task1.getAvgDuration(List.of()));
    }
}
