package edu.hw9;

import edu.hw9.Task2.FindDirsTask;
import edu.hw9.Task2.FindFilesTask;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test {

    private static final Path ROOT = Paths.get("src/test/java/edu/hw9/task2");

    @Test
    void findDirsTest1() {
        try (var pool = new ForkJoinPool()) {
            // Arrange
            var expected = List.of(
                ROOT,
                Paths.get(ROOT.toString(), "/dir1/dir3")
            );
            var task = new FindDirsTask(ROOT, 1);

            // Act
            var actual = pool.invoke(task);

            // Assert
            assertEquals(expected, actual);
        }
    }

    @Test
    void findDirsTest2() {
        try (var pool = new ForkJoinPool()) {
            // Arrange
            var task = new FindDirsTask(ROOT);

            // Act
            var actual = pool.invoke(task);

            // Assert
            assertEquals(List.of(), actual);
        }
    }

    @Test
    void findFilesTest1() {
        try (var pool = new ForkJoinPool()) {
            // Arrange
            var task = new FindFilesTask(ROOT, ".png", 0);
            var expected = List.of(Paths.get("src/test/java/edu/hw9/task2/dir1/file3.png").toFile());

            // Act
            var actual = pool.invoke(task);

            // Assert
            assertEquals(expected, actual);
        }
    }

    @Test
    void findFilesTest2() {
        try (var pool = new ForkJoinPool()) {
            // Arrange
            var task = new FindFilesTask(ROOT, ".txt", 10);
            var expected = List.of(Paths.get("src/test/java/edu/hw9/task2/dir2/file4.txt").toFile());

            // Act
            var actual = pool.invoke(task);

            // Assert
            assertEquals(expected, actual);
        }
    }
}
