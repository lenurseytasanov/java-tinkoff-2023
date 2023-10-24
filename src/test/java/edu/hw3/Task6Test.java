package edu.hw3;

import edu.hw3.Task6.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Task6Test {
    @Test
    void standardTest() {
        var market = new Market();
        var stock1 = new Stock(20);
        var stock2 = new Stock(30);
        var stock3 = new Stock(10);
        market.add(stock1);
        market.add(stock2);
        market.add(stock3);
        assertEquals(30, market.mostValuableStock().price());
        market.remove(stock2);
        assertEquals(20, market.mostValuableStock().price());
        market.remove(stock1);
        assertEquals(10, market.mostValuableStock().price());
        market.remove(stock3);
        assertNull(market.mostValuableStock());
    }
}
