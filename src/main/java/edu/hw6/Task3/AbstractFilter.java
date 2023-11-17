package edu.hw6.Task3;

import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.Arrays;
import java.util.regex.Pattern;
import org.jetbrains.annotations.NotNull;

public interface AbstractFilter extends DirectoryStream.Filter<Path> {
    default AbstractFilter and(@NotNull AbstractFilter after) {
        return entry -> this.accept(entry) && after.accept(entry);
    }

    static AbstractFilter largerThen(int size) {
        return entry -> Files.size(entry) > size;
    }

    static AbstractFilter globMatches(@NotNull String glob) {
        PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:" + glob);
        return entry -> matcher.matches(entry.getFileName());
    }

    static AbstractFilter regexContains(@NotNull String regex) {
        var pattern = Pattern.compile(regex);
        return entry -> pattern.matcher(entry.getFileName().toString()).find();
    }

    static AbstractFilter magicNumber(byte @NotNull... id) {
        return entry -> startWith(Files.readAllBytes(entry), id);
    }

    private static boolean startWith(byte[] file, byte[] numbers) {
        if (file.length >= numbers.length) {
            return Arrays.equals(file, 0, numbers.length, numbers, 0, numbers.length);
        }
        return false;
    }
}
