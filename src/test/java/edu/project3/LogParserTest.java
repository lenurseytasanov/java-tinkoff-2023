package edu.project3;

import org.junit.jupiter.api.Test;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogParserTest {
    @Test
    void getPathsTest() {
        var files1 = LogParser.getPaths("logs/log*");
        var files2 = LogParser.getPaths("logs/*.txt");

        assertEquals(files1, files2);

        var files = LogParser.getPaths(".mvn/**/*");
        assertEquals(2, files.size());
    }

    @Test
    void getRecordsTest() {
        var recs1 = LogParser.getLogRecords(List.of(Paths.get("logs/logs1.txt")));
        assertFalse(recs1.toList().isEmpty());

        var recs2 = LogParser.getLogRecords(List.of(Paths.get("logs/logs1000.txt")));
        assertTrue(recs2.toList().isEmpty());

        var url = "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs";
        var recs3 = LogParser.getLogRecordsUri(url);
        assertFalse(recs3.toList().isEmpty());
    }

    @Test
    void getReportTest() {
        assertNull(LogParser.getReport(Map.of()));

        assertNotNull(LogParser.getReport(Map.of("path", "", "from", "-", "to", "-")));
    }

    @Test
    void getRecordTest() {
        var rec = LogRecord.getRecord("91.234.194.89 - - [17/May/2015:08:05:22 +0000] \"GET /downloads/product_2 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.9.7.9)\"");
        assert rec != null;
        assertEquals("/downloads/product_2", rec.source());
        assertEquals(304, rec.statusCode());
        assertEquals(0, rec.dataSize());

        var rec2 = LogRecord.getRecord("aaaaa");
        assertNull(rec2);
    }
}
