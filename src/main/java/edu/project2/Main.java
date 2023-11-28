package edu.project2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Main {

    private final static Logger LOGGER = LogManager.getLogger();

    private final static int STD_MAZE_WIDTH = 30;

    private final static int STD_MAZE_HEIGHT = 20;


    private Main() { }

    public static void main(String[] args) {
        Generator generator = new DfsCarvingGenerator();
        Solver solver = new BfsSolver();
        Renderer renderer = new StdRenderer();
        var maze = generator.generate(STD_MAZE_HEIGHT, STD_MAZE_WIDTH);
        var start = new Coordinate(1, 1);
        var path = solver.solve(maze, start, maze.getFarthestPoint(start));
        LOGGER.info('\n' + renderer.render(maze, path));
    }
}
