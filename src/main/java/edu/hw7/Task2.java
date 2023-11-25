package edu.hw7;

import java.math.BigInteger;
import java.util.stream.IntStream;

public final class Task2 {
    private Task2() { }

    public static BigInteger getFactorialParallel(int arg) {
        if (arg < 0) {
            throw new IllegalArgumentException();
        }
        return IntStream.rangeClosed(1, arg)
            .boxed()
            .parallel()
            .map(n -> new BigInteger(String.valueOf(n)))
            .reduce(BigInteger.ONE, BigInteger::multiply);
    }
}
