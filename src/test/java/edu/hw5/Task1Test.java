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
        var expected = "3ч 40м";

        var actual = Task1.getAvgDuration(sessions);

        assertEquals(expected, actual);
    }

    @Test
    void onlyMinutesTest() {
        var sessions = List.of(
            "2022-03-12, 20:20 - 2022-03-12, 20:50",
            "2022-04-01, 23:40 - 2022-04-02, 00:10"
        );
        var expected = "0ч 30м";

        var actual = Task1.getAvgDuration(sessions);

        assertEquals(expected, actual);
    }

    @Test
    void bigValueTest() {
        var sessions = List.of(
            "2022-03-12, 20:20 - 2022-03-16, 20:50",
            "2022-04-01, 23:40 - 2022-04-07, 00:10"
        );
        var expected = "108ч 30м";

        var actual = Task1.getAvgDuration(sessions);

        assertEquals(expected, actual);
    }

    @Test
    void illegalArgumentTest() {
        var illegalSessions = List.of(
            List.of("2022-03-12, 20:20 2022-03-16, 20:50"),
            List.of("2022-03-12, 20:20 - hello"),
            List.of("2022-03-12 - 2022-03-16, 20:50")
        );

        var actual1 = Task1.getAvgDuration(illegalSessions.get(0));
        var actual2 = Task1.getAvgDuration(illegalSessions.get(1));
        var actual3 = Task1.getAvgDuration(illegalSessions.get(2));

        assertNull(actual1);
        assertNull(actual2);
        assertNull(actual3);
    }

    @Test
    void emptyListTest() {
        var actual = Task1.getAvgDuration(List.of());
        var expected = "0ч 0м";

        assertEquals(expected, actual);
    }
}
