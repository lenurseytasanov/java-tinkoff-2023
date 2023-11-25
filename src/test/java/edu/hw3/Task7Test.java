package edu.hw3;

import edu.hw3.Task7.KeyComparator;
import org.junit.jupiter.api.Test;
import java.util.TreeMap;
import static org.assertj.core.api.Assertions.assertThat;

public class Task7Test {
    @Test
    void standardTest() {
        TreeMap<String, String> tree = new TreeMap<>(new KeyComparator());
        tree.put(null, "test");

        assertThat(tree.containsKey(null)).isTrue();
    }
}

