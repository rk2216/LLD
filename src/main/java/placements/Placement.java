package placements;

import game.Board;
import game.Cell;

import java.util.Optional;

public interface Placement {
    Optional<Cell> place(Board board);
    Placement next();
}
