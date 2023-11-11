package edu.hw5.Task3;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public class StandardParser extends DateParser {
    private final static List<SimpleDateFormat> FORMATS = List.of(
        new SimpleDateFormat("yyyy-MM-dd"),
        new SimpleDateFormat("yyyy-MM-d"),
        new SimpleDateFormat("d/M/yy"),
        new SimpleDateFormat("d/M/yyyy")
    );

    public StandardParser(DateParser next) {
        super(next);
    }

    @Override
    public Optional<LocalDate> tryParse(@NotNull String string) {
        for (var format : FORMATS) {
            try {
                var date = LocalDate.ofInstant(format.parse(string).toInstant(), ZoneId.systemDefault());
                return Optional.of(date);
            } catch (ParseException ignored) { }
        }
        if (this.nextParser != null) {
            return nextParser.tryParse(string);
        }
        return Optional.empty();
    }
}
