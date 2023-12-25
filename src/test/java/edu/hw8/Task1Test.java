package edu.hw8;

import edu.hw8.Task1.Client;
import edu.hw8.Task1.QuotesList;
import edu.hw8.Task1.Server;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task1Test {
    @Test
    void resultTest() {
        // Arrange
        var threadCount = 3;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        // Act
        executor.execute(() -> Server.start(threadCount - 1));
        threadSleep();
        var actual1 = executor.submit(() -> Client.start("глупый"));
        var actual2 = executor.submit(() -> Client.start("личности"));
        executor.close();

        // Assert
        assertTrue(actual1.resultNow().startsWith("А я тебе"));
        assertTrue(actual2.resultNow().startsWith("Не переходи на"));
    }

    @Test
    void quoteTest() {
        // Arrange
        var quotes = new QuotesList();

        // Act
        var actual1 = quotes.getQuote("интеллект");
        var expected1 = "Чем ниже интеллект, тем громче оскорбления";
        var actual2 = quotes.getQuote("ddd");

        // Assert
        assertEquals(expected1, actual1.orElseThrow());
        assertTrue(actual2.isEmpty());
    }

    private void threadSleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
