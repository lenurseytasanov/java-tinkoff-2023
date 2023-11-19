package edu.project3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.jetbrains.annotations.NotNull;
import static java.net.http.HttpClient.newHttpClient;

public final class LogParser {
    private LogParser() { }

    public static List<Path> getPaths(@NotNull String glob) {
        var matcher = FileSystems.getDefault().getPathMatcher("glob:" + glob);
        var files = new ArrayList<Path>();

        try {
            Files.walkFileTree(Paths.get(""), new FillListVisitor(matcher, files));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return files;
    }

    public static Stream<LogRecord> getLogRecords(@NotNull List<Path> paths) {
        return paths.stream().flatMap(path -> {
            try (var reader = new BufferedReader(new InputStreamReader(Files.newInputStream(path)))) {
                return reader.lines().toList().stream().map(LogRecord::getRecord);
            } catch (IOException e) {
                return Stream.of();
            }
        });
    }

    public static Stream<LogRecord> getLogRecordsUri(@NotNull String url) {
        var request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .GET()
            .build();

        try (HttpClient client = newHttpClient()) {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return Arrays.stream(response.body().split("\n")).map(LogRecord::getRecord);
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static LogReport getReport(@NotNull Map<String, String> args) {
        try {
            var path = args.get("path");
            var paths = getPaths(path);
            Stream<LogRecord> records;
            if (path.startsWith("http")) {
                records = getLogRecordsUri(path);
            } else {
                records = getLogRecords(paths);
            }

            return LogReport.getReport(paths, args.get("from"), args.get("to"), records);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
