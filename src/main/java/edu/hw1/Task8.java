package edu.hw1;

public final class Task8 {
    private final static int BOARD_SIZE = 8;

    private Task8() {
    }

    public static boolean knightBoardCapture(int[][] board) throws IllegalArgumentException {
        checkInput(board);
        for (var i = 0; i < board.length; i++) {
            for (var j = 0; j < board[0].length; j++) {
                if (isHits(board, i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void checkInput(int[][] board) throws IllegalArgumentException {
        if (board == null || board.length != BOARD_SIZE) {
            throw new IllegalArgumentException();
        }
        for (int[] row : board) {
            if (row == null || row.length != BOARD_SIZE) {
                throw new IllegalArgumentException();
            }
        }
    }

    private static boolean isHits(int[][] board, int i, int j) {
        return board[j][i] == 1
            && (isHitsUpperRight(board, i, j)
            || isHitsBottomRight(board, i, j)
            || isHitsBottomLeft(board, i, j)
            || isHitsUpperLeft(board, i, j));
    }

    private static boolean isHitsUpperRight(int[][] board, int i, int j) {
        return j < board[0].length - 2 && i < board.length - 1 && board[j + 2][i + 1] == 1
            || j < board[0].length - 1 && i < board.length - 2 && board[j + 1][i + 2] == 1;
    }

    private static boolean isHitsBottomRight(int[][] board, int i, int j) {
        return j < board[0].length - 1 && i >= 2 && board[j + 1][i - 2] == 1
            || j < board[0].length - 2 && i >= 1 && board[j + 2][i - 1] == 1;
    }

    private static boolean isHitsBottomLeft(int[][] board, int i, int j) {
        return j >= 1 && i >= 2 && board[j - 1][i - 2] == 1
            || j >= 2 && i >= 1 && board[j - 2][i - 1] == 1;
    }

    private static boolean isHitsUpperLeft(int[][] board, int i, int j) {
        return j >= 1 && i < board.length - 2 && board[j - 1][i + 2] == 1
            || j >= 2 && i < board.length - 1 && board[j - 2][i + 1] == 1;
    }
}
