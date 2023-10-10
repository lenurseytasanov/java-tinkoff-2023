package edu.hw1;

import java.util.Arrays;
import java.util.HashSet;

public final class Task6 {
    private final static int MIN_VALUE = 1000;

    private Task6() {
    }

    public static int countK(int number) throws StackOverflowError {
        if (number <= MIN_VALUE || hasEqualDigits(number)) {
            return -1;
        }
        try {
            return countK(Integer.toString(number), 0);
        } catch (StackOverflowError error) {
            return -1;
        }
    }

    private static boolean hasEqualDigits(int number) {
        var set = new HashSet<Character>();
        for (var digit : Integer.toString(number).toCharArray()) {
            set.add(digit);
        }
        return set.size() == 1;
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
