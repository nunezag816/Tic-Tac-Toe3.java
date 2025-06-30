package org.example;

import java.util.*;

public class GameBoard {
    private final char[] board = new char[9];

    public GameBoard() {
        reset();
    }

    public void reset() {
        Arrays.fill(board, ' ');
    }

    public boolean isValidMove(int pos) {
        return pos >= 1 && pos <= 9 && board[pos - 1] == ' ';
    }

    public void makeMove(int pos, char symbol) {
        board[pos - 1] = symbol;
    }

    public boolean isFull() {
        for (char c : board) {
            if (c == ' ') return false;
        }
        return true;
    }

    public List<Integer> getOpenPositions() {
        List<Integer> open = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            if (board[i] == ' ') open.add(i + 1);
        }
        return open;
    }

    public char checkWinner() {
        int[][] winPatterns = {
                {0,1,2}, {3,4,5}, {6,7,8},
                {0,3,6}, {1,4,7}, {2,5,8},
                {0,4,8}, {2,4,6}
        };
        for (int[] pattern : winPatterns) {
            if (board[pattern[0]] != ' ' &&
                board[pattern[0]] == board[pattern[1]] &&
                board[pattern[1]] == board[pattern[2]]) {
                return board[pattern[0]];
            }
        }
        return '-';
    }

    public void display() {
        System.out.println();
        for (int i = 0; i < 9; i += 3) {
            System.out.printf("  %s  |  %s  |  %s\n", mark(i), mark(i + 1), mark(i + 2));
            if (i < 6) System.out.println("-----+-----+-----");
        }
    }

    private String mark(int i) {
        return board[i] == ' ' ? Integer.toString(i + 1) : String.valueOf(board[i]);
    }

    public char[] getBoardState() {
        return board.clone();
    }
}
