package edu.project1;

import java.io.InputStream;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConsoleHangman {
    private final Session session;
    private final Scanner scanner;
    private final static Logger LOGGER = LogManager.getLogger();

    private final static String LOSE_COMMAND = "giveup";

    public ConsoleHangman(InputStream source, Dictionary dictionary) {
        scanner = new Scanner(source);
        session = new Session(dictionary);
    }

    public void run() {
        LOGGER.info("HANGMAN");
        GuessResult result = null;
        while (!(result instanceof GuessResult.Win || result instanceof GuessResult.Defeat)) {
            result = tryGuess();
            printResult(result);
        }
    }

    private GuessResult tryGuess() {
        var input = "";
        LOGGER.info("Guess a letter or type 'giveup': ");
        while (!input.matches("giveup|[A-Za-z]")) {
            input = scanner.hasNext() ? scanner.next() : LOSE_COMMAND;
        }
        if (input.equals(LOSE_COMMAND)) {
            return session.giveUp();
        }
        var chr = input.toLowerCase().charAt(0);
        return session.guess(chr);
    }

    private void printResult(GuessResult result) {
        result.print(LOGGER);
    }

    public Session getSession() {
        return session;
    }
}
