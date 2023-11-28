package edu.hw3;

import org.jetbrains.annotations.NotNull;

public final class Task1 {
    private Task1() { }

    public static String atbash(@NotNull String string) {
        var sb = new StringBuilder();
        for (var chr : string.toCharArray()) {
            if (chr >= 'A' && chr <= 'Z') {
                sb.append((char) ('Z' - (chr - 'A')));
            } else if (chr >= 'a' && chr <= 'z') {
                sb.append((char) ('z' - (chr - 'a')));
            } else {
                sb.append(chr);
            }
        }
        return sb.toString();
    }
}
