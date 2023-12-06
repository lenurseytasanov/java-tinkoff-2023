package edu.hw9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.DoubleStream;
import org.jetbrains.annotations.NotNull;

public class StatsCollector {

    private final List<Map<String, String>> stats = Collections.synchronizedList(new ArrayList<>());

    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    public void push(@NotNull Metric metric, double[] data) {
        executorService.execute(() -> {
            double result = getStat(metric, data);
            stats.add(Map.of(
                "metric", metric.toString(),
                "result", Double.toString(result),
                "data", Arrays.toString(data)
            ));
        });
    }

    public List<Map<String, String>> stats() {
        executorService.close();
        return stats;
    }

    private double getStat(Metric metric, double[] data) {
        return switch (metric) {
            case SUM -> DoubleStream.of(data).sum();
            case AVG -> DoubleStream.of(data).average().orElseThrow();
            case MAX -> DoubleStream.of(data).max().orElseThrow();
            case MIN -> DoubleStream.of(data).min().orElseThrow();
        };
    }

    public enum Metric {
        SUM,
        AVG,
        MAX,
        MIN
    }
}
