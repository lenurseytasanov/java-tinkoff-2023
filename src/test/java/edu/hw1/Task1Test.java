package edu.hw1;

import java.util.Random;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Task1Test {
    private static final Random random = new Random();
    private static final int SECONDS_NUMBER = 60;

    @Test
    void invalidStringTest() {
        assertEquals(Task1.minutesToSeconds(null), -1);
        assertEquals(Task1.minutesToSeconds("text"), -1);
        assertEquals(Task1.minutesToSeconds("mm:ss"), -1);
    }

    @Test
    void invalidNumberTest() {
        assertEquals(Task1.minutesToSeconds("-143:00"), -1);
        assertEquals(Task1.minutesToSeconds("00:-30"), -1);
        assertEquals(Task1.minutesToSeconds("00:60"), -1);
        assertEquals(Task1.minutesToSeconds("100:100"), -1);
    }

    @Test
    void randomCorrectInputTest() {
        for (var i = 0; i < 100; i++) {
            var seconds = random.nextInt(1000);
            var input = String.format("%d:%d", seconds / SECONDS_NUMBER, seconds % SECONDS_NUMBER);
            assertEquals(seconds, Task1.minutesToSeconds(input));
        }
    }
}
