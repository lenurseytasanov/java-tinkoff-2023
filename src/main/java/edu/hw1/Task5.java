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
                var temporaryString = new StringBuilder();
                for (var i = 0; i < string.length() / 2; i++) {
                    var digit1 = Integer.parseInt(string.substring(2 * i, 2 * i + 1));
                    var digit2 = Integer.parseInt(string.substring(2 * i + 1, 2 * i + 2));
                    temporaryString.append(digit1 + digit2);
                }
                string = temporaryString.toString();
            }
        }
        return false;
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
