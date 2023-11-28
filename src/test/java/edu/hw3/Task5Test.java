package edu.hw3;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class Task5Test {
    @Test
    void ascTest() {
        assertEquals(
            Arrays.toString(new Task5.Contact[] {
                new Task5.Contact("Thomas", "Aquinas"),
                new Task5.Contact("David", "Hume"),
                new Task5.Contact("John", "Locke")
            }), Arrays.toString(Task5.parseContacts(new String[] {
                "John Locke",
                "Thomas Aquinas",
                "David Hume"
            }, Task5.Order.ASK)));
    }

    @Test
    void descTest() {
        assertEquals(
            Arrays.toString(new Task5.Contact[] {
                new Task5.Contact("John", "Locke"),
                new Task5.Contact("David", "Hume"),
                new Task5.Contact("Thomas", "Aquinas")
            }), Arrays.toString(Task5.parseContacts(new String[] {
                "John Locke",
                "Thomas Aquinas",
                "David Hume"
            }, Task5.Order.DESK)));
    }

    @Test
    void surnameNullTest() {
        assertEquals(
            Arrays.toString(new Task5.Contact[] {
                new Task5.Contact("Aaron", null),
                new Task5.Contact("Thomas", "Aquinas"),
                new Task5.Contact("David", "Hume"),
                new Task5.Contact("John", "Locke")
            }), Arrays.toString(Task5.parseContacts(new String[] {
                "John Locke",
                "Thomas Aquinas",
                "David Hume",
                "Aaron"
            }, Task5.Order.ASK)));
    }

    @Test
    void emptyInputTest() {
        assertEquals(
            Arrays.toString(new Task5.Contact[0]),
            Arrays.toString(Task5.parseContacts(null, Task5.Order.ASK)));
        assertEquals(
            Arrays.toString(new Task5.Contact[0]),
            Arrays.toString(Task5.parseContacts(new String[0], Task5.Order.ASK)));
    }
}
