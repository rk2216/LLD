import api.*;
import boards.Board;
import commands.implementations.EmailCommand;
import commands.implementations.SMSCommand;
import events.Event;
import events.EventBus;
import events.Subscriber;
import game.Cell;
import game.Move;
import game.Player;
import services.EmailService;
import services.SMSService;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        GameEngine gameEngine = new GameEngine();
        RuleEngine ruleEngine = new RuleEngine();
        AIEngine aiEngine = new AIEngine();
        EmailService emailService = new EmailService();
        SMSService smsService = new SMSService();
        Board board = gameEngine.start("TicTacToe");

        EventBus eventBus = new EventBus();
        eventBus.subscribe(new Subscriber(event -> emailService.send(new EmailCommand(event))));
        eventBus.subscribe(new Subscriber(event -> smsService.send(new SMSCommand(event))));

        //make moves in a loop
        int row, col;
        Scanner scanner = new Scanner(System.in);

        Player human = new Player("X");
        Player computer = new Player("O");

        if(human.getUser().activeAfter(10, TimeUnit.DAYS)) {
            eventBus.publish(new Event(
                    human.getUser(),
                    "We are glad you are back!",
                    "https://www.google.com",
                    "ACTIVITY"
            ));
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
            eventBus.publish(new Event(
                    human.getUser(),
                    "Congratulations on the win!",
                    null,
                    "ACTIVITY"
            ));
        }
        System.out.println("Game Result: " + ruleEngine.getState(board));
        System.out.println(board);
    }
}
