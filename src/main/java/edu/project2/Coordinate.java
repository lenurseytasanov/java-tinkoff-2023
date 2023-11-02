package edu.project2;

import static java.lang.Math.sqrt;

public record Coordinate(int row, int col) {
    public static double getDistance(Coordinate point1, Coordinate point2) {
        return sqrt((point2.row - point1.row) * (point2.row - point1.row)
            + (point2.col + point1.col) * (point2.col - point1.col));
    }
}
