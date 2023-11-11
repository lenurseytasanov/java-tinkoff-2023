package edu.hw5;

import java.util.regex.Pattern;
import org.jetbrains.annotations.NotNull;

public final class Task5 {
    private Task5() { }

    public static boolean validateNumber(@NotNull String string) {
        var pattern = Pattern.compile("[А-Я]\\d{3}[А-Я]{2}\\d{3}");
        return pattern.matcher(string).matches();
    }
}
