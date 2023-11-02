package edu.project2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class DfsCarvingGenerator implements Generator {
    @Override
    public Maze generate(int height, int width) {
        var maze = new Maze(width, height);
        var grid = maze.getGrid();
        var stack = new LinkedList<Cell>();
        stack.addLast(grid[1][1]);
        while (!stack.isEmpty()) {
            var current = stack.removeLast();
            if (maze.getNeighbors(current).stream()
                .filter(cell -> cell.type() == Cell.Type.PASSAGE).count() > 1) {
                continue;
            }
            grid[current.row()][current.col()] = new Cell(current.row(), current.col(), Cell.Type.PASSAGE);
            var nextCells = new ArrayList<>(maze.getNeighbors(current).stream()
                .filter(cell -> cell.type() == Cell.Type.WALL).toList());
            Collections.shuffle(nextCells);
            for (var next : nextCells) {
                stack.addLast(next);
            }
        }
        return maze;
    }
}
