import api.AIEngine;
import api.EmailService;
import api.GameEngine;
import api.RuleEngine;
import boards.Board;
import commands.builder.SendEmailCommandBuilder;
import game.Cell;
import game.Move;
import game.Player;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        GameEngine gameEngine = new GameEngine();
        RuleEngine ruleEngine = new RuleEngine();
        AIEngine aiEngine = new AIEngine();
        EmailService emailService = new EmailService();
        Board board = gameEngine.start("TicTacToe");

        //make moves in a loop
        int row, col;
        Scanner scanner = new Scanner(System.in);

        Player human = new Player("X");
        Player computer = new Player("O");

        if(human.getUser().activeAfter(10, TimeUnit.DAYS)) {
            emailService.send(new SendEmailCommandBuilder()
                    .user(human.getUser())
                    .message("We are glad you are back!")
                    .build()
            );
        }
        while(!ruleEngine.getState(board).isOver()) {
            System.out.println("Make your move!");
            System.out.println(board);

            row = scanner.nextInt();
            col = scanner.nextInt();

            Move humanMove = new Move(Cell.getCell(row, col), human);
            board = gameEngine.move(board, humanMove);

            if(!ruleEngine.getState(board).isOver()) {
                Move computerMove = aiEngine.suggestMove(computer, board);
                board = gameEngine.move(board, computerMove);
            }

        }
        if(ruleEngine.getState(board).getWinner().equals(human.symbol())) {
            emailService.send(new SendEmailCommandBuilder()
                    .user(human.getUser())
                    .message("Congratulations on the win!")
                    .build()
            );
        }
        System.out.println("Game Result: " + ruleEngine.getState(board));
        System.out.println(board);
    }
}
