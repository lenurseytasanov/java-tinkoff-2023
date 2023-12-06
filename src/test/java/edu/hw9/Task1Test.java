package edu.hw9;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test {
    @Test
    void test() {
        // Arrange
        StatsCollector collector = new StatsCollector();
        var expected = List.of(0.05, 1.39, 6.95);

        // Act
        collector.push(StatsCollector.Metric.AVG, new double[] {0.1, 0.05, 1.4, 5.1, 0.3});
        collector.push(StatsCollector.Metric.SUM, new double[] {0.1, 0.05, 1.4, 5.1, 0.3});
        collector.push(StatsCollector.Metric.MIN, new double[] {0.1, 0.05, 1.4, 5.1, 0.3});
        var stats = collector.stats();
        var actual = stats.stream()
            .map(map -> Double.parseDouble(map.get("result")))
            .sorted().toList();

        // Assert
        assertEquals(expected.size(), actual.size());
        for (var i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i), 0.001);
        }
    }
}
