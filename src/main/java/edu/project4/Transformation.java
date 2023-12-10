package edu.project4;

import java.util.function.Function;

@FunctionalInterface
public interface Transformation extends Function<Point, Point> {

    class Sinusoidal implements Transformation {
        @Override
        public Point apply(Point point) {
            return new Point(Math.sin(point.x()), Math.sin(point.y()));
        }
    }

    class Spherical implements Transformation {
        @Override
        public Point apply(Point point) {
            return new Point(
                point.x() / (point.x() * point.x() + point.y() * point.y()),
                point.y() / (point.x() * point.x() + point.y() * point.y())
            );
        }
    }

    class Polar implements Transformation {
        @Override
        public Point apply(Point point) {
            return new Point(
                Math.atan(point.y() / point.x()) / Math.PI,
                Math.sqrt(point.x() * point.x() + point.y() * point.y()) - 1
            );
        }
    }

    class Disc implements Transformation {
        @Override
        public Point apply(Point point) {
            return new Point(
                1 / Math.PI * Math.atan(point.y() / point.x())
                    * Math.sin(Math.PI * Math.sqrt(point.x() * point.x() + point.y() * point.y())),
                1 / Math.PI * Math.atan(point.y() / point.x())
                    * Math.cos(Math.PI * Math.sqrt(point.x() * point.x() + point.y() * point.y()))
            );
        }
    }
}
