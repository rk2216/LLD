import game.*;
import org.junit.jupiter.api.Test;

public class GameTest {
    GameFactory gameFactory = new GameFactory();

    @Test void timeOutTest() {
        Game game = gameFactory.createGame(3, 120);
        Player x = new Player("X");
        Cell c00 = Cell.getCell(0, 0);
        int ts = 5000;
        game.move(new Move(c00, x), ts);
    }

    @Test void timeOutTestPerPlayer() {
        Game game = gameFactory.createGame(null, 120);

    }
}
