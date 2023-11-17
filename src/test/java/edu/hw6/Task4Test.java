package edu.hw6;

import edu.hw6.Task4.Task4;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task4Test {
    @Test
    void standardTest() {
        var path = Paths.get("src/test/java/edu/hw6/task4.txt");
        Task4.writeFile(path);
        var expected = "Programming is learned by writing programs. ― Brian Kernighan";
        try {
            var data = Files.readString(path, StandardCharsets.UTF_8);
            assertEquals(expected, data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
