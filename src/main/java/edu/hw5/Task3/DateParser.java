package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public abstract class DateParser {
    public DateParser nextParser;

    public DateParser(DateParser next) {
        this.nextParser = next;
    }

    public abstract Optional<LocalDate> tryParse(@NotNull String string);
}
