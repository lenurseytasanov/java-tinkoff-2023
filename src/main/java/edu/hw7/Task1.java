package edu.hw7;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task1 {

    private Task1() { }

    private static final Logger LOGGER = LogManager.getLogger();

    private static final AtomicInteger COUNTER = new AtomicInteger(0);

    public static void resetCounter() {
        COUNTER.set(0);
    }

    public static int increment(int iterations) {
        if (iterations < 0) {
            throw new IllegalArgumentException();
        }
        for (var i = 0; i < iterations; i++) {
            COUNTER.incrementAndGet();
        }
        return COUNTER.get();
    }

    public static int incrementMultithreaded(int iterations, int threads) {
        if (iterations < 0 || threads < 0) {
            throw new IllegalArgumentException();
        }
        var threadPool = new ArrayList<Thread>();
        for (var i = 0; i < threads; i++) {
            threadPool.addLast(new Thread(() -> increment(iterations / threads)));
        }
        threadPool.addLast(new Thread(() -> increment(iterations % threads)));

        threadPool.forEach(Thread::start);
        threadPool.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                LOGGER.error("error", e);
                throw new RuntimeException(e);
            }
        });
        return COUNTER.get();
    }
}
