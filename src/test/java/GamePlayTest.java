import api.AIEngine;
import api.GameEngine;
import api.RuleEngine;
import game.Board;
import game.Cell;
import game.Move;
import game.Player;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class GamePlayTest {
    GameEngine gameEngine;
    AIEngine aiEngine;
    RuleEngine ruleEngine;

    @BeforeEach
    void setup() {
        gameEngine = new GameEngine();
        aiEngine = new AIEngine();
        ruleEngine = new RuleEngine();
    }

    private void playGame(Board board, int[][] moves) {
        int row, col;
        int next = 0;
        while(!ruleEngine.getState(board).isOver()) {

            row = moves[next][0];
            col = moves[next][1];
            next++;

            Player human = new Player("X");
            Move humanMove = new Move(new Cell(row, col), human);
            gameEngine.move(board, humanMove);

            Player computer = new Player("O");
            if(!ruleEngine.getState(board).isOver()) {
                Move computerMove = aiEngine.suggestMove(computer, board);
                gameEngine.move(board, computerMove);
            }

        }
    }

    @Test
    public void checkForRowWin() {

        Board board = gameEngine.start("TicTacToe");

        //make moves in a loop
        int row, col;
        int[][] moves = new int[][]{{1,0}, {1, 1}, {1, 2}};
        playGame(board, moves);

        assertTrue(ruleEngine.getState(board).isOver());
        assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkForColWin() {

        Board board = gameEngine.start("TicTacToe");

        //make moves in a loop
        int row, col;
        int[][] moves = new int[][]{{0,0}, {1, 0}, {2, 0}};
        playGame(board, moves);

        assertTrue(ruleEngine.getState(board).isOver());
        assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkForDiagWin() {

        Board board = gameEngine.start("TicTacToe");

        //make moves in a loop
        int row, col;
        int[][] moves = new int[][]{{0,0}, {1, 1}, {2, 2}};
        playGame(board, moves);

        assertTrue(ruleEngine.getState(board).isOver());
        assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkForRevDiagWin() {

        Board board = gameEngine.start("TicTacToe");

        //make moves in a loop
        int row, col;
        int[][] moves = new int[][]{{0,2}, {1, 1}, {2, 0}};
        playGame(board, moves);

        assertTrue(ruleEngine.getState(board).isOver());
        assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkForComputerWin() {

        Board board = gameEngine.start("TicTacToe");

        //make moves in a loop
        int row, col;
        int[][] moves = new int[][]{{1, 0}, {1, 1}, {2, 0}};
        playGame(board, moves);

        assertTrue(ruleEngine.getState(board).isOver());
        assertEquals(ruleEngine.getState(board).getWinner(), "O");
    }

}
