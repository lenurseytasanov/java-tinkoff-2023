package edu.project2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Maze {

    private final static Logger LOGGER = LogManager.getLogger();

    private final static int MIN_MAZE_SIZE = 3;

    private final int height;

    private final int width;

    private final Cell[][] grid;


    public Maze(int width, int height) {
        if (height < MIN_MAZE_SIZE || width < MIN_MAZE_SIZE) {
            var e = new IllegalArgumentException("Size must be at least '3'");
            LOGGER.log(Level.ERROR, "exception: ", e);
            throw e;
        }
        this.width = width;
        this.height = height;
        this.grid = new Cell[height][width];
        for (var i = 0; i < height; i++) {
            for (var j = 0; j < width; j++) {
                grid[i][j] = new Cell(i, j, Type.WALL);
            }
        }
    }

    public Maze(String[] map) {
        this(map[0].length(), map.length);
        for (var i = 0; i < height; i++) {
            for (var j = 0; j < width; j++) {
                this.grid[i][j] = new Cell(i, j, map[i].charAt(j) == 'W' ? Type.WALL : Type.PASSAGE);
            }
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public List<Cell> getNeighbors(Cell cell) {
        if (cell.col() < 0 || cell.col() >= this.width || cell.row() < 0 || cell.row() >= this.height) {
            throw new IllegalArgumentException("Cell is not belong to maze");
        }
        List<Cell> result = new ArrayList<>();
        if (cell.col() > 1) {
            result.add(this.getGrid()[cell.row()][cell.col() - 1]);
        }
        if (cell.col() < this.getWidth() - 2) {
            result.add(this.getGrid()[cell.row()][cell.col() + 1]);
        }
        if (cell.row() > 1) {
            result.add(this.getGrid()[cell.row() - 1][cell.col()]);
        }
        if (cell.row() < this.getHeight() - 2) {
            result.add(this.getGrid()[cell.row() + 1][cell.col()]);
        }
        return result;
    }

    public Coordinate getFarthestPoint(Coordinate start) {
        return Arrays.stream(this.grid).flatMap(Arrays::stream)
            .filter(cell -> cell.type() == Type.PASSAGE)
            .map(cell -> new Coordinate(cell.row(), cell.col()))
            .max(Comparator.comparingDouble(point -> Coordinate.getDistance(start, point)))
            .orElseThrow(NoSuchElementException::new);
    }
}
