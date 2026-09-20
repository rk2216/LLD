import game.Game;
import game.GameFactory;
import org.junit.jupiter.api.Test;

public class GameTest {
    GameFactory gameFactory = new GameFactory();

    @Test void timeOutTest() {
        Game game = gameFactory.createGame(3, 120);

    }

    @Test void timeOutTestPerPlayer() {
        Game game = gameFactory.createGame(null, 120);

    }
}
