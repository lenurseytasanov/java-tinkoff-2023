package edu.project1;

import org.apache.logging.log4j.Logger;

public sealed interface GuessResult {
    void print(Logger logger);

    record SuccessfulGuess(String userInput, String state) implements  GuessResult {
        public void print(Logger logger) {
            logger.info(String.format("Hit!\nThe word: %s\n%s", userInput, state));
        }
    }

    record FailedGuess(String userInput, String state) implements GuessResult {
        public void print(Logger logger) {
            logger.info(String.format("Missed!\nThe word: %s\n%s", userInput, state));
        }
    }

    record Win(String userInput, String state) implements GuessResult {
        public void print(Logger logger) {
            logger.info(String.format("Win!\nThe word: %s\n%s", userInput, state));
        }
    }

    record Defeat(String userInput, String state) implements GuessResult {
        public void print(Logger logger) {
            logger.info(String.format("Lost!\nThe word: %s\n%s", userInput, state));
        }
    }
}
