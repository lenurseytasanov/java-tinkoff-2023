package edu.hw6;

import edu.hw6.Task3.AbstractFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task3Test {
    private final static List<Path> FILES = List.of(
        Paths.get("src/test/java/edu/hw6/Task3Files/test1.txt"),
        Paths.get("src/test/java/edu/hw6/Task3Files/test2.txt"),
        Paths.get("src/test/java/edu/hw6/Task3Files/test.png"),
        Paths.get("src/test/java/edu/hw6/Task3Files/audio.wav")
    );
    private final static Path DIR = Paths.get("src/test/java/edu/hw6/Task3Files");

    @Test
    void fileSizeTest() {
        AbstractFilter filter = Files::isRegularFile;
        filter = filter.and(Files::isReadable)
            .and(AbstractFilter.largerThen(10));

        List<Path> paths;

        try (var dirStream = Files.newDirectoryStream(DIR, filter)) {
            paths = new ArrayList<>();
            dirStream.forEach(paths::addLast);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertEquals(2, paths.size());
        assertTrue(paths.contains(FILES.get(0)));
        assertTrue(paths.contains(FILES.get(2)));
    }

    @Test
    void globTest() {
        AbstractFilter filter = Files::isRegularFile;
        filter = filter.and(AbstractFilter.globMatches("*.wav"));

        List<Path> paths;
        try (var dirStream = Files.newDirectoryStream(DIR, filter)) {
            paths = new ArrayList<>();
            dirStream.forEach(paths::addLast);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertEquals(1, paths.size());
        assertEquals(FILES.get(3), paths.get(0));
    }

    @Test
    void regexTest() {
        AbstractFilter filter = Files::isRegularFile;
        filter = filter.and(AbstractFilter.regexContains("test\\d"));

        List<Path> paths;
        try (var dirStream = Files.newDirectoryStream(DIR, filter)) {
            paths = new ArrayList<>();
            dirStream.forEach(paths::addLast);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertEquals(2, paths.size());
        assertTrue(paths.contains(FILES.get(0)));
        assertTrue(paths.contains(FILES.get(1)));
    }

    @Test
    void magicNumbersTest() {
        AbstractFilter filter = Files::isRegularFile;
        filter = filter.and(AbstractFilter.magicNumber((byte) 0x89, (byte) 0x50, (byte) 0x4e, (byte) 0x47));

        List<Path> paths;
        try (var dirStream = Files.newDirectoryStream(DIR, filter)) {
            paths = new ArrayList<>();
            for (var path : dirStream) {
                paths.addLast(path);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertEquals(1, paths.size());
        assertEquals("test.png", paths.get(0).getFileName().toString());

    }
}
