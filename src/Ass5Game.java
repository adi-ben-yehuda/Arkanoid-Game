import Animation.AnimationRunner;
import biuoop.KeyboardSensor;
import game.GameFlow;
import game.LevelInformation;
import levels.FirstLevel;
import game.GameLevel;
import biuoop.GUI;
import levels.FourthLevel;
import levels.SecondLevel;
import levels.ThirdLevel;

import java.util.ArrayList;
import java.util.List;

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
        AnimationRunner animationRunner = new AnimationRunner(gui);
        KeyboardSensor keyboardSensor = gui.getKeyboardSensor();

        List<LevelInformation> levels = new ArrayList<>();
        levels.add(new FirstLevel());
//        levels.add(new SecondLevel());
//        levels.add(new ThirdLevel());
//        levels.add(new FourthLevel());

        GameFlow gameFlow = new GameFlow(animationRunner, keyboardSensor, gui);
        gameFlow.runLevels(levels);
    }
}
