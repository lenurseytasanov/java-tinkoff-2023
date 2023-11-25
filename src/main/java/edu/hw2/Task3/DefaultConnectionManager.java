package edu.hw2.Task3;

import java.util.Random;

public class DefaultConnectionManager implements ConnectionManager {
    private static final Random RANDOM = new Random();
    private static final double STD_STABLE_CONNECTION_PROBABILITY = 0.5;
    private final double stableConnectionProbability;

    public DefaultConnectionManager(double stableConnectionProbability) {
        this.stableConnectionProbability = stableConnectionProbability;
    }

    public DefaultConnectionManager() {
        this(STD_STABLE_CONNECTION_PROBABILITY);
    }

    @Override
    public Connection getConnection() {
        var number = RANDOM.nextDouble();
        if (number < stableConnectionProbability) {
            return new StableConnection();
        } else {
            return new FaultyConnection();
        }
    }
}
