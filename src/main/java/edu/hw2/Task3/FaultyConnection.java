package edu.hw2.Task3;

import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {
    private static final Random RANDOM = new Random();
    private static final Logger LOGGER = LogManager.getLogger();
    private static final double SUCCESS_PROBABILITY = 0.1;

    @Override
    public void execute(String command) {
        var number = RANDOM.nextDouble();
        if (number < SUCCESS_PROBABILITY) {
            LOGGER.info("connection established");
        } else {
            throw new ConnectionException();
        }
    }

    @Override
    public void close() throws Exception {
        LOGGER.info("connection closed");
    }
}
