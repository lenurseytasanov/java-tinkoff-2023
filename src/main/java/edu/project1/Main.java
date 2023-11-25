package edu.project1;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        var game = new ConsoleHangman(System.in, new Dictionary());
        game.run();
    }
}
