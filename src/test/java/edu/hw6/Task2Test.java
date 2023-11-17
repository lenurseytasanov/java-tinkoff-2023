package edu.hw6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static edu.hw6.Task2.Task2.cloneFile;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task2Test {
    private final static Path FILE_PATH = Paths.get("src\\test\\java\\edu\\hw6\\task2.txt");
    private final static List<String> DATA = List.of("123", "456", "789");

    @BeforeAll
    static void setUpFile() {
        try {
            if (!Files.exists(FILE_PATH)) {
                Files.createFile(FILE_PATH);
            }

            Files.write(FILE_PATH, DATA);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Test
    void cloneTest() {
        try {
            cloneFile(FILE_PATH);
            var pathCopy1 = Paths.get( "src\\test\\java\\edu\\hw6\\task2 — копия.txt");
            assertTrue(Files.exists(pathCopy1));

            cloneFile(FILE_PATH);
            assertTrue(Files.exists(Paths.get("src\\test\\java\\edu\\hw6\\task2 — копия (2).txt")));

            Files.delete(pathCopy1);

            cloneFile(FILE_PATH);
            assertTrue(Files.exists(pathCopy1));

            try (var data = Files.lines(pathCopy1)) {
                assertEquals(DATA, data.toList());
            }
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @AfterAll
    static void deleteTempFiles() {
        try (var files = Files.list(FILE_PATH.getParent())) {
            files.filter(file -> file.getFileName().toString().contains("task2"))
                .forEach(file -> {
                    try {
                        Files.delete(file);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
