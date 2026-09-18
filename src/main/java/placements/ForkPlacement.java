package placements;

import game.Board;
import game.Cell;

import java.util.Optional;

public class ForkPlacement implements Placement {
    private static ForkPlacement forkPlacement;

    private ForkPlacement(){}

    public static synchronized Placement get() {
        if(forkPlacement == null) {
            forkPlacement = new ForkPlacement();
        }
        return forkPlacement;
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
