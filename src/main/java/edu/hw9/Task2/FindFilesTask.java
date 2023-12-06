package edu.hw9.Task2;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public class FindFilesTask extends RecursiveTask<List<File>> {

    private static final Logger LOGGER = LogManager.getLogger();

    private final Path currentDir;

    private final long size;

    private final String extension;

    public FindFilesTask(@NotNull Path root, @NotNull String extension, long bytes) {
        this.currentDir = root;
        this.size = bytes;
        this.extension = extension;
    }

    @Override
    protected List<File> compute() {
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(currentDir)) {
            List<File> result = new ArrayList<>();
            List<RecursiveTask<List<File>>> tasks = new LinkedList<>();

            for (var path : directoryStream) {
                if (Files.isDirectory(path)) {
                    tasks.addLast(new FindFilesTask(path, extension, size));
                } else if (Files.isRegularFile(path)
                    && path.getFileName().toString().endsWith(extension)
                    && Files.size(path) >= size) {
                    result.addLast(path.toFile());
                }
            }

            tasks.forEach(ForkJoinTask::fork);
            var taskResults = tasks.stream()
                .map(ForkJoinTask::join)
                .flatMap(Collection::stream)
                .toList();

            result.addAll(taskResults);

            return result;
        } catch (IOException e) {
            LOGGER.error("error", e);
            throw new RuntimeException(e);
        }
    }
}
