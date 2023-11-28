package edu.hw3;

import java.util.HashMap;
import org.jetbrains.annotations.NotNull;

public final class Task3 {
    private Task3() { }

    public static <T> HashMap<T, Integer> freqDict(@NotNull T[] words) {
        var dict = new HashMap<T, Integer>();
        for (var word : words) {
            dict.put(word, dict.getOrDefault(word, 0) + 1);
        }
        return dict;
    }
}
