package edu.hw2.Task1;

public sealed interface Expr {
    double evaluate();

    record Constant(double number) implements Expr {
        public double evaluate() {
            return number;
        }
    }

    record Negate(Expr x, Expr y) implements Expr {
        public Negate(Expr y) {
            this(new Constant(0), y);
        }

        public Negate(double y) {
            this(new Constant(y));
        }

        public Negate(double x, double y) {
            this(new Constant(x), new Constant(y));
        }

        public Negate(Expr x, double y) {
            this(x, new Constant(y));
        }

        public Negate(double x, Expr y) {
            this(new Constant(x), y);
        }

        public double evaluate() {
            return x.evaluate() - y.evaluate();
        }
    }

    record Exponent(Expr x, Expr y) implements Expr {
        public Exponent(double x, double y) {
            this(new Constant(x), new Constant(y));
        }

        public Exponent(Expr x, double y) {
            this(x, new Constant(y));
        }

        public Exponent(double x, Expr y) {
            this(new Constant(x), y);
        }

        public double evaluate() {
            return Math.pow(x.evaluate(), y.evaluate());
        }
    }

    record Addition(Expr x, Expr y) implements Expr {
        public Addition(double x, double y) {
            this(new Constant(x), new Constant(y));
        }

        public Addition(Expr x, double y) {
            this(x, new Constant(y));
        }

        public Addition(double x, Expr y) {
            this(new Constant(x), y);
        }

        public double evaluate() {
            return x.evaluate() + y.evaluate();
        }
    }

    record Multiplication(Expr x, Expr y) implements Expr {
        public Multiplication(double x, double y) {
            this(new Constant(x), new Constant(y));
        }

        public Multiplication(Expr x, double y) {
            this(x, new Constant(y));
        }

        public Multiplication(double x, Expr y) {
            this(new Constant(x), y);
        }

        public double evaluate() {
            return x.evaluate() * y.evaluate();
        }
    }
}
