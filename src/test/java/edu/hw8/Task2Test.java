package edu.hw8;

import edu.hw8.Task2.FixedThreadPool;
import edu.hw8.Task2.ThreadPool;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test {
    private static final Logger LOGGER = LogManager.getLogger();

    @Test
    void counterTest() {
        // Arrange
        var counter = new AtomicInteger(0);
        var n = 10;

        // Act
        try (ThreadPool pool = FixedThreadPool.create(5)) {
            for (var i = 0; i < n; i++) {
                pool.execute(counter::getAndIncrement);
            }

            pool.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Assert
        assertEquals(n, counter.get());
    }

    @Test
    void fibonacciTest() {
        // print fibs
        try (ThreadPool pool = FixedThreadPool.create(10)) {
            for (var i = 0; i < 10; i++) {
                var n = ThreadLocalRandom.current().nextInt(1, 100);
                pool.execute(() -> fibonacci(n));
            }

            pool.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void fibonacci(int n) {
        if (n <= 0) {
            LOGGER.info("illegal argument");
            return;
        }
        int first = 0;
        int second = 1;
        for (var i = 1; i < n; i++) {
            var temp = second;
            second = first + second;
            first = temp;
        }
        LOGGER.info("%dth fib = %d".formatted(n, second));
    }
}
