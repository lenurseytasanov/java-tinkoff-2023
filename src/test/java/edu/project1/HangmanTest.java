package edu.project1;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import static org.junit.jupiter.api.Assertions.*;

public class HangmanTest {
    private InputStream getInputStream(String string) {
        return new ByteArrayInputStream(string.getBytes(StandardCharsets.UTF_8));
    }

    @Test
    void winTest() {
        var game = new ConsoleHangman(getInputStream(""), new Dictionary(new String[] {"test"}));
        var session = game.getSession();
        assertEquals("****", new String(session.getUserInput()));
        assertEquals(0, session.getAttempts());

        assertTrue(session.guess('t') instanceof GuessResult.SuccessfulGuess);
        assertEquals("t**t", new String(session.getUserInput()));
        assertEquals(0, session.getAttempts());

        session.guess('e');
        assertEquals("te*t", new String(session.getUserInput()));

        assertTrue(session.guess('s') instanceof GuessResult.Win);
        assertEquals("test", new String(session.getUserInput()));
    }

    @Test
    void defeatTest() {
        var game = new ConsoleHangman(getInputStream(""), new Dictionary(new String[] {"test"}));
        var session = game.getSession();
        assertTrue(session.guess('u') instanceof GuessResult.FailedGuess);
        assertEquals(1, session.getAttempts());
        assertEquals("****", new String(session.getUserInput()));

        session.guess('y');
        assertEquals(2, session.getAttempts());

        session.guess('i');
        session.guess('q');

        assertTrue(session.guess('z') instanceof GuessResult.Defeat);
        assertEquals(5, session.getAttempts());
        assertEquals("****", new String(session.getUserInput()));
    }

    @Test
    void incorrectInputTest() {
        var input = String.join("\n", "tEsT1", "tt");
        var game = new ConsoleHangman(getInputStream(input), new Dictionary(new String[] {"test"}));
        var session = game.getSession();
        var state0 = session.getUserInput();
        game.run();
        assertEquals(state0, session.getUserInput());
    }

    @Test
    void giveUpTest() {
        var game = new ConsoleHangman(getInputStream(""), new Dictionary(new String[] {"test"}));
        var session = game.getSession();
        assertTrue(session.giveUp() instanceof GuessResult.Defeat);
    }

    @Test
    void incorrectWordTest() {
        var input = "k";
        assertThrows(IllegalArgumentException.class, () -> new ConsoleHangman(
            getInputStream(input), new Dictionary(new String[] {"", "king"})));
    }
}
