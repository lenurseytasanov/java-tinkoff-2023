package edu.hw3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public final class Task4 {
    private Task4() { }

    private final static int MAX_ROMAN_NUMBER = 1000;
    private final static int RADIX = 10;
    private final static HashMap<Integer, String> CONVERT_MAP;

    static {
        CONVERT_MAP = new HashMap<>();
        CONVERT_MAP.put(0, "");
        try (Scanner scanner = new Scanner(new File("src/main/java/edu/hw3/nums.txt"))) {
            scanner.useDelimiter("\n");
            while (scanner.hasNext()) {
                var pair = scanner.next().split(" ");
                CONVERT_MAP.put(Integer.parseInt(pair[0]), pair[1].strip());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static String convertToRoman(int number) {
        var sb = new StringBuilder();
        var remainder = number;
        var mod = MAX_ROMAN_NUMBER;
        while (mod > 0) {
            sb.append(CONVERT_MAP.get(remainder / mod * mod));
            remainder %= mod;
            mod /= RADIX;
        }
        return sb.toString();
    }

}
