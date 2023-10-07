package edu.hw1;

import java.util.Arrays;

public class Task3 {
    private Task3() {
    }

    public static boolean isNestable(int[] a1, int[] a2) {
        if (a1 != null && a2 != null) {
            var minA1 = Arrays.stream(a1).min();
            var maxA1 = Arrays.stream(a1).max();
            var minA2 = Arrays.stream(a2).min();
            var maxA2 = Arrays.stream(a2).max();
            if (minA1.isPresent() && minA2.isPresent()) {
                return minA1.getAsInt() > minA2.getAsInt() && maxA1.getAsInt() < maxA2.getAsInt();
            }
        }

        return false;
    }
}
