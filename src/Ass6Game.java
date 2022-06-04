import Animation.AnimationRunner;
import biuoop.KeyboardSensor;
import game.GameFlow;
import game.LevelInformation;
import levels.FirstLevel;
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
public class Ass6Game {

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

        /* When run without arguments, you should start a game with four
        levels that run one after the other. */
        if (args.length == 0) {
            levels.add(new FirstLevel());
            levels.add(new SecondLevel());
            levels.add(new ThirdLevel());
            levels.add(new FourthLevel());
        } else {
            /* When run with additional arguments, the arguments should be
            treated as a list of level numbers to run, in the specified order. */
            for (String level : args) {
                switch (level) {
                    case "1":
                        levels.add(new FirstLevel());
                        break;
                    case "2":
                        levels.add(new SecondLevel());
                        break;
                    case "3":
                        levels.add(new ThirdLevel());
                        break;
                    case "4":
                        levels.add(new FourthLevel());
                        break;
                    default:
                        break;
                }
            }
        }

        if (levels.size() > 0) {
            GameFlow gameFlow = new GameFlow(animationRunner, keyboardSensor, gui);
            gameFlow.runLevels(levels);
        }
    }
}
