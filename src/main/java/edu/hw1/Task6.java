package edu.hw1;

import java.util.Arrays;
import java.util.Collections;

public final class Task6 {
    private final static int MIN_VALUE = 1000;

    private Task6() {
    }

    public static int countK(int number) {
        if (number <= MIN_VALUE) {
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
        var digitArray = new Integer[charArray.length];
        for (var i = 0; i < charArray.length; i++) {
            digitArray[i] = Integer.valueOf(Character.toString(charArray[i]));
        }
        if (reverse) {
            Arrays.sort(digitArray, Collections.reverseOrder());
        } else {
            Arrays.sort(digitArray);
        }
        var stringArray = new String[charArray.length];
        for (var i = 0; i < digitArray.length; i++) {
            stringArray[i] = Integer.toString(digitArray[i]);
        }
        return String.join("", stringArray);
    }
}
