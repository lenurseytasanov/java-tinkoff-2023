package edu.project4;

public record Rectangle(double x0, double y0, double x, double y) {
    public boolean contains(Point point) {
        return point.x() >= x0 && point.x() <= x && point.y() >= y0 && point.y() <= y;
    }
}
