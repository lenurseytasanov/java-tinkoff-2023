package edu.hw1;

public class Task5 {
    private Task5() {
    }

    public static boolean isPalindromeDescendant(int number) {
        if (number > 0) {
            var string = Integer.toString(number);
            while (string.length() > 1) {
                if (isPalindrome(string)) {
                    return true;
                }
                string = getDescendant(string);
            }
        }
        return false;
    }

    private static String getDescendant(String string) {
        var stringBuilder = new StringBuilder();
        for (var i = 0; i < string.length() / 2; i++) {
            var digit1 = Integer.parseInt(Character.toString(string.charAt(2 * i)));
            var digit2 = Integer.parseInt(Character.toString(string.charAt(2 * i + 1)));
            stringBuilder.append(digit1 + digit2);
        }
        return stringBuilder.toString();
    }

    private static boolean isPalindrome(String string) {
        var length = string.length();
        for (var i = 0; i < length / 2; i++) {
            if (string.charAt(i) != string.charAt(length - i - 1)) {
                return false;
            }
        }
        return true;
    }
}
