package edu.hw6.Task4;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import org.jetbrains.annotations.NotNull;

public final class Task4 {
    private Task4() { }

    public static void writeFile(@NotNull Path path) {
        try (OutputStream os = Files.newOutputStream(path);
             CheckedOutputStream cos = new CheckedOutputStream(os, new CRC32());
             BufferedOutputStream bos = new BufferedOutputStream(cos);
             OutputStreamWriter osw = new OutputStreamWriter(bos, StandardCharsets.UTF_8);
             PrintWriter printWriter = new PrintWriter(osw)) {

            printWriter.write("Programming is learned by writing programs. ― Brian Kernighan");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
