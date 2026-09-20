package game;

import boards.Board;

public class Game {
    private GameConfig gameConfig;
    private Board board;
    Player winner;

    private int lastMoveTimeInMillis;
    private int maxTimePerPlayer;
    private int maxTimePerMove;

    public void move(Move move, int timestampInMillis) {
        int timeTakenSinceLastMove = timestampInMillis - lastMoveTimeInMillis;
        move.getPlayer().setTimeTaken(timeTakenSinceLastMove);
        if(gameConfig.timed) {
            moveForTimedGame(move, timeTakenSinceLastMove);
        } else {
            board.move(move);
        }
    }

    private void moveForTimedGame(Move move, int timeTakenSinceLastMove) {
        final int currentTime, endTime;
        if(gameConfig.timePerMove != null) {
            currentTime = timeTakenSinceLastMove;
            endTime = maxTimePerMove;
        } else {
            currentTime = move.getPlayer().getTimeUsedInMillis();
            endTime = maxTimePerPlayer;
        }

        if (currentTime < endTime){
            board.move(move);
        } else {
            winner = move.getPlayer().flip();
        }
    }
}
