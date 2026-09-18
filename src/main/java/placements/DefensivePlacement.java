package placements;

import game.Board;
import game.Cell;

import java.util.Optional;

public class DefensivePlacement implements Placement {
    private static DefensivePlacement defensivePlacement;

    private DefensivePlacement(){}

    public static synchronized Placement get() {
        if(defensivePlacement == null) {
            defensivePlacement = new DefensivePlacement();
        }
        return defensivePlacement;
    }

    @Override
    public Optional<Cell> place(Board board) {
        return Optional.empty();
    }

    @Override
    public Placement next() {
        return null;
    }
}
