package edu.project3;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public final class Main {
    private Main() { }

    public static void main(String[] args) {
        var formatLabel = "format";
        var formatDefault = "markdown";

        var map = new HashMap<>(Map.of(
            "path", "-",
            "from", "-",
            "to", "-",
            formatLabel, formatDefault
        ));
        for (var i = 1; i < args.length; i += 2) {
            map.replace(args[i - 1].substring(2), args[i]);
        }

        var writer = new LogWriter(LogParser.getReport(map));

        if (map.get(formatLabel).equals(formatDefault)) {
            var file = new File("report.md");

            try {
                if (!file.exists()) {
                    Files.createFile(file.toPath());
                }
                writer.print(Format.FORMAT.MARKDOWN, Files.newOutputStream(file.toPath()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (map.get(formatLabel).equals("adoc")) {
            var file = new File("report.adoc");

            try {
                if (!file.exists()) {
                    Files.createFile(file.toPath());
                }
                writer.print(Format.FORMAT.ADOC, Files.newOutputStream(file.toPath()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
