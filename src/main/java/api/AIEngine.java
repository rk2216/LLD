package api;

import boards.TicTacToeBoard;
import game.Board;
import game.Cell;
import game.Move;
import game.Player;

public class AIEngine {
    public Move suggestMove(Player computer, Board board) {
        if(board instanceof TicTacToeBoard) {
            TicTacToeBoard board1 = (TicTacToeBoard) board;
            Move suggestion;
            if(isStarting(board1, 3)) {
                suggestion = getBasicMove(computer, board1);
            } else {
                suggestion = getSmartMove(computer, board1);
            }
            if(suggestion != null)
                return suggestion;
            throw new IllegalStateException();
        } else {
            throw new IllegalArgumentException();
        }
    }

    private boolean isStarting(TicTacToeBoard board, int threshold) {
        int count = 0;
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                if(board.getSymbol(i, j) != null) {
                    count++;
                }
            }
        }

        return count < threshold;
    }

    private Move getBasicMove(Player computer, TicTacToeBoard board) {
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                if(board.getSymbol(i, j) == null) {
                    return new Move(new Cell(i, j), computer);
                }
            }
        }
        return null;
    }

    private Move getSmartMove(Player computer, TicTacToeBoard board) {
        RuleEngine ruleEngine = new RuleEngine();

        //Attacking Move
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                if(board.getSymbol(i, j) == null) {
                    Move move = new Move(new Cell(i, j), computer);
                    board.move(move);
                    if(ruleEngine.getState(board).isOver()) {
                        return move;
                    }
                }
            }
        }

        //Defensive Move
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                if(board.getSymbol(i, j) == null) {
                    Move move = new Move(new Cell(i, j), computer.flip());
                    board.move(move);
                    if(ruleEngine.getState(board).isOver()) {
                        return new Move(new Cell(i, j), computer);
                    }
                }
            }
        }

        return getBasicMove(computer, board);
    }
}
