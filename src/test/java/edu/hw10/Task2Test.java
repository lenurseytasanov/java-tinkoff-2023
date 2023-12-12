package edu.hw10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test {

    private static final Path PATH = Paths.get("src/test/java/edu/hw10/caching.txt");

    private static final Logger LOGGER = LogManager.getLogger();

    @Test
    void proxyTest() {
        // Arrange
        FibCalculator fibCalculator = new FibSimpleCalculator();
        FibCalculator proxy = CacheProxy.create(fibCalculator, FibCalculator.class);

        // Act
        long actual1 = proxy.fibWithCaching(6);
        long actual2 = proxy.fib(5);
        long cache = readCacheFile();

        // Assert
        assertEquals(8, actual1);
        assertEquals(5, actual2);
        assertEquals(8, cache);
    }

    @Test
    void illegalNumberTest() {
        // Arrange
        FibCalculator calculator = new FibSimpleCalculator();

        // Act
        long actual = calculator.fib(-4);

        // Assert
        assertEquals(-1, actual);
    }

    private long readCacheFile() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(
            PATH)))) {
            return Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            LOGGER.error("error", e);
            throw new RuntimeException(e);
        }
    }

    @AfterAll
    static void deleteTempFile() {
        try {
            Files.delete(Paths.get("src/test/java/edu/hw10/caching.txt"));
        } catch (IOException e) {
            LOGGER.error("error", e);
            throw new RuntimeException(e);
        }
    }
}
