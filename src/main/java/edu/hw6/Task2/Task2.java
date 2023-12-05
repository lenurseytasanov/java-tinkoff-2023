package edu.hw6.Task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public final class Task2 {

    private static final Logger LOGGER = LogManager.getLogger();

    private Task2() { }

    public static void cloneFile(@NotNull Path path) {
        if (Files.exists(path)) {
            var newPath = path.toString().replace(".", " — копия.");
            try {
                for (var i = 2; Files.exists(Paths.get(newPath)); i++) {
                    newPath = newPath.replaceFirst("( \\(.\\))?\\.", " (%d).".formatted(i));
                }
                Files.copy(path, Paths.get(newPath), StandardCopyOption.COPY_ATTRIBUTES);
            } catch (IOException exception) {
                LOGGER.error("error: ", exception);
                throw new RuntimeException(exception);
            }
        }
    }
}
