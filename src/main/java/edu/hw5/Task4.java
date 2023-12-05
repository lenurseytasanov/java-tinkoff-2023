package edu.hw5;

import java.util.regex.Pattern;
import org.jetbrains.annotations.NotNull;

public final class Task4 {
    private Task4() { }

    public static boolean validatePassword(@NotNull String string) {
        var pattern = Pattern.compile("~|!|@|#|\\$|%|\\^|&|\\*|\\|");
        return pattern.matcher(string).find();
    }
}
