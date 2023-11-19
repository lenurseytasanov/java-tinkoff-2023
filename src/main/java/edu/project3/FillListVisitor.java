package edu.project3;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class FillListVisitor extends SimpleFileVisitor<Path> {
    private final PathMatcher matcher;
    private final List<Path> files;

    public FillListVisitor(PathMatcher matcher, List<Path> files) {
        this.matcher = matcher;
        this.files = files;
    }

    @Override
    public FileVisitResult visitFile(Path path,
        BasicFileAttributes attrs) throws IOException {
        if (matcher.matches(path)) {
            files.add(path);
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc)
        throws IOException {
        return FileVisitResult.CONTINUE;
    }
}
