package edu.project1;

import java.util.Arrays;

public class Session {
    private final String answer;
    private final char[] userInput;
    private int attempts = 0;
    private final String[] gameStates = new String[] {"""
 ____\s
|
|
|
|""", """
 ____
|   0
|   |
|
|""", """
 ____
|   0
|  /|
|
|""", """
 ____
|   0
|  /|\\
|
|""", """
 ____
|   0
|  /|\\
|  /
|""", """
 ____
|   0
|  /|\\
|  / \\
|"""};


    public Session(Dictionary dictionary) {
        answer = dictionary.getRandomWord();
        userInput = new char[answer.length()];
        Arrays.fill(userInput, '*');
    }

    public GuessResult guess(char letter) {
        var isSuccessful = false;
        for (var i = 0; i < answer.length(); i++) {
            if (answer.charAt(i) == letter) {
                userInput[i] = letter;
                isSuccessful = true;
            }
        }
        var word = new String(userInput);
        if (attempts < gameStates.length - 1 && !word.contains("*")) {
            return getWinResult();
        } else if (attempts < gameStates.length - 1 && isSuccessful) {
            return getHitResult();
        } else if (attempts == gameStates.length - 1 || ++attempts == gameStates.length - 1) {
            return getLostResult();
        } else {
            return getMissedResult();
        }
    }

    public GuessResult giveUp() {
        return getLostResult();
    }

    public int getAttempts() {
        return attempts;
    }

    public char[] getUserInput() {
        return userInput;
    }

    private GuessResult getWinResult() {
        return new GuessResult.Win(new String(userInput), gameStates[attempts]);
    }

    private GuessResult getHitResult() {
        return new GuessResult.SuccessfulGuess(new String(userInput), gameStates[attempts]);
    }

    private GuessResult getLostResult() {
        return new GuessResult.Defeat(new String(userInput), gameStates[attempts]);
    }

    private GuessResult getMissedResult() {
        return new GuessResult.FailedGuess(new String(userInput), gameStates[attempts]);
    }
}
