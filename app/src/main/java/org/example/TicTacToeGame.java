package org.example;

package game;

import java.util.Scanner;

public class TicTacToeGame {
    private final Scanner scanner = new Scanner(System.in);
    private final GameBoard board = new GameBoard();
    private PlayerType p1, p2;
    private AIPlayer aiPlayer;
    private char[] symbols = {'X', 'O'};

    public void run() {
        System.out.println("Welcome to Tic-Tac-Toe!\n");
        selectMode();

        boolean playAgain = true;
        while (playAgain) {
            board.reset();
            int turn = 0;
            char winner = '-';

            while (!board.isFull() && winner == '-') {
                board.display();
                System.out.print("\n");

                char currentSymbol = symbols[turn % 2];
                PlayerType currentPlayer = turn % 2 == 0 ? p1 : p2;

                if (currentPlayer == PlayerType.HUMAN) {
                    int move = getHumanMove(currentSymbol);
                    board.makeMove(move, currentSymbol);
                } else {
                    int move = aiPlayer.chooseMove(board);
                    System.out.println("Computer chooses position: " + move);
                    board.makeMove(move, currentSymbol);
                }

                winner = board.checkWinner();
                turn++;
            }

            board.display();
            System.out.println();

            if (winner == 'X' || winner == 'O') {
                System.out.println("Player " + winner + " wins!");
            } else {
                System.out.println("It's a draw!");
            }

            playAgain = askReplay();
        }

        System.out.println("Goodbye!");
    }

    private void selectMode() {
        System.out.println("Choose game mode:");
        System.out.println("1. Human vs Human");
        System.out.println("2. Human vs Computer");
        System.out.println("3. Computer vs Human");

        int selection = 0;
        while (selection < 1 || selection > 3) {
            System.out.print("Enter 1, 2, or 3: ");
            if (scanner.hasNextInt()) {
                selection = scanner.nextInt();
            } else {
                scanner.next(); // clear invalid input
            }
        }

        if (selection == 1) {
            p1 = PlayerType.HUMAN;
            p2 = PlayerType.HUMAN;
        } else if (selection == 2) {
            p1 = PlayerType.HUMAN;
            p2 = PlayerType.COMPUTER;
            aiPlayer = new AIPlayer('O', 'X');
        } else {
            p1 = PlayerType.COMPUTER;
            p2 = PlayerType.HUMAN;
            aiPlayer = new AIPlayer('X', 'O');
        }
    }

    private int getHumanMove(char symbol) {
        int move = 0;
        while (!board.isValidMove(move)) {
            System.out.print("Player " + symbol + ", enter your move (1–9): ");
            if (scanner.hasNextInt()) {
                move = scanner.nextInt();
            } else {
                System.out.println("Invalid input!");
                scanner.next();
            }
        }
        return move;
    }

    private boolean askReplay() {
        System.out.print("Would you like to play again (yes/no)? ");
        String input = scanner.next().toLowerCase();
        return input.startsWith("y");
    }
}
