package edu.hw4;

public class ValidationError extends Exception {
    public ValidationError() { }

    public ValidationError(String message) {
        super(message);
    }
}
