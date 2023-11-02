package edu.project2;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {
    @Test
    void IllegalArgumentsTest() {
        Generator generator = new DfsCarvingGenerator();
        assertThrows(IllegalArgumentException.class, () -> generator.generate(1, 2));
        var maze = new Maze(3, 3);
        assertThrows(IllegalArgumentException.class, () -> maze.getNeighbors(new Cell(-1, 1, Cell.Type.WALL)));
        assertDoesNotThrow(() -> maze.getNeighbors(new Cell(1, 1, Cell.Type.WALL)));
    }

    private static @NotNull Stream<Arguments> getArguments() {
        return Stream.of(
            Arguments.of(
                new Maze(new String[] {
                    "WWWWW",
                    "WPPWW",
                    "WWPWW",
                    "WWPPW",
                    "WWWWW"
                }), List.of(
                    new Coordinate(1, 1), new Coordinate(1, 2), new Coordinate(2, 2),
                    new Coordinate(3, 2), new Coordinate(3, 3)),
                new Coordinate(1, 1), new Coordinate(3, 3)),
            Arguments.of(
                new Maze(new String[] {
                    "WWW",
                    "WPW",
                    "WWW",
                }), List.of(new Coordinate(1, 1)),
                new Coordinate(1, 1), new Coordinate(1, 1)),
            Arguments.of(
                new Maze(new String[] {
                    "WWWW",
                    "WPPW",
                    "WWWW"
                }), List.of(new Coordinate(1, 1), new Coordinate(1, 2)),
                new Coordinate(1, 1), new Coordinate(1, 2)),
            Arguments.of(
                new Maze(new String[] {
                    "WWWWW",
                    "WPWPW",
                    "WWWWW"
                }), List.of(),
                new Coordinate(1, 1), new Coordinate(1, 3)
            ),
            Arguments.of(
                new Maze(new String[] {
                    "WWW",
                    "WWW",
                    "WWW"
                }), List.of(),
                new Coordinate(1, 1), new Coordinate(1, 1)
            )
        );
    }

    @ParameterizedTest
    @MethodSource("getArguments")
    void pathFindingTest(Maze maze, List<Coordinate> path, Coordinate start, Coordinate end) {
        Solver solver = new BfsSolver();
        var actualPath = solver.solve(maze, start, end);
        assertEquals(path, actualPath);
    }

    private final static int ITERATIONS_COUNT = 1000;
    private final static int MIN_MAZE_SIZE = 3;
    private final static int MAX_MAZE_SIZE = 50;

    @Test
    void randomPathFindingTest() {
        Generator generator = new DfsCarvingGenerator();
        Solver solver = new BfsSolver();
        var random = new Random();
        for (var i = 0; i < ITERATIONS_COUNT; i++) {
            var width = random.nextInt(MIN_MAZE_SIZE, MAX_MAZE_SIZE + 1);
            var height = random.nextInt(MIN_MAZE_SIZE, MAX_MAZE_SIZE + 1);
            var maze = generator.generate(height, width);
            var available = Arrays.stream(maze.getGrid()).flatMap(Arrays::stream)
                .filter(cell -> cell.type() == Cell.Type.PASSAGE).toList();
            var start = available.get(random.nextInt(available.size())).getCoordinate();
            var end = maze.getFarthestPoint(start);
            var path = solver.solve(maze, start, end);
            assertNotNull(path);
            assertEquals(end, path.getLast());
        }
    }

    @Test
    void standardRenderTest() {
        var maze = new Maze(new String[] {
            "WWWWW",
            "WPWWW",
            "WPPWW",
            "WWPPW",
            "WWWWW"
        });
        var path = List.of(
            new Coordinate(1, 1), new Coordinate(2, 1), new Coordinate(2, 2),
            new Coordinate(3, 2), new Coordinate(3, 3)
        );
        Renderer renderer = new StdRenderer();
        var actual = renderer.render(maze, path);
        var expected = """
            ██████████
            ██* ██████
            ██* * ████
            ████* * ██
            ██████████""";
        assertEquals(expected, actual);
    }
}
