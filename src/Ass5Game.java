import levels.FirstLevel;
import game.GameLevel;
import biuoop.GUI;

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

        FirstLevel firstLevel = new FirstLevel(1, "lego man");
        GameLevel game = new GameLevel(gui, firstLevel);

        game.initialize();
        game.run();
    }
}
