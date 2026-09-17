package api;

import boards.TicTacToeBoard;
import game.Board;
import game.GameState;

import java.util.function.BiFunction;
import java.util.function.Function;

public class RuleEngine {

    public GameState getState(Board board) {
        if(board instanceof TicTacToeBoard) {
            TicTacToeBoard board1 = (TicTacToeBoard) board;
            String firstCharacter = "-";

            BiFunction<Integer, Integer, String> getRowNextCharacter = (i, j) -> board1.getSymbol(i, j);
            BiFunction<Integer, Integer, String> getColNextCharacter = (i, j) -> board1.getSymbol(j, i);

            GameState rowWin = isVictory(getRowNextCharacter);
            if(rowWin != null)
                return rowWin;

            GameState colWin = isVictory(getColNextCharacter);
            if(colWin != null)
                return colWin;

            Function<Integer, String> getDiagNextCharacter = i -> board1.getSymbol(i, i);
            Function<Integer, String> getRevDiagNextCharacter = i -> board1.getSymbol(i, 2-i);

            GameState diagWin = isDiagVictory(getDiagNextCharacter);
            if(diagWin != null)
                return diagWin;

            GameState revDiagWin = isDiagVictory(getRevDiagNextCharacter);
            if(revDiagWin != null)
                return revDiagWin;

            int countOfFilledCells = 0;
            for(int i=0; i<3; i++) {
                for(int j=0; j<3; j++) {
                    if(board1.getSymbol(i, j) != null) {
                        countOfFilledCells++;
                    }
                }
            }
            if(countOfFilledCells == 9) {
                return new GameState(true, "-");
            }

            return new GameState(false, "-");
        }
        return new GameState(false, "-");
    }

    private GameState isVictory(BiFunction<Integer, Integer, String> next) {
        for(int i=0; i<3; i++) {
            boolean possibleStreak = true;
            for(int j=0; j<3; j++) {
                if(next.apply(i, j) == null ||
                        !next.apply(i, 0).equals(next.apply(i, j))) {
                    possibleStreak = false;
                    break;
                }
            }
            if(possibleStreak) {
                return new GameState(true, next.apply(i, 0));
            }
        }
        return null;
    }

    private GameState isDiagVictory(Function<Integer, String> next) {
        boolean possibleStreak = true;
        for(int i=0; i<3; i++) {
            if(next.apply(i) == null || !next.apply(0).equals(next.apply(i))) {
                possibleStreak = false;
                break;
            }
        }
        if(possibleStreak) {
            return new GameState(true, next.apply(0));
        }
        return null;
    }
}
