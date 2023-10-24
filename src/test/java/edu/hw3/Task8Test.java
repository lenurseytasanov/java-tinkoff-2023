package edu.hw3;

import edu.hw3.Task8.BackwardIterator;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

public class Task8Test {
    @Test
    void standardTest() {
        var iterator = new BackwardIterator<>(List.of(1,2,3));
        var actual = new ArrayList<>();
        while (iterator.hasNext()) {
            actual.add(iterator.next());
        }
        assertEquals(List.of(3, 2, 1), actual);
    }

    @Test
    void emptyIteratorTest() {
        var iterator = new BackwardIterator<>(new ArrayList<>());
        assertThrows(NoSuchElementException.class, iterator::next);
    }
}
