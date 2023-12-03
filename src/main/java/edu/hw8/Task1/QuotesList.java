package edu.hw8.Task1;

import java.util.List;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public class QuotesList {

    private final List<String> quotes = List.of(
        "Не переходи на личности там, где их нет",
        "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами",
        "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.",
        "Чем ниже интеллект, тем громче оскорбления"
    );

    public Optional<String> getQuote(@NotNull String word) {
        return quotes.stream().filter(quote -> quote.toLowerCase().contains(word.toLowerCase())).findFirst();
    }
}
