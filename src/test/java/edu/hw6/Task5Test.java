package edu.hw6;

import edu.hw6.Task5.HackerNews;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task5Test {
    private final static long[] DATA = HackerNews.hackerNewsTopStories();

    @Test
    void newsArrayTest() {
        assertTrue(DATA.length > 0);
    }

    @Test
    void newsTitleTest() {
        assertTrue(HackerNews.news(-1).isEmpty());

        assertTrue(HackerNews.news(DATA[0]).isPresent());
    }
}
