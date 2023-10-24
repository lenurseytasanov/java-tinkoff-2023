package edu.hw3;

import java.util.ArrayList;
import java.util.Stack;
import org.jetbrains.annotations.NotNull;

public final class Task2 {
    private Task2() { }

    public static ArrayList<String> clusterize(@NotNull String string) {
        for (var chr : string.toCharArray()) {
            if (chr != '(' && chr != ')') {
                throw new IllegalArgumentException();
            }
        }
        var result = new ArrayList<String>();
        var stack = new Stack<Character>();
        var start = 0;
        for (var i = 0; i < string.length(); i++) {
            var chr = string.charAt(i);
            if (chr == '(') {
                stack.push(chr);
            } else if (!stack.empty()) {
                stack.pop();
            }
            if (stack.empty() && i - start > 0) {
                result.add(string.substring(start, i + 1));
                start = i + 1;
            }
        }
        return result;
    }
}
