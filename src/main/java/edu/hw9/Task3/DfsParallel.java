package edu.hw9.Task3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DfsParallel extends RecursiveTask<String> {

    private static final Logger LOGGER = LogManager.getLogger();

    private final Graph graph;

    private final String current;

    private final ConcurrentHashMap<String, Object> visited;

    public DfsParallel(Graph graph, String start, ConcurrentHashMap<String, Object> visited) {
        this.graph = graph;
        this.current = start;
        this.visited = visited;
    }

    public DfsParallel(Graph graph, String start) {
        this(graph, start, new ConcurrentHashMap<>());
    }

    @Override
    protected String compute() {
        if (!visited.containsKey(current)) {
            visited.putIfAbsent(current, new Object());

            List<RecursiveTask<String>> tasks = new ArrayList<>();
            List<String> neighbors = graph.getNeighbors(current);
            if (neighbors != null) {
                for (String neighbor : neighbors) {
                    if (!visited.containsKey(neighbor)) {
                        tasks.addLast(new DfsParallel(graph, neighbor, visited));
                    }
                }
            }

            tasks.forEach(ForkJoinTask::fork);

            tasks.forEach(ForkJoinTask::join);
        }
        LOGGER.info(current);
        return current;
    }
}
