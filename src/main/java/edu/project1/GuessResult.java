package edu.project1;

import org.apache.logging.log4j.Logger;

public class GuessResult {
    private final String userInput;
    private final String state;
    private final String message;
    private final boolean isComplete;

    public boolean isComplete() {
        return isComplete;
    }

    public GuessResult(String userInput, String state, String message, boolean isComplete) {
        this.userInput = userInput;
        this.state = state;
        this.message = message;
        this.isComplete = isComplete;
    }

    public void print(Logger logger) {
        logger.info(String.format("%s\nThe word: %s\n%s", message, userInput, state));
    }
}
