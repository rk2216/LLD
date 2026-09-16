public class Main {
    public static void main(String[] args) {


    }

    public Board start() {
        return new Board();
    }

    public void move(Board board, Player player, Move move) {

    }

    public GameResult isComplete(Board board) {
        if(board instanceof TicTacToeBoard) {
            TicTacToeBoard board1 = (TicTacToeBoard) board;
            String firstCharacter = "-";
            boolean rowComplete = true;
            for(int i=0; i<3; i++) {
                rowComplete = true;
                firstCharacter = board1.cells[i][0];
                for(int j=1; j<3; j++) {
                    if(!board1.cells[i][j].equals(firstCharacter)) {
                        rowComplete = false;
                        break;
                    }
                }
                if(rowComplete) {
                    break;
                }
            }
            if(rowComplete) {
                return new GameResult(true, firstCharacter);
            }

            boolean colComplete = true;
            for(int i=0; i<3; i++) {
                colComplete = true;
                firstCharacter = board1.cells[0][i];
                for(int j=1; j<3; j++) {
                    if(!board1.cells[j][i].equals(firstCharacter)) {
                        colComplete = false;
                        break;
                    }
                }
                if(colComplete) {
                    break;
                }
            }
            if(colComplete) {
                return new GameResult(true, firstCharacter);
            }

            boolean diagComplete = true;
            for(int i=1; i<3; i++) {
                diagComplete = true;
                firstCharacter = board1.cells[0][0];
                if(!board1.cells[i][i].equals(firstCharacter)) {
                    diagComplete = false;
                    break;
                }
            }
            if(diagComplete) {
                return new GameResult(true, firstCharacter);
            }

            boolean revDiagComplete = true;
            for(int i=1; i<3; i++) {
                revDiagComplete = true;
                firstCharacter = board1.cells[0][2];
                if(!board1.cells[i][2-i].equals(firstCharacter)) {
                    revDiagComplete = false;
                    break;
                }
            }
            if(revDiagComplete) {
                return new GameResult(true, firstCharacter);
            }

            int countOfFilledCells = 0;
            for(int i=0; i<3; i++) {
                for(int j=0; j<3; j++) {
                    if(board1.cells[i][j] != null) {
                        countOfFilledCells++;
                    }
                }
            }
            if(countOfFilledCells == 9) {
                return new GameResult(true, "-");
            }

            return new GameResult(false, "-");
        }
        return new GameResult(false, "-");
    }
}

public class Board {

}

public class TicTacToeBoard extends Board {
    String cells[][] = new String[3][3];
}

public class Player {

}

public class Move {

}

public class GameResult {
    boolean isOver;
    String winner;

    public GameResult(boolean isOver, String winner) {
        this.isOver = isOver;
        this.winner = winner;
    }

}
