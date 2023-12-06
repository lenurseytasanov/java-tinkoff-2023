package edu.hw9;

import edu.hw9.Task3.DfsParallel;
import edu.hw9.Task3.Graph;
import java.util.concurrent.ForkJoinPool;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class Task3Test {

    private static final Graph GRAPH = new Graph();

    @BeforeAll
    static void setUp() {
        GRAPH.addVertex("A");
        GRAPH.addVertex("B");
        GRAPH.addVertex("C");
        GRAPH.addVertex("D");
        GRAPH.addVertex("E");

        GRAPH.addEdge("A", "B");
        GRAPH.addEdge("A", "C");
        GRAPH.addEdge("B", "D");
        GRAPH.addEdge("D", "E");
    }

    @Test
    void test() {
        try (var pool = new ForkJoinPool()) {
            var task = new DfsParallel(GRAPH, "A");
            pool.invoke(task);
        }
    }
}
