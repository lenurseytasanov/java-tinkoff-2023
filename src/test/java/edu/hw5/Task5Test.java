package edu.hw5;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task5Test {
    @Test
    void mainTest() {
        List<String> strings = List.of(
            "А123ВЕ777", "О777ОО177", "123АВЕ777",
            "А123ВГ77", "А123ВЕ7777"
        );

        var actual = strings.stream().map(Task5::validateNumber).toList();

        assertTrue(actual.get(0));
        assertTrue(actual.get(1));
        assertFalse(actual.get(2));
        assertFalse(actual.get(3));
        assertFalse(actual.get(4));
    }
}
