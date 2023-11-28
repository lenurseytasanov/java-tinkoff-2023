package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jetbrains.annotations.NotNull;

public class SpecialParser extends DateParser {
    private static final Map<Pattern, Function<Matcher, Integer>> FORMATS = Map.of(
        Pattern.compile("tomorrow"), matcher -> 1,
        Pattern.compile("today"), matcher -> 0,
        Pattern.compile("yesterday"), matcher -> -1,
        Pattern.compile("(\\d+) days? ago"), matcher -> -Integer.parseInt(matcher.group(1))
    );

    public SpecialParser(DateParser next) {
        super(next);
    }

    @Override
    public Optional<LocalDate> tryParse(@NotNull String string) {
        for (var pattern : FORMATS.keySet()) {
            var matcher = pattern.matcher(string);
            if (matcher.matches()) {
                var daysDiff = FORMATS.get(pattern).apply(matcher);
                return Optional.of(LocalDate.now().plusDays(daysDiff));
            }
        }
        if (nextParser != null) {
            return nextParser.tryParse(string);
        }
        return Optional.empty();
    }
}
