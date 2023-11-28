package edu.project2;

import java.util.List;

public class StdRenderer implements Renderer {

    private final static String WALL = "██";

    private final static String PASSAGE = "  ";

    @Override
    public String render(Maze maze) {
        var grid = maze.getGrid();
        var result = new StringBuilder();
        for (var i = 0; i < maze.getHeight(); i++) {
            for (var j = 0; j < maze.getWidth(); j++) {
                result.append(grid[i][j].type() == Type.WALL ? WALL : PASSAGE);
            }
            result.append('\n');
        }
        return result.toString();
    }

    @Override
    public String render(Maze maze, List<Coordinate> path) {
        var mazeView = render(maze).split("\n");
        for (var point : path) {
            var chars = mazeView[point.row()].toCharArray();
            chars[point.col() * 2] = '*';
            mazeView[point.row()] = new String(chars);
        }
        return String.join("\n", mazeView);
    }
}
