import api.GameEngine;
import game.Board;
import game.Cell;
import game.Move;
import game.Player;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GameEngine gameEngine = new GameEngine();
        Board board = gameEngine.start("TicTacToe");

        //make moves in a loop
        int row, col;
        Scanner scanner = new Scanner(System.in);
        while(!gameEngine.isComplete(board).isOver()) {
            System.out.println("Make your move!");
            row = scanner.nextInt();
            col = scanner.nextInt();

            Player human = new Player("X");
            Move humanMove = new Move(new Cell(row, col));
            gameEngine.move(board, human, humanMove);

            Player computer = new Player("O");
            if(!gameEngine.isComplete(board).isOver()) {
                Move computerMove = gameEngine.suggestMove(computer, board);
                gameEngine.move(board, computer, computerMove);
            }

        }

        System.out.println("Game Result: " + gameEngine.isComplete(board));

    }
}
