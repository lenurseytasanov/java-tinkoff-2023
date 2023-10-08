package edu.hw1;

public final class Task8 {
    private final static int BOARD_SIZE = 8;
    private final static int FRAME_SIZE = 2;

    private Task8() {
    }

    public static boolean knightBoardCapture(int[][] board) throws IllegalArgumentException {
        checkInput(board);
        var extendedBoard = extendBoard(board);
        for (var i = 2; i < extendedBoard.length - 2; i++) {
            for (var j = 2; j < extendedBoard[0].length - 2; j++) {
                if (extendedBoard[j][i] == 1
                    && (extendedBoard[j - 1][i - 2] == 1 || extendedBoard[j + 1][i - 2] == 1
                    || extendedBoard[j + 2][i - 1] == 1 || extendedBoard[j + 2][i + 1] == 1
                    || extendedBoard[j - 1][i + 2] == 1 || extendedBoard[j + 1][i + 2] == 1
                    || extendedBoard[j - 2][i + 1] == 1 || extendedBoard[j - 2][i - 1] == 1)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static int[][] extendBoard(int[][] board) {
        var extendedBoard = new int[BOARD_SIZE + 2 * FRAME_SIZE][BOARD_SIZE + 2 * FRAME_SIZE];
        for (var i = 0; i < board.length; i++) {
            for (var j = 0; j < board[0].length; j++) {
                extendedBoard[j + 2][i + 2] = board[j][i];
            }
        }
        return extendedBoard;
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
}
