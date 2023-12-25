package edu.hw8.Task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class Server {

    private Server() { }

    private static final QuotesList QUOTES = new QuotesList();

    private static final int PORT = 18080;

    private static final int MAX_THREADS = 8;

    public static void start(int connectionCount) {
        try (ServerSocket server = new ServerSocket(PORT)) {
            ExecutorService executors = Executors.newFixedThreadPool(MAX_THREADS);
            for (var i = 0; i < connectionCount; i++) {
                Socket client = server.accept();
                executors.execute(() -> {
                    try (
                        BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                        PrintWriter writer = new PrintWriter(client.getOutputStream());
                    ) {
                        var word = reader.readLine();
                        writer.println(QUOTES.getQuote(word).orElseThrow());
                        writer.flush();

                        client.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }

            executors.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
