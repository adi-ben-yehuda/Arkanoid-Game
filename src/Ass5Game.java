import levels.FirstLevel;
import game.GameLevel;
import biuoop.GUI;
import levels.FourthLevel;
import levels.SecondLevel;
import levels.ThirdLevel;

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
        ThirdLevel thirdLevel = new ThirdLevel();
        FourthLevel fourthLevel = new FourthLevel();
        GameLevel game = new GameLevel(gui, fourthLevel);

        game.initialize();
        game.run();
    }
}
