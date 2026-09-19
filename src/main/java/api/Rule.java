package api;

import boards.Board;
import boards.CellBoard;
import game.GameState;

import java.util.function.Function;

public class Rule {
    // Using TicTacToeBoard instead of CellBoard would violate Dependency Inversion.
    Function<CellBoard, GameState> condition;

    public Rule(Function<CellBoard, GameState> condition) {
        this.condition = condition;
    }
}
