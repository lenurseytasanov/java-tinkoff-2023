package edu.hw9.Task2;

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

public class FindDirsTask extends RecursiveTask<List<Path>> {

    private static final Logger LOGGER = LogManager.getLogger();

    private final Path currentNode;

    private final int filesCount;

    private static final int STD_FILES_COUNT = 1000;

    public FindDirsTask(@NotNull Path root) {
        this(root, STD_FILES_COUNT);
    }

    public FindDirsTask(@NotNull Path root, int filesCount) {
        this.currentNode = root;
        this.filesCount = filesCount;
    }

    @Override
    protected List<Path> compute() {
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(currentNode)) {
            List<Path> result = new ArrayList<>();
            int counter = 0;
            List<RecursiveTask<List<Path>>> tasks = new LinkedList<>();

            for (var path : directoryStream) {
                if (Files.isRegularFile(path)) {
                    counter++;
                } else {
                    tasks.addLast(new FindDirsTask(path, filesCount));
                }
            }
            if (counter > filesCount) {
                result.add(currentNode);
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
