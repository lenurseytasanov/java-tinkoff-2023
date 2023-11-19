package edu.project3;

import org.junit.jupiter.api.Test;

public class MainTest {
    @Test
    void test() {
        Main.main(new String[] {
            "--path", "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs",
            "--from", "2015-05-22T22:05:31+00:00",
            "--to", "2015-05-28T22:05:31+00:00",
            "--format", "markdown"
        });
    }
}
