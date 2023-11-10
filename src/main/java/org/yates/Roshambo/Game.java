package org.yates.Roshambo;

import java.util.Scanner;

public class Game {
    private static final int PLAYER_X = 1;
    private static final int PLAYER_O = 2;

    private final Board board;
    private int currentPlayer;

    public Game() {
        board = new Board();
        currentPlayer = PLAYER_X; // Assume Player X starts
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);

        while (!isGameFinished()) {
            board.displayBoard();
            int row = getMoveInput("Enter the row: ", scanner);
            int col = getMoveInput("Enter the column: ", scanner);

            try {
                board.move(row, col, currentPlayer);
                switchPlayer();
            } catch (IllegalStateException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        board.displayBoard();
        System.out.println("Game Over!");
    }

    private int getMoveInput(String prompt, Scanner scanner) {
        int move;
        while (true) {
            System.out.print(prompt);
            try {
                move = Integer.parseInt(scanner.nextLine());
                if (move >= 0 && move <= 2) {
                    return move;
                } else {
                    System.out.println("Please enter a number between 0 and 2.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
    }

    private boolean isGameFinished() {
        switch (board.checkGameState()) {
            case ONGOING -> {
                return false;
            }
            case X_WINS -> {
                System.out.println("X has won the game!");
                return true;
            }
            case O_WINS -> {
                System.out.println("O has won the game!");
                return true;
            }
            case TIE -> {
                System.out.println("It's a tie!!!!");
                return true;
            }
            default -> throw new RuntimeException();
        }
    }
}
