package org.example;

import game.GameBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {

    private GameBoard board;

    @BeforeEach
    void setUp() {
        board = new GameBoard();
    }

    @Test
    void testInitialBoardIsEmpty() {
        for (int i = 1; i <= 9; i++) {
            assertTrue(board.isValidMove(i));
        }
    }

    @Test
    void testMakeMoveAndCheckValidity() {
        assertTrue(board.makeMove(1, 'X'));
        assertFalse(board.isValidMove(1));
    }

    @Test
    void testInvalidMoveOutOfBounds() {
        assertFalse(board.makeMove(0, 'X'));
        assertFalse(board.makeMove(10, 'O'));
    }

    @Test
    void testWinnerHorizontal() {
        board.makeMove(1, 'X');
        board.makeMove(2, 'X');
        board.makeMove(3, 'X');
        assertEquals('X', board.checkWinner());
    }

    @Test
    void testWinnerVertical() {
        board.makeMove(1, 'O');
        board.makeMove(4, 'O');
        board.makeMove(7, 'O');
        assertEquals('O', board.checkWinner());
    }

    @Test
    void testWinnerDiagonal() {
        board.makeMove(1, 'X');
        board.makeMove(5, 'X');
        board.makeMove(9, 'X');
        assertEquals('X', board.checkWinner());
    }

    @Test
    void testNoWinnerDraw() {
        board.makeMove(1, 'X');
        board.makeMove(2, 'O');
        board.makeMove(3, 'X');
        board.makeMove(4, 'X');
        board.makeMove(5, 'O');
        board.makeMove(6, 'X');
        board.makeMove(7, 'O');
