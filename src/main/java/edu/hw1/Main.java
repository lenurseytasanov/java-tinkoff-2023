package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public final class Main {
    private static final Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

    public static void main(String[] args) {
        printHelloWorld();
    }

    private static void printHelloWorld() {
        LOGGER.info("Привет, мир!");
    }
}
