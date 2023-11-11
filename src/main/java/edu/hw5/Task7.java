package edu.hw5;

import java.util.regex.Pattern;
import org.jetbrains.annotations.NotNull;

public final class Task7 {
    private Task7() { }

    public static boolean validate1(@NotNull String string) {
        var pattern = Pattern.compile("[01]{2}0[01]*");
        return pattern.matcher(string).matches();
    }

    public static boolean validate2(@NotNull String string) {
        var pattern = Pattern.compile("^([01])[01]*\\1$");
        return pattern.matcher(string).matches();
    }

    public static boolean validate3(@NotNull String string) {
        var pattern = Pattern.compile("[01]{1,3}");
        return pattern.matcher(string).matches();
    }
}
