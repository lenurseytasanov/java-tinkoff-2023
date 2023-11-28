package edu.project2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class BfsSolver implements Solver {
    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        var queue = new LinkedList<Coordinate>();
        queue.addLast(start);
        var transitions = new HashMap<Coordinate, Coordinate>();
        var current = new Coordinate(-1, -1);
        while (!queue.isEmpty() && !current.equals(end)) {
            current = queue.removeFirst();
            var nextSteps = maze.getNeighbors(maze.getGrid()[current.row()][current.col()]).stream()
                .filter(cell -> cell.type() == Type.PASSAGE)
                .map(Cell::getCoordinate)
                .filter(point -> !transitions.containsKey(point)).toList();
            for (var next : nextSteps) {
                queue.addLast(next);
                transitions.put(next, current);
            }
        }
        if (!current.equals(end) || maze.getGrid()[current.row()][current.col()].type() == Type.WALL) {
            return List.of();
        }
        var path = new ArrayList<Coordinate>();
        path.addFirst(current);
        while (!current.equals(start)) {
            current = transitions.get(current);
            path.addFirst(current);
        }
        return path;
    }
}
