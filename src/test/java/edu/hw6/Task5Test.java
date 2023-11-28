package edu.hw6;

import edu.hw6.Task5.HackerNews;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task5Test {

    @Test
    void newsArrayTest() {
        var actual = HackerNews.hackerNewsTopStories();

        assertTrue(actual.length > 0);
    }

    @Test
    void newsTitleTest() {
        var data = HackerNews.hackerNewsTopStories();

        var condition1 = HackerNews.news(-1).isEmpty();
        var condition2 = HackerNews.news(data[0]).isPresent();

        assertTrue(condition1);
        assertTrue(condition2);
    }
}
