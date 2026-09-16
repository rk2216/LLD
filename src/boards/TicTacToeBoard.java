package boards;

import game.Board;

public class TicTacToeBoard extends Board {
    String[][] cells = new String[3][3];
    public String getCell(int i, int j) {
        return cells[i][j];
    }
}
