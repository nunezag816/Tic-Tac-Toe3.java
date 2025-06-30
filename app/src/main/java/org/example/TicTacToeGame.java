package org.example;

import game.io.InputProvider;
import game.io.OutputPrinter;

import java.util.Arrays;

public class TicTacToeGame {
    private final InputProvider input;
    private final OutputPrinter output;

    private final char[] board = new char[9];
    private char currentPlayer;
    private boolean gameRunning;

    public TicTacToeGame(InputProvider input, OutputPrinter output) {
        this.input = input;
        this.output = output;
        Arrays.fill(board, ' ');
        currentPlayer = 'X';
        gameRunning = true;
    }

    public void run() {
        output.print("Welcome to Tic-Tac-Toe!");

        while (gameRunning) {
            playRound();
            output.print("Would you like to play again (yes/no)?");
            String response = input.getInput().trim().toLowerCase();
            while (!response.equals("yes") && !response.equals("no")) {
                output.print("That is not a valid entry!");
                response = input.getInput().trim().toLowerCase();
            }
            if (response.equals("no")) {
                gameRunning = false;
                output.print("Goodbye!");
            } else {
                resetBoard();
                currentPlayer = 'X';
            }
        }
    }

    private void playRound() {
        boolean roundOver = false;
        while (!roundOver) {
            displayBoard();
            output.print("What is your move?");

            String userInput = input.getInput();
            int move;
            try {
                move = Integer.parseInt(userInput);
                if (move < 1 || move > 9 || board[move - 1] != ' ') {
                    output.print("That is not a valid move! Try again.");
                    continue;
                }
            } catch (NumberFormatException e) {
                output.print("That is not a valid move! Try again.");
                continue;
            }

            board[move - 1] = currentPlayer;

            if (checkWin()) {
                displayBoard();
                output.print("Player " + currentPlayer + " wins!");
                roundOver = true;
            } else if (isBoardFull()) {
                displayBoard();
                output.print("It's a tie!");
                roundOver = true;
            } else {
                switchPlayer();
            }
        }
    }

    private void displayBoard() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            char value = (board[i] == ' ') ? Character.forDigit(i + 1, 10) : board[i];
            builder.append("  ").append(value).append("  ");
            if ((i + 1) % 3 == 0 && i != 8) {
                builder.append("\n-----+-----+-----\n");
            } else if ((i + 1) % 3 != 0) {
                builder.append("|");
            }
        }
        output.print(builder.toString());
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    private boolean checkWin() {
        int[][] winPositions = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // rows
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // cols
            {0, 4, 8}, {2, 4, 6}             // diags
        };

        for (int[] pos : winPositions) {
            if (board[pos[0]] == currentPlayer &&
                board[pos[1]] == currentPlayer &&
                board[pos[2]] == currentPlayer) {
                return true;
            }
        }
        return false;
    }

    private boolean isBoardFull() {
        for (char c : board) {
            if (c == ' ') return false;
        }
        return true;
    }

    private void resetBoard() {
        Arrays.fill(board, ' ');
    }
}
    private boolean askReplay() {
        System.out.print("Would you like to play again (yes/no)? ");
        String input = scanner.next().toLowerCase();
        return input.startsWith("y");
    }
}
