package edu.project4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public record AffinCoeff(double a, double b, double c, double d, double e, double f, int red, int green, int blue) {

    private static final int ADDING_COEFF = 3;

    private static final int RGB_MAX_VALUE = 256;

    public static List<AffinCoeff> generate(int count) {
        List<AffinCoeff> coeffs = new ArrayList<>();
        while (coeffs.size() < count) {
            double a = ThreadLocalRandom.current().nextDouble(-1, 1);
            double b = ThreadLocalRandom.current().nextDouble(-1, 1);
            double c = ThreadLocalRandom.current().nextDouble(-ADDING_COEFF, ADDING_COEFF);
            double d = ThreadLocalRandom.current().nextDouble(-1, 1);
            double e = ThreadLocalRandom.current().nextDouble(-1, 1);
            double f = ThreadLocalRandom.current().nextDouble(-ADDING_COEFF, ADDING_COEFF);

            int red = ThreadLocalRandom.current().nextInt(0, RGB_MAX_VALUE);
            int green = ThreadLocalRandom.current().nextInt(0, RGB_MAX_VALUE);
            int blue = ThreadLocalRandom.current().nextInt(0, RGB_MAX_VALUE);

            if (a * a  + d * d < 1 && b * b + e * e < 1
                && a * a + b * b + d * d + e * e < 1 + (a * e - b * d) * (a * e - b * d)) {
                coeffs.addLast(new AffinCoeff(a, b, c, d, e, f, red, green, blue));
            }
        }
        return coeffs;
    }

    public Point transform(Point point) {
        return new Point(
            a * point.x() + b * point.y() + c,
            d * point.x() + e * point.y() + f
        );
    }
}
