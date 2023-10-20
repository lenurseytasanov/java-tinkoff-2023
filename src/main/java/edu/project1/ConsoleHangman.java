package edu.project1;

import java.io.InputStream;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConsoleHangman {
    private final Session session;
    private final Scanner scanner;
    private final static Logger LOGGER = LogManager.getLogger();

    public ConsoleHangman(InputStream source, Dictionary dictionary) {
        scanner = new Scanner(source);
        session = new Session(dictionary);
    }

    public void run() {
        LOGGER.info("HANGMAN");
        GuessResult result;
        do {
            result = tryGuess();
            printResult(result);
        } while (!result.isComplete());
    }

    private GuessResult tryGuess() {
        var input = "";
        do {
            LOGGER.info("Guess a letter or type 'giveup': ");
            input = scanner.next();
        } while (!input.matches("[A-Za-z]|giveup"));
        if (input.equals("giveup")) {
            return session.giveUp();
        }
        var chr = input.charAt(0);
        return session.guess(chr);
    }

    private void printResult(GuessResult result) {
        result.print(LOGGER);
    }
}
