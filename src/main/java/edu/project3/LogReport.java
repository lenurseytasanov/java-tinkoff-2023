package edu.project3;

import java.nio.file.Path;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.jetbrains.annotations.NotNull;

public record LogReport(
    Map<String, String> info, List<Map.Entry<String, Long>> sources, List<Map.Entry<String, Long>> responses
) {
    private final static int TOP_LIMIT = 3;

    public static LogReport getReport(
        @NotNull List<Path> files, @NotNull String dateFrom,
        @NotNull String dateTo, @NotNull Stream<LogRecord> records
    ) {
        var filtered = records.filter(rec ->
                (dateFrom.equals("-") || rec.time().isAfter(OffsetDateTime.parse(dateFrom)))
                && (dateTo.equals("-") || rec.time().isBefore(OffsetDateTime.parse(dateTo))))
            .toList();

        var info = Map.of(
            "files", files.stream()
                .map(file -> file.getFileName().toString()).collect(Collectors.joining(";")),
            "date_from", dateFrom,
            "date_to", dateTo,
            "count", Integer.toString(filtered.size()),
            "avg", Integer.toString((int) filtered.stream()
                .mapToInt(rec -> rec.dataSize().intValue()).average().orElse(0)));

        var sources = filtered.stream()
            .collect(Collectors.groupingBy(LogRecord::source, Collectors.counting()))
            .entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .limit(TOP_LIMIT)
            .toList();

        var responses = filtered.stream()
            .collect(Collectors.groupingBy(rec -> rec.statusCode().toString(), Collectors.counting()))
            .entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .limit(TOP_LIMIT)
            .toList();

        return new LogReport(info, sources, responses);
    }
}
