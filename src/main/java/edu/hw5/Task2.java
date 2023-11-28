package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public final class Task2 {
    private static final int UNLUCKY_NUMBER = 13;

    private Task2() { }

    public static List<String> getAllFridays13th(int year) {
        LocalDate date = LocalDate.of(year, 1, 1);
        List<String> result = new ArrayList<>();
        for (var day = 0; day < date.lengthOfYear(); day++) {
            if (date.getDayOfWeek() == DayOfWeek.FRIDAY && date.getDayOfMonth() == UNLUCKY_NUMBER) {
                result.addLast(date.toString());
            }
            date = date.plusDays(1);
        }
        return result;
    }

    public static String getNextFriday13th(@NotNull String dateString) {
        try {
            var date = LocalDate.parse(dateString);
            do {
                date = date.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
            } while (date.getDayOfMonth() != UNLUCKY_NUMBER);
            return date.toString();
        } catch (DateTimeParseException exception) {
            return null;
        }
    }
}
