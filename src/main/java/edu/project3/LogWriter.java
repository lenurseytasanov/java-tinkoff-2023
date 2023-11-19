package edu.project3;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.time.OffsetDateTime;
import java.util.Map;

public class LogWriter {
    private final LogReport report;
    private final static Map<String, String> CODE_DESCRIPTION = Map.of(
        "200", "OK",
        "404", "Not Found",
        "500", "Internal Server Error"
    );
    private final static String DATE_DEFAULT_VALUE = "-";

    public LogWriter(LogReport report) {
        this.report = report;
    }

    public void print(Format.FORMAT format, OutputStream stream) {
        try (var writer = new BufferedWriter(new OutputStreamWriter(stream))) {
            var dateFrom = report.info().get("date_from");
            var dateTo = report.info().get("date_to");
            var info = format.getFormat().get("info").formatted(
                report.info().get("files").replace(";", ", "),
                dateFrom.equals("-")
                    ? DATE_DEFAULT_VALUE
                    : OffsetDateTime.parse(dateFrom).toString(),
                dateTo.equals("-")
                    ? DATE_DEFAULT_VALUE
                    : OffsetDateTime.parse(dateTo).toString(),
                report.info().get("count"),
                report.info().get("avg")
            );
            var sources = new StringBuilder(format.getFormat().get("sources"));
            var responses = new StringBuilder(format.getFormat().get("responses"));

            if (format == Format.FORMAT.MARKDOWN) {
                for (var entry : report.sources()) {
                    sources.append("|%s|%d|\n".formatted(entry.getKey(), entry.getValue()));
                }

                for (var entry : report.responses()) {
                    responses.append("|%s|%s|%d|\n".formatted(
                        entry.getKey(),
                        CODE_DESCRIPTION.get(entry.getKey()), entry.getValue())
                    );
                }
            } else if (format == Format.FORMAT.ADOC) {
                var statEnd = "|===\n\n";
                for (var entry : report.sources()) {
                    sources.append("|%s|%d\n".formatted(entry.getKey(), entry.getValue()));
                }
                sources.append(statEnd);

                for (var entry : report.responses()) {
                    responses.append("|%s|%s|%d\n".formatted(
                        entry.getKey(), CODE_DESCRIPTION.get(entry.getKey()), entry.getValue())
                    );
                }
                responses.append(statEnd);
            }

            writer.write(info + sources + responses);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
