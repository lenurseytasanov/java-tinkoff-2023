package edu.hw7;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;

public final class Task4 {
    private final static long SQUARE_SIZE = 100_000;
    private final static int CONST_MONTE_CARLO = 4;

    private Task4() { }

    private static boolean isBelongToCircle(long x, long y) {
        return SQUARE_SIZE / 2 >= Math.sqrt((x - SQUARE_SIZE / 2) * (x - SQUARE_SIZE / 2)
            + (y - SQUARE_SIZE / 2) * (y - SQUARE_SIZE / 2));
    }

    public static double getPiMonteCarlo(long n) {
        if (n <= 0) {
            return -1;
        }
        var random = ThreadLocalRandom.current();
        var totalCount = n;
        var circleCount = 0;
        for (var i = 0; i < n; i++) {
            var pointX = random.nextLong(0, SQUARE_SIZE);
            var pointY = random.nextLong(0, SQUARE_SIZE);
            if (isBelongToCircle(pointX, pointY)) {
                circleCount++;
            }
        }
        return CONST_MONTE_CARLO * ((double) circleCount / totalCount);
    }

    public static double getPiMonteCarloParallel(long n, int threadCount) {
        if (n <= 0 || threadCount <= 0) {
            return -1;
        }
        var totalCount = new AtomicLong();
        var circleCount = new AtomicLong();
        var threadPool = new ArrayList<Thread>();
        for (var i = 0; i < threadCount; i++) {
            threadPool.add(new Thread(() -> {
                var random = ThreadLocalRandom.current();
                var localTotalCount = 0;
                var localCircleCount = 0;
                for (var j = 0; j < n / threadCount; j++) {
                    var pointX = random.nextLong(0, SQUARE_SIZE);
                    var pointY = random.nextLong(0, SQUARE_SIZE);
                    localTotalCount++;
                    if (isBelongToCircle(pointX, pointY)) {
                        localCircleCount++;
                    }
                }
                totalCount.getAndAdd(localTotalCount);
                circleCount.getAndAdd(localCircleCount);
            }));
        }
        threadPool.forEach(Thread::start);
        threadPool.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        return CONST_MONTE_CARLO * ((double) circleCount.get() / totalCount.get());
    }
}
