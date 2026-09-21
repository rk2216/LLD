package game;

import api.RuleEngine;
import boards.Board;

public class Game {
    private GameConfig gameConfig;
    private Board board;
    Player winner;

    private Integer lastMoveTimeInMillis;
    private Integer maxTimePerPlayer;
    private Integer maxTimePerMove;
    private RuleEngine ruleEngine = new RuleEngine();

    public Game(GameConfig gameConfig, Board board, Player winner, Integer lastMoveTimeInMillis, Integer maxTimePerPlayer, Integer maxTimePerMove){
        this.gameConfig = gameConfig;
        this.board = board;
        this.winner = winner;
        this.lastMoveTimeInMillis = lastMoveTimeInMillis;
        this.maxTimePerMove = maxTimePerMove;
        this.maxTimePerPlayer = maxTimePerPlayer;
    }

    public void move(Move move, int timestampInMillis) {
        if(winner != null) {
            return;
        }
        int timeTakenSinceLastMove = timestampInMillis - lastMoveTimeInMillis;
        move.getPlayer().setTimeTaken(timeTakenSinceLastMove);
        if(gameConfig.timed) {
            moveForTimedGame(move, timeTakenSinceLastMove);
        } else {
            board = board.move(move);
        }
        if (winner==null && ruleEngine.getState(board).isOver()) {
            winner = move.getPlayer();
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
            board = board.move(move);
        } else {
            winner = move.getPlayer().flip();
        }
    }

    public Player getWinner() {
        return winner;
    }
}
