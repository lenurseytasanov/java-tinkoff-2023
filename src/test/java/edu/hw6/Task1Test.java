package edu.hw6;

import edu.hw6.Task1.DiscMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task1Test {
    private final static String FILE_PATH = "src\\test\\java\\edu\\hw6\\task1.txt";
    private final static DiscMap DISC_MAP = new DiscMap(FILE_PATH);

    @Test
    void putAllTest() {
        assertTrue(DISC_MAP.isEmpty());

        var sourceMap = Map.of(
            "123", "456",
            "key", "value",
            "", ""
        );
        DISC_MAP.putAll(sourceMap);

        assertEquals(sourceMap, DISC_MAP);
    }

    @Test
    void putTest() {
        var key = "test";
        var value = "test";
        DISC_MAP.clear();

        DISC_MAP.put(key, value);

        assertTrue(DISC_MAP.containsKey(key));
        assertEquals(1, DISC_MAP.size());
        assertEquals(value, DISC_MAP.get(key));
    }

    @Test
    void removeTest() {
        DISC_MAP.clear();

        DISC_MAP.put("key", "value");
        assertEquals(1, DISC_MAP.size());

        assertEquals("value", DISC_MAP.remove("key"));
    }
}
