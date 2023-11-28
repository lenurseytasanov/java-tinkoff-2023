package edu.hw2;

import edu.hw2.Task4.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Task4Test {
    @Test
    public void test1() {
        var info = Utility.callingInfo();
        assertEquals("Task4Test", info.className());
        assertEquals("test1", info.methodName());
    }

    @Test void test2() {
        var info = Utility.callingInfo();
        assertEquals(this.getClass().getSimpleName(), info.className());
        assertEquals("test2", info.methodName());
    }
}
