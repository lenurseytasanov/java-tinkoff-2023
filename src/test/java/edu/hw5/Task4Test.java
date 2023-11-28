package edu.hw5;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task4Test {
    @Test
    void mainTest() {
        List<String> passwords = new ArrayList<>();
        for (var ch : "~!@#$%^&*|".toCharArray()) {
            passwords.addLast(String.format("abc%c123", ch));
        }

        passwords.forEach(password -> assertTrue(Task4.validatePassword(password)));
    }
}
