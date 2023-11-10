package org.yates.Roshambo;

public class Board {
    private static final int EMPTY = 0;
    private static final int PLAYER_X = 1;
    private static final int PLAYER_O = 2;

    private int[][] board;

    public Board() {
        // Initialize the board with empty cells
        board = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    public void displayBoard() {
        // Display the current state of the board
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                switch (board[i][j]) {
                    case EMPTY:
                        System.out.print(" - ");
                        break;
                    case PLAYER_X:
                        System.out.print(" X ");
                        break;
                    case PLAYER_O:
                        System.out.print(" O ");
                        break;
                }
                if (j < 2) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i < 2) {
                System.out.println("-----------");
            }
        }
        System.out.println();
    }

    public boolean isCellEmpty(int row, int col) {
        return board[row][col] == EMPTY;
    }

    public void move(int row, int col, int player) throws IllegalStateException {
        if (isCellEmpty(row, col)) {
            board[row][col] = player;
        } else {
            throw new IllegalStateException("Cell is not empty. Choose another move.");
        }
    }

    public enum GameState {
        ONGOING, X_WINS, O_WINS, TIE
    }

    public GameState checkGameState() {
        // Check rows, columns, and diagonals for a win
        for (int i = 0; i < 3; i++) {
            // Check rows
            if (board[i][0] == PLAYER_X && board[i][1] == PLAYER_X && board[i][2] == PLAYER_X) {
                return GameState.X_WINS;
            }
            if (board[i][0] == PLAYER_O && board[i][1] == PLAYER_O && board[i][2] == PLAYER_O) {
                return GameState.O_WINS;
            }

            // Check columns
            if (board[0][i] == PLAYER_X && board[1][i] == PLAYER_X && board[2][i] == PLAYER_X) {
                return GameState.X_WINS;
            }
            if (board[0][i] == PLAYER_O && board[1][i] == PLAYER_O && board[2][i] == PLAYER_O) {
                return GameState.O_WINS;
            }
        }

        // Check diagonals
        if (board[0][0] == PLAYER_X && board[1][1] == PLAYER_X && board[2][2] == PLAYER_X ||
                board[0][2] == PLAYER_X && board[1][1] == PLAYER_X && board[2][0] == PLAYER_X) {
            return GameState.X_WINS;
        }
        if (board[0][0] == PLAYER_O && board[1][1] == PLAYER_O && board[2][2] == PLAYER_O ||
                board[0][2] == PLAYER_O && board[1][1] == PLAYER_O && board[2][0] == PLAYER_O) {
            return GameState.O_WINS;
        }

        // Check for a tie
        boolean isBoardFull = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == EMPTY) {
                    isBoardFull = false;
                    break;
                }
            }
        }

        if (isBoardFull) {
            return GameState.TIE;
        }

        return GameState.ONGOING;
    }
}