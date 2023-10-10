package edu.hw1;

public final class Task2 {
    private static final int RADIX = 10;

    private Task2() {
    }

    public static int countDigits(int number) {
        var remainder = number;
        var count = 0;
        do {
            count++;
            remainder /= RADIX;
        } while (remainder != 0);
        return count;
    }
}
