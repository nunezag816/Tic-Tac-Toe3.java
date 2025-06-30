package org.example;

import game.TicTacToeGame;
import game.io.InputProvider;
import game.io.OutputPrinter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicTacToeGameTest {

    @Test
    void testHumanVsHumanGame_PlayerXWins() {
        MockInputProvider input = new MockInputProvider();
        MockOutputPrinter output = new MockOutputPrinter();

        // Simulate:
        // Choose Human vs Human
        // X moves: 1, 2, 3
        // O moves: 4, 5
        // Replay: no
        input.addInput("1");     // mode: Human vs Human
        input.addInput("1");     // Player X move
        input.addInput("4");     // Player O move
        input.addInput("2");     // Player X move
        input.addInput("5");     // Player O move
        input.addInput("3");     // Player X move -> wins
        input.addInput("no");    // don't replay

        TicTacToeGame game = new TicTacToeGame(input, output);
        game.run();

        // Verify expected output
        assertTrue(output.containsOutput("Player X wins!"));
        assertTrue(output.containsOutput("Would you like to play again"));
    }
}
