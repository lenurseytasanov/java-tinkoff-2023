package edu.hw6.Task5;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static java.net.http.HttpClient.newHttpClient;

public final class HackerNews {

    private static final Logger LOGGER = LogManager.getLogger();

    private HackerNews() { }

    public static long[] hackerNewsTopStories() {
        var request = HttpRequest.newBuilder()
            .uri(URI.create("https://hacker-news.firebaseio.com/v0/topstories.json"))
            .GET()
            .build();

        try (HttpClient client = newHttpClient()) {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return Arrays.stream(response.body().replaceAll("[\\[\\]]", "").split(","))
                .mapToLong(Long::parseLong)
                .toArray();
        } catch (InterruptedException | IOException e) {
            LOGGER.error("", e);
            return new long[0];
        }
    }

    public static Optional<String> news(long id) {
        var request = HttpRequest.newBuilder()
            .uri(URI.create("https://hacker-news.firebaseio.com/v0/item/" + id + ".json"))
            .GET()
            .build();

        try (HttpClient client = newHttpClient()) {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());

            Pattern pattern = Pattern.compile("\"title\":\"(.*?)\"");
            var matcher = pattern.matcher(response.body());
            return matcher.find() ? Optional.of(matcher.group(1)) : Optional.empty();
        } catch (IOException | InterruptedException e) {
            LOGGER.error("", e);
            return Optional.empty();
        }
    }
}
