package api;

import boards.TicTacToeBoard;
import game.*;

import java.util.HashMap;
import java.util.Map;

public class RuleEngine {

    Map<String, RuleSet> ruleMap = new HashMap<>();

    public RuleEngine() {
        String key = TicTacToeBoard.class.getName();
        ruleMap.put(key, TicTacToeBoard.getRules());
    }

    public GameInfo getInfo(Board board) {
        if(board instanceof TicTacToeBoard) {
            GameState gameState = getState(board);
            String[] players = new String[]{"X", "O"};
            for(int index = 0; index < 2; index++) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        Board boardCopy = board.copy();
                        Player player = new Player(players[index]);
                        boardCopy.move(new Move(new Cell(i, j), player));
                        boolean canStillWin = false;
                        for (int k = 0; k < 3; k++) {
                            for (int l = 0; l < 3; l++) {
                                Board b = boardCopy.copy();
                                b.move(new Move(new Cell(k, l), player.flip()));
                                if (getState(b).getWinner().equals(player.flip().symbol())) {
                                    canStillWin = true;
                                    break;
                                }
                            }
                            if (canStillWin) {
                                break;
                            }
                        }
                        if (canStillWin) {
                            return new GameInfoBuilder()
                                    .isOver(gameState.isOver())
                                    .winner(gameState.getWinner())
                                    .hasFork(true)
                                    .player(player.flip())
                                    .build();
                        }
                    }
                }
            }
            return new GameInfoBuilder()
                    .isOver(gameState.isOver())
                    .winner(gameState.getWinner())
                    .build();

        } else {
            throw new IllegalArgumentException();
        }
    }
    public GameState getState(Board board) {
        if(board instanceof TicTacToeBoard) {
            TicTacToeBoard tBoard = (TicTacToeBoard) board;
            RuleSet<TicTacToeBoard> rules = ruleMap.get(TicTacToeBoard.class.getName());
            for(Rule<TicTacToeBoard> r : rules) {
                GameState gameState = r.condition.apply(tBoard);
                if(gameState.isOver()) {
                    return gameState;
                }
            }
        }
        return new GameState(false, "-");
    }

}

