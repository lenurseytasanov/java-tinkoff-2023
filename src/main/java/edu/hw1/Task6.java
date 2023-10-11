package edu.hw1;

import java.util.Arrays;

public final class Task6 {
    private final static int MIN_VALUE = 1001;
    private final static int MAX_VALUE = 9999;

    private final static int MOD = 1111;

    private Task6() {
    }

    public static int countK(int number) {
        if (number < MIN_VALUE || number > MAX_VALUE || number % MOD == 0) {
            return -1;
        }
        return countK(Integer.toString(number), 0);
    }

    private static int countK(String number, int count) {
        if (number.equals("6174")) {
            return count;
        }
        var min = sortedString(number, false);
        var max = sortedString(number, true);
        var difference = Integer.parseInt(max) - Integer.parseInt(min);
        return countK(Integer.toString(difference), count + 1);
    }

    private static String sortedString(String number, boolean reverse) {
        var charArray = number.toCharArray();
        Arrays.sort(charArray);
        var sortedString = new String(charArray);
        if (reverse) {
            var stringBuilder = new StringBuilder(sortedString).reverse();
            return stringBuilder.toString();
        }
        return sortedString;
    }
}
