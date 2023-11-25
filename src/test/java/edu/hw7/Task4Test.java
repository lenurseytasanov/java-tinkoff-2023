package edu.hw7;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task4Test {
    private final static Logger LOGGER = LogManager.getLogger();
    @Test
    void test() {
        assertEquals(Math.PI, Task4.getPiMonteCarlo((long) 1e8), 1e-3);
        assertEquals(Math.PI, Task4.getPiMonteCarloParallel((long) 1e8, 2), 1e-3);

        assertEquals(-1, Task4.getPiMonteCarloParallel((long) -1e8, -1));
    }
}
