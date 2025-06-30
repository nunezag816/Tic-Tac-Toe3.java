package org.example;

import game.AIPlayer;
import game.GameBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AIPlayerTest {

    private AIPlayer ai;
    private GameBoard board;

    @BeforeEach
    void setUp() {
        ai = new AIPlayer('O', 'X');
        board = new GameBoard();
    }

    @Test
    void testChooseCornerFirstMove() {
        int move = ai.chooseMove(board);
        assertTrue(move == 1 || move == 3 || move == 7 || move == 9);
    }

    @Test
    void testChooseCenterSecondMove() {
        board.makeMove(1, 'X');
        int move = ai.chooseMove(board);
        assertEquals(5, move);
    }

    @Test
    void testTakeWinningMove() {
        board.makeMove(1, 'O');
        board.makeMove(2, 'O');
        board.makeMove(4, 'X');
        int move = ai.chooseMove(board);
        assertEquals(3, move);
    }

    @Test
    void testBlockOpponentWinningMove() {
        board.makeMove(1, 'X');
        board.makeMove(2, 'X');
        int move = ai.chooseMove(board);
        assertEquals(3, move);
    }

    @Test
    void testChooseRandomIfNoOtherOption() {
        // Fill board except for one move
        for (int i = 1; i <= 9; i++) {
            board.makeMove(i, (i == 5 ? '-' : 'X'));
        }
        int move = ai.chooseMove(board);
        assertEquals(5, move);
    }
}
