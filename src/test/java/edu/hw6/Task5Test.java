package edu.hw6;

import edu.hw6.Task5.HackerNews;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task5Test {

    @Test
    void newsArrayTest() {
        // Arrange
        // Act
        var actual = HackerNews.hackerNewsTopStories();

        // Assert
        assertTrue(actual.length > 0);
    }

    @Test
    void newsTitleTest() {
        // Arrange
        var data = HackerNews.hackerNewsTopStories();

        // Act
        var condition1 = HackerNews.news(-1).isEmpty();
        var condition2 = HackerNews.news(data[0]).isPresent();

        // Assert
        assertTrue(condition1);
        assertTrue(condition2);
    }
}
