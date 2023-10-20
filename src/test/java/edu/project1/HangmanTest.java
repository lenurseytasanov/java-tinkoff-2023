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
    void test1() {
        var input = "h\nj\na\nt\ny\ne\nq\np\nc";
        var game = new ConsoleHangman(getInputStream(input), new Dictionary());
        game.run();
    }

    @Test
    void invalidInputTest() {
        var input = "t";
        var game = new ConsoleHangman(getInputStream(input), new Dictionary(new String[] {""}));
        assertThrows(IllegalArgumentException.class, game::run);
    }
}
