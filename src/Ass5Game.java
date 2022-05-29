import levels.FirstLevel;
import game.GameLevel;
import biuoop.GUI;
import levels.SecondLevel;

/**
 * @author Adi Ben Yehuda 211769757
 * @since 2022-04-14
 */
public class Ass5Game {

    /**
     * The function creates a game object, initializes and runs it.
     *
     * @param args
     */
    public static void main(String[] args) {
        int width = 800, height = 600;
        GUI gui = new GUI("Arkanoid game", width, height);

        FirstLevel firstLevel = new FirstLevel();
        SecondLevel secondLevel = new SecondLevel();
        GameLevel game = new GameLevel(gui, secondLevel);

        game.initialize();
        game.run();
    }
}
