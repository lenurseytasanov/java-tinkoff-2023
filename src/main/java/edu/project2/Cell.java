package edu.project2;

public record Cell(int row, int col, Type type) {
    public Coordinate getCoordinate() {
        return new Coordinate(this.row, this.col);
    }

    public enum Type { WALL, PASSAGE }
}
