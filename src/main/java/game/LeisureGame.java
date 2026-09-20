package game;

import boards.Board;

public class LeisureGame extends Game {
    public LeisureGame(GameConfig gameConfig, Board board, Player winner, Integer lastMoveTimeInMillis, Integer maxTimePerPlayer, Integer maxTimePerMove) {
        super(gameConfig, board, winner, lastMoveTimeInMillis, maxTimePerPlayer, maxTimePerMove);
    }
}
