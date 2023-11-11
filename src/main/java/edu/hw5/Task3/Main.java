package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;

public final class Main {
    private Main() { }

    public static Optional<LocalDate> parseDate(String string) {
        var parsersChain = new StandardParser(new SpecialParser(null));
        return parsersChain.tryParse(string);
    }
}
