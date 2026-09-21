package strategy;

import boards.TicTacToeBoard;
import game.Cell;
import game.Player;

public class BasicStrategy extends Strategy{
    @Override
    public Cell getOptimalMove(TicTacToeBoard b, Player player) {
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                if(b.getSymbol(i, j) == null) {
                    return Cell.getCell(i, j);
                }
            }
        }
        return null;
    }
}
