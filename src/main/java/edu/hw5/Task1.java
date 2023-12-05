package edu.hw5;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public final class Task1 {

    private Task1() { }

    private static final Logger LOGGER = LogManager.getLogger();

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd, hh:mm");

    public static String getAvgDuration(@NotNull List<String> sessions) {
        var duration = Duration.ZERO;
        try {
            for (var session : sessions) {
                var bounds = session.split(" - ");
                if (bounds.length != 2) {
                    return null;
                }
                var start = LocalDateTime.ofInstant(DATE_FORMAT.parse(bounds[0]).toInstant(), ZoneId.systemDefault());
                var end = LocalDateTime.ofInstant(DATE_FORMAT.parse(bounds[1]).toInstant(), ZoneId.systemDefault());
                duration = duration.plus(Duration.between(start, end));
            }
        } catch (ParseException exception) {
            LOGGER.error("parse error: ", exception);
            return null;
        }
        if (!sessions.isEmpty()) {
            duration = duration.dividedBy(sessions.size());
        }
        return String.format("%dч %dм", duration.toHours(), duration.toMinutesPart());
    }
}
