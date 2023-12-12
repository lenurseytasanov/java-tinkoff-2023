package edu.hw10;

public interface FibCalculator {

    @Cache(persist = true)
    long fibWithCaching(int number);

    long fib(int number);
}
