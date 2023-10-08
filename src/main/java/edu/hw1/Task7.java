package edu.hw1;

public final class Task7 {
    private Task7() {
    }

    public static int rotateLeft(int n, int shift) throws IllegalArgumentException {
        if (n < 0 || shift < 0) {
            throw new IllegalArgumentException();
        }
        var mask1 = (int) Math.pow(2, Integer.toString(n, 2).length() - 1);
        var mask2 = mask1 * 2 - 1;
        var result = n;
        for (var i = 0; i < shift; i++) {
            if ((result & mask1) == mask1) {
                result <<= 1;
                result &= mask2;
                result += 1;
            } else {
                result <<= 1;
            }
        }
        return result;
    }

    public static int rotateRight(int n, int shift) {
        var mask1 = 1;
        var mask2 = (int) Math.pow(2, Integer.toString(n, 2).length() - 1);
        var result = n;
        for (var i = 0; i < shift; i++) {
            if ((result & mask1) == mask1) {
                result >>= 1;
                result |= mask2;
            } else {
                result >>= 1;
            }
        }
        return result;
    }
}
