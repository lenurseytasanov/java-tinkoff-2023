package edu.project3;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Pattern;
import org.jetbrains.annotations.NotNull;

public record LogRecord(OffsetDateTime time, String source, Integer statusCode, Long dataSize) {
    private final static int GROUP_1_NUMBER = 1;
    private final static int GROUP_2_NUMBER = 2;
    private final static int GROUP_3_NUMBER = 3;
    private final static int GROUP_4_NUMBER = 4;

    public static LogRecord getRecord(@NotNull String string) {
        Pattern pattern = Pattern.compile("\\[(.*?)] \"\\S+ (\\S+) \\S+\" (\\d+) (\\d+)");
        var matcher = pattern.matcher(string);
        var isFound = matcher.find();

        if (isFound) {
            var clfFormatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH);

            return new LogRecord(
                OffsetDateTime.parse(matcher.group(GROUP_1_NUMBER), clfFormatter),
                matcher.group(GROUP_2_NUMBER),
                Integer.parseInt(matcher.group(GROUP_3_NUMBER)),
                Long.parseLong(matcher.group(GROUP_4_NUMBER))
            );
        } else {
            return null;
        }
    }
}
