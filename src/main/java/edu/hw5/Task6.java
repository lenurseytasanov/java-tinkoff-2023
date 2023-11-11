package edu.hw5;

import java.util.regex.Pattern;
import org.jetbrains.annotations.NotNull;

public final class Task6 {
    private Task6() {}

    public static boolean isSubstring(@NotNull String s, @NotNull String t) {
        var pattern = Pattern.compile(s);
        return pattern.matcher(t).find();
    }

    public static boolean isSubsequence(@NotNull String s, @NotNull String t) {
        var pattern = Pattern.compile(String.join(".*", s.split("")));
        return pattern.matcher(t).find();
    }
}
