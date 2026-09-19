package boards;

import api.Rule;
import api.RuleSet;
import game.Cell;
import game.GameState;
import game.Move;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class TicTacToeBoard implements CellBoard {
    String[][] cells;
    History history;

    public TicTacToeBoard() {
        cells =  new String[3][3];
        history = new History();
    }

    public TicTacToeBoard(History history) {
        cells =  new String[3][3];
        this.history = history;
    }

    public TicTacToeBoard(Representation boardProxy) {
        //Construct TicTacToeBoard out of boardProxy
    }

    public String getSymbol(int i, int j) {
        return cells[i][j];
    }

    public static RuleSet getRules() {
        RuleSet rules = new RuleSet();
        rules.add(new Rule(board -> outerTraversal((i, j) -> board.getSymbol(i, j))));
        rules.add(new Rule(board -> outerTraversal((i, j) -> board.getSymbol(j, i))));
        rules.add(new Rule(board -> traverse((i) -> board.getSymbol(i, i))));
        rules.add(new Rule(board -> traverse((i) -> board.getSymbol(i, 2-i))));
        rules.add(new Rule(board -> {
            int countOfFilledCells = 0;
            for(int i=0; i<3; i++) {
                for(int j=0; j<3; j++) {
                    if(board.getSymbol(i, j) != null) {
                        countOfFilledCells++;
                    }
                }
            }
            if(countOfFilledCells == 9) {
                return new GameState(true, "-");
            }

            return new GameState(false, "-");
        }));

        return rules;
    }


    private static GameState outerTraversal(BiFunction<Integer, Integer, String> next) {
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

    private static GameState traverse(Function<Integer, String> traversal) {
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

    public void setCell(Cell cell, String symbol) {
        if(cells[cell.getRow()][cell.getCol()] == null) {
            cells[cell.getRow()][cell.getCol()] = symbol;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        String result = "";
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                result += (cells[i][j] != null) ? cells[i][j]: "-";
            }
            result += "\n";
        }
        return result;
    }

    @Override
    public TicTacToeBoard move(Move move) {
        history.add(new Representation(this));
        TicTacToeBoard board = copy();
        board.setCell(move.getCell(), move.getPlayer().symbol());
        return board;
    }

    @Override
    public TicTacToeBoard copy() {
        TicTacToeBoard ticTacToeBoard = new TicTacToeBoard();
        for(int i=0; i<3; i++) {
            System.arraycopy(cells[i], 0, ticTacToeBoard.cells[i], 0, 3);
        }

        ticTacToeBoard.history = history;

        return ticTacToeBoard;
    }
}

class History {
    List<Representation> boards = new ArrayList<>();

    public Representation getBoardAtMove(int moveIndex) {
        for(int i=0; i<boards.size()-(moveIndex+1); i++) {
            boards.remove(boards.size()-1);
        }
        return boards.get(moveIndex);
    }

    public Representation undo() {
        boards.remove(boards.size()-1);
        return boards.get(boards.size()-1);
    }

    public void add(Representation representation) {
        boards.add(representation);
    }
}

class Representation { // Similar to BoardProxy - Proxy Design Pattern
    String representation;

    public Representation(TicTacToeBoard board) {
        representation = board.toString();
    }
}
