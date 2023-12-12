package edu.hw10;

public class FibSimpleCalculator implements FibCalculator {
    @Override
    public long fib(int number) {
        if (number <= 0) {
            return -1;
        }
        int a = 0;
        int b = 1;
        for (int i = 1; i < number; i++) {
            int temp = b;
            b = a + b;
            a = temp;
        }
        return b;
    }

    @Override
    public long fibWithCaching(int number) {
        return fib(number);
    }
}
