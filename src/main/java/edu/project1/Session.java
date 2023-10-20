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
        if (isSuccessful && !word.contains("*")) {
            return getWinResult();
        } else if (isSuccessful) {
            return getHitResult();
        } else if (++attempts == gameStates.length - 1) {
            return getLostResult();
        } else {
            return getMissedResult();
        }
    }

    public GuessResult giveUp() {
        return getLostResult();
    }

    private GuessResult getWinResult() {
        return new GuessResult(new String(userInput), gameStates[attempts], "Win!", true);
    }

    private GuessResult getHitResult() {
        return new GuessResult(new String(userInput), gameStates[attempts], "Hit!", false);
    }

    private GuessResult getLostResult() {
        return new GuessResult(new String(userInput), gameStates[attempts], "Lost!", true);
    }

    private GuessResult getMissedResult() {
        return new GuessResult(new String(userInput), gameStates[attempts], "Missed!", false);
    }
}
