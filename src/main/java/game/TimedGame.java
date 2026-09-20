package game;

import boards.Board;

public class TimedGame extends Game {
    public TimedGame(GameConfig gameConfig, Board board, Player winner, Integer lastMoveTimeInMillis, Integer maxTimePerPlayer, Integer maxTimePerMove) {
        super(gameConfig, board, winner, lastMoveTimeInMillis, maxTimePerPlayer, maxTimePerMove);
    }
}
