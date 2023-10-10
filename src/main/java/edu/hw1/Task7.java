package edu.hw1;

public final class Task7 {
    private Task7() {
    }

    public static int rotateLeft(int n, int shift) throws IllegalArgumentException {
        return rotateRight(n, -shift);
    }

    public static int rotateRight(int n, int shift) throws IllegalArgumentException {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        var shift1 = Math.floorMod(shift, floorLog2(n) + 1);
        var mask1 = 1;
        var mask2 = (int) Math.pow(2, floorLog2(n));
        var result = n;
        for (var i = 0; i < shift1; i++) {
            var temporary = result;
            result >>= 1;
            if ((temporary & mask1) == mask1) {
                result |= mask2;
            }
        }
        return result;
    }

    private static int floorLog2(int n) {
        return (int) (Math.log(n) / Math.log(2));
    }
}
