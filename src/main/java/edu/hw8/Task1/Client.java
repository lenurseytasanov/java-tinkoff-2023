package edu.hw8.Task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public final class Client {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final int PORT = 18080;

    private static final String HOST = "localhost";

    private Client() { }

    public static String start(@NotNull String word) {
        try (
            Socket client = new Socket(HOST, PORT);
            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter writer = new PrintWriter(client.getOutputStream());
        ) {
            writer.println(word);
            writer.flush();

            return reader.readLine();
        } catch (IOException e) {
            LOGGER.error("error", e);
            throw new RuntimeException(e);
        }
    }
}
