package strategy;

import api.RuleEngine;
import boards.TicTacToeBoard;
import game.Cell;
import game.Move;
import game.Player;

public class SmartStrategy extends Strategy{
    RuleEngine ruleEngine = new RuleEngine();
    private BasicStrategy basicStrategy = new BasicStrategy();
    @Override
    public Cell getOptimalMove(TicTacToeBoard b, Player player) {
        //Attacking Move
        Cell best = offense(player, b);
        if (best != null) return best;
        //Defensive Move
        best = defense(player, b);
        if (best != null) return best;

        return basicStrategy.getOptimalMove(b, player);
    }

    private Cell offense(Player player, TicTacToeBoard board) {
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                if(board.getSymbol(i, j) == null) {
                    Move move = new Move(Cell.getCell(i, j), player);
                    TicTacToeBoard boardCopy = board.move(move);
                    if(ruleEngine.getState(boardCopy).isOver()) {
                        return move.getCell();
                    }
                }
            }
        }
        return null;
    }

    private Cell defense(Player player, TicTacToeBoard board) {
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                if(board.getSymbol(i, j) == null) {
                    Move move = new Move(Cell.getCell(i, j), player.flip());
                    TicTacToeBoard boardCopy = board.move(move);
                    if(ruleEngine.getState(boardCopy).isOver()) {
                        return Cell.getCell(i, j);
                    }
                }
            }
        }
        return null;
    }
}
