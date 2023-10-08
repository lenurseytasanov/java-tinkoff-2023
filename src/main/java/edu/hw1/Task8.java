package edu.hw1;

public final class Task8 {
    private final static int BOARD_SIZE = 8;
    private final static int FRAME_SIZE = 2;

    private Task8() {
    }

    public static boolean knightBoardCapture(int[][] board) {
        var extendedBoard = new int[BOARD_SIZE + 2 * FRAME_SIZE][BOARD_SIZE + 2 * FRAME_SIZE];
        for (var i = 0; i < board.length; i++) {
            for (var j = 0; j < board[0].length; j++) {
                extendedBoard[j + 2][i + 2] = board[j][i];
            }
        }
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
}
