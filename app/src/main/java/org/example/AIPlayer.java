package org.example;

import java.util.*;

public class AIPlayer {
    private final char symbol;
    private final char opponentSymbol;

    public AIPlayer(char symbol, char opponentSymbol) {
        this.symbol = symbol;
        this.opponentSymbol = opponentSymbol;
    }

    public int chooseMove(GameBoard board) {
        List<Integer> open = board.getOpenPositions();

        if (open.size() == 9) {
            return pickRandom(Arrays.asList(1, 3, 7, 9));
        }

        if (open.size() == 8 && board.isValidMove(5)) {
            return 5;
        }

        for (int pos : open) {
            GameBoard copy = simulate(board, pos, symbol);
            if (copy.checkWinner() == symbol) return pos;
        }

        for (int pos : open) {
            GameBoard copy = simulate(board, pos, opponentSymbol);
            if (copy.checkWinner() == opponentSymbol) return pos;
        }

        return pickRandom(open);
    }

    private GameBoard simulate(GameBoard board, int move, char sym) {
        GameBoard copy = new GameBoard();
        char[] state = board.getBoardState();
        for (int i = 0; i < 9; i++) copy.makeMove(i + 1, state[i]);
        copy.makeMove(move, sym);
        return copy;
    }

    private int pickRandom(List<Integer> choices) {
        List<Integer> copy = new ArrayList<>(choices);
        Collections.shuffle(copy);
        return copy.get(0);
    }
}
