package edu.hw6.Task6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class PortScanner {
    private final static Logger LOGGER = LogManager.getLogger();
    private final static String FORMAT = "%-9s %-6s %s";
    private final static Path SERVICES_PATH = Paths.get("src/main/java/edu/hw6/Task6/services.txt");
    private final static int MAX_PORT_NUMBER = 49151;

    private PortScanner() { }

    private static Map<Integer, String> getServices() {
        try (var reader = new BufferedReader(new InputStreamReader(Files.newInputStream(SERVICES_PATH)))) {
            return reader.lines()
                .map(string -> string.split(","))
                .collect(Collectors.toMap(entry -> Integer.parseInt(entry[0]), entry -> entry[1]));
        } catch (IOException e) {
            LOGGER.error("error: ", e);
            throw new RuntimeException(e);
        }
    }

    public static void printStats() {
        Map<Integer, String> map = getServices();

        LOGGER.info(String.format(FORMAT, "Протокол", "Порт", "Сервис"));
        for (var port = 1; port <= MAX_PORT_NUMBER; port++) {
            try (var ignored = new ServerSocket(port)) {
                ignored.getLocalPort();
            } catch (IOException e) {
                LOGGER.info(String.format(FORMAT, "TCP", port, map.getOrDefault(port, "")));
            }
            try (var ignored = new DatagramSocket(port)) {
                ignored.getLocalPort();
            } catch (IOException e) {
                LOGGER.info(String.format(FORMAT, "UDP", port, map.getOrDefault(port, "")));
            }
        }
    }
}
