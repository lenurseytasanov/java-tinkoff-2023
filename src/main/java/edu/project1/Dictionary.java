package edu.project1;

import java.util.Random;

public class Dictionary {
    private final String[] words;
    private final static Random RANDOM = new Random();

    public Dictionary(String[] words) {
        for (var word : words) {
            if (word.isEmpty()) {
                throw new IllegalArgumentException("'%s' - incorrect word".formatted(word));
            }
        }
        this.words = words;
    }

    public Dictionary() {
        this.words = new String[] {"hello", "java", "hangman"};
    }

    public String getRandomWord() {
        return words[RANDOM.nextInt(words.length)];
    }
}
