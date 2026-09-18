package placements;

import game.Board;
import game.Cell;

import java.util.Optional;

public class OffensivePlacement implements Placement{
    private static OffensivePlacement offensivePlacement;

    private OffensivePlacement(){}

    public static synchronized Placement get() {
        if(offensivePlacement == null) {
            offensivePlacement = new OffensivePlacement();
        }
        return offensivePlacement;
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
