package placements;

import boards.TicTacToeBoard;
import game.Cell;
import game.Player;
import utils.Utils;

import java.util.Optional;

public class CornerPlacement implements Placement {
    private static CornerPlacement cornerPlacement;

    private CornerPlacement(){}

    public static synchronized CornerPlacement get() {
        cornerPlacement = (CornerPlacement) Utils.getIfNull(cornerPlacement, CornerPlacement::new);
        return cornerPlacement;
    }

    @Override
    public Optional<Cell> place(TicTacToeBoard board, Player player) {
        //6. If the corner is available, take it.
        Cell corner = null;
        int[][] corners = new int[][]{{0, 0}, {0, 2}, {2, 0}, {2, 2}};
        for(int i=0; i<4; i++) {
            if(board.getSymbol(corners[i][0], corners[i][1]) == null) {
                corner = Cell.getCell(corners[i][0], corners[i][1]);
                break;
            }
        }
        return Optional.ofNullable(corner);
    }

    @Override
    public Placement next() {
        return null;
    }
}
