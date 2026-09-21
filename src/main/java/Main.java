import api.AIEngine;
import api.GameEngine;
import api.RuleEngine;
import boards.Board;
import game.Cell;
import game.Move;
import game.Player;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GameEngine gameEngine = new GameEngine();
        RuleEngine ruleEngine = new RuleEngine();
        AIEngine aiEngine = new AIEngine(ruleEngine);
        Board board = gameEngine.start("TicTacToe");

        //make moves in a loop
        int row, col;
        Scanner scanner = new Scanner(System.in);
        while(!ruleEngine.getState(board).isOver()) {
            System.out.println("Make your move!");
            System.out.println(board);

            row = scanner.nextInt();
            col = scanner.nextInt();

            Player human = new Player("X");
            Move humanMove = new Move(new Cell(row, col), human);
            board = gameEngine.move(board, humanMove);

            Player computer = new Player("O");
            if(!ruleEngine.getState(board).isOver()) {
                Move computerMove = aiEngine.suggestMove(computer, board);
                board = gameEngine.move(board, computerMove);
            }

        }

        System.out.println("Game Result: " + ruleEngine.getState(board));
        System.out.println(board);
    }
}
