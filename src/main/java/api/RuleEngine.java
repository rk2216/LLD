package api;

import boards.TicTacToeBoard;
import game.*;

import java.util.function.BiFunction;
import java.util.function.Function;

public class RuleEngine {

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
                            return new GameInfo(gameState, true, player.flip());
                        }
                    }
                }
            }
            return new GameInfo(gameState, false, null);
        } else {
            throw new IllegalArgumentException();
        }
    }
    public GameState getState(Board board) {
        if(board instanceof TicTacToeBoard) {
            TicTacToeBoard board1 = (TicTacToeBoard) board;
            String firstCharacter = "-";

            BiFunction<Integer, Integer, String> getRowNextCharacter = (i, j) -> board1.getSymbol(i, j);
            BiFunction<Integer, Integer, String> getColNextCharacter = (i, j) -> board1.getSymbol(j, i);

            GameState rowWin = outerTraversal(getRowNextCharacter);
            if(rowWin.isOver())
                return rowWin;

            GameState colWin = outerTraversal(getColNextCharacter);
            if(colWin.isOver())
                return colWin;

            Function<Integer, String> getDiagNextCharacter = i -> board1.getSymbol(i, i);
            Function<Integer, String> getRevDiagNextCharacter = i -> board1.getSymbol(i, 2-i);

            GameState diagWin = traverse(getDiagNextCharacter);
            if(diagWin.isOver())
                return diagWin;

            GameState revDiagWin = traverse(getRevDiagNextCharacter);
            if(revDiagWin.isOver())
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

    private GameState outerTraversal(BiFunction<Integer, Integer, String> next) {
        GameState result = new GameState(false, "-");
        for(int i=0; i<3; i++) {
            final int ii = i;
            Function<Integer, String> traversal = (j) -> next.apply(ii, j);
            GameState innerTraversal = traverse(traversal);
            if(innerTraversal.isOver()) {
                result = innerTraversal;
            }
        }
        return result;
    }

    private GameState traverse(Function<Integer, String> traversal) {
        GameState result = new GameState(false, "-");
        boolean possibleStreak = true;
        for(int j = 0; j <3; j++) {
            if(traversal.apply(j) == null || !traversal.apply(0).equals(traversal.apply(j))) {
                possibleStreak = false;
                break;
            }
        }
        if(possibleStreak) {
            result = new GameState(true, traversal.apply(0));
        }
        return result;
    }
}

