package api;

import boards.Board;
import boards.CellBoard;
import boards.TicTacToeBoard;
import boards.TicTacToeBoard.Symbol;
import game.*;
import placements.DefensivePlacement;
import placements.OffensivePlacement;
import placements.Placement;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class RuleEngine {

    Map<String, RuleSet> ruleMap = new HashMap<>();

    public RuleEngine() {
        String key = TicTacToeBoard.class.getName();
        ruleMap.put(key, TicTacToeBoard.getRules());
    }

    public GameInfo getInfo(CellBoard board) {
        if(board instanceof TicTacToeBoard) {
            TicTacToeBoard ticTacToeBoard = (TicTacToeBoard) board;
            GameState gameState = getState(ticTacToeBoard);
            for(Symbol symbol : Symbol.values()) {
                Player player = new Player(symbol.marker());
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if(ticTacToeBoard.getSymbol(i, j) == null) {
                            TicTacToeBoard b = ticTacToeBoard.move(new Move(Cell.getCell(i, j), player));
                            //force opponent to make a defensive move
                            Placement defense  = DefensivePlacement.get();
                            Optional<Cell> defensiveCell = defense.place(b, player.flip());
                            if(defensiveCell.isPresent()) {
                                b = b.move(new Move(defensiveCell.get(), player.flip()));
                                OffensivePlacement offense = OffensivePlacement.get();
                                Optional<Cell> offensiveCell = offense.place(b, player);
                                if(offensiveCell.isPresent()) {
                                    return new GameInfoBuilder()
                                            .isOver(gameState.isOver())
                                            .winner(gameState.getWinner())
                                            .hasFork(true)
                                            .forkCell(Cell.getCell(i, j))
                                            .player(player.flip())
                                            .build();
                                }
                            }
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
            RuleSet rules = ruleMap.get(TicTacToeBoard.class.getName());
            for(Rule r : rules) {
                GameState gameState = r.condition.apply(tBoard);
                if(gameState.isOver()) {
                    return gameState;
                }
            }
        }
        return new GameState(false, "-");
    }

}

