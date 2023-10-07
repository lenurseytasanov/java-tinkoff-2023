package edu.hw1;

public final class Task2 {
    private static final int RADIX = 10;

    private Task2() {
    }

    public static int countDigits(int number) {
        if (number == 0) {
            return 1;
        }
        var remainder = number;
        var count = 0;
        while (remainder != 0) {
            count++;
            remainder /= RADIX;
        }
        return count;
    }
}
