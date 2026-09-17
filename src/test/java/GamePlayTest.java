import api.GameEngine;
import api.RuleEngine;
import game.Board;
import game.Cell;
import game.Move;
import game.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GamePlayTest {
    GameEngine gameEngine;
    RuleEngine ruleEngine;

    @BeforeEach
    void setup() {
        gameEngine = new GameEngine();
        ruleEngine = new RuleEngine();
    }

    private void playGame(Board board, int[][] firstPlayerMoves, int[][] secondPlayerMoves) {
        int row, col;
        int next = 0;
        while(!ruleEngine.getState(board).isOver()) {

            row = firstPlayerMoves[next][0];
            col = firstPlayerMoves[next][1];

            Player human = new Player("X");
            Move humanMove = new Move(new Cell(row, col), human);
            gameEngine.move(board, humanMove);

            Player computer = new Player("O");
            if(!ruleEngine.getState(board).isOver()) {
                int sRow = secondPlayerMoves[next][0];
                int sCol = secondPlayerMoves[next][1];
                Move computerMove = new Move(new Cell(sRow, sCol), computer);
                gameEngine.move(board, computerMove);
            }

            next++;

        }
    }

    @Test
    public void checkForRowWin() {

        Board board = gameEngine.start("TicTacToe");

        int[][] firstPlayerMoves = new int[][]{{1,0}, {1, 1}, {1, 2}};
        int[][] secondPlayerMoves = new int[][]{{0,0}, {0, 1}, {0, 2}};
        playGame(board, firstPlayerMoves, secondPlayerMoves);

        assertTrue(ruleEngine.getState(board).isOver());
        assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkForColWin() {

        Board board = gameEngine.start("TicTacToe");


        int row, col;
        int[][] firstPlayerMoves = new int[][]{{0,0}, {1, 0}, {2, 0}};
        int[][] secondPlayerMoves = new int[][]{{0, 1}, {0, 2}, {1, 1}};
        playGame(board, firstPlayerMoves, secondPlayerMoves);

        assertTrue(ruleEngine.getState(board).isOver());
        assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkForDiagWin() {

        Board board = gameEngine.start("TicTacToe");


        int row, col;
        int[][] firstPlayerMoves = new int[][]{{0,0}, {1, 1}, {2, 2}};
        int[][] secondPlayerMoves = new int[][]{{1,0}, {2, 1}, {1, 2}};
        playGame(board, firstPlayerMoves, secondPlayerMoves);

        assertTrue(ruleEngine.getState(board).isOver());
        assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkForRevDiagWin() {

        Board board = gameEngine.start("TicTacToe");


        int row, col;
        int[][] firstPlayerMoves = new int[][]{{0,2}, {1, 1}, {2, 0}};
        int[][] secondPlayerMoves = new int[][]{{1,0}, {0, 1}, {1, 2}};
        playGame(board, firstPlayerMoves, secondPlayerMoves);

        assertTrue(ruleEngine.getState(board).isOver());
        assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkForComputerWin() {

        Board board = gameEngine.start("TicTacToe");


        int row, col;
        int[][] firstPlayerMoves = new int[][]{{1, 0}, {1, 1}, {2, 0}};
        int[][] secondPlayerMoves = new int[][]{{0,0}, {0, 1}, {0, 2}};
        playGame(board, firstPlayerMoves, secondPlayerMoves);

        assertTrue(ruleEngine.getState(board).isOver());
        assertEquals(ruleEngine.getState(board).getWinner(), "O");
    }

}
