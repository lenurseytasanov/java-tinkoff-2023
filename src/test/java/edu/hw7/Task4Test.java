package edu.hw7;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task4Test {

    @Test
    void test() {
        // Act
        double actual1 = Task4.getPiMonteCarlo((long) 1e8);
        double actual2 = Task4.getPiMonteCarloParallel((long) 1e8, 2);
        double actual3 = Task4.getPiMonteCarloParallel((long) -1e8, -1);

        // Assert
        assertEquals(Math.PI, actual1, 1e-3);
        assertEquals(Math.PI, actual2, 1e-3);
        assertEquals(-1, actual3);
    }
}
