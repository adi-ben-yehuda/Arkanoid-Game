package game;

import Animation.Animation;
import Animation.AnimationRunner;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * @author Adi Ben Yehuda 211769757
 * @since 2022-05-31
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private GUI gui;
    private Counter score;

    /**
     * The function creates a new GameFlow object.
     *
     * @param ar
     * @param ks
     * @param gui
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.gui = gui;
        this.score = new Counter();
    }

    /**
     * The function runs all the levels of the game.
     *
     * @param levels - all the levels of the game.
     */
    public void runLevels(List<LevelInformation> levels) {
        GameLevel level = null;

        // Run each level of the game.
        for (LevelInformation levelInfo : levels) {
            level = new GameLevel(levelInfo,
                    this.keyboardSensor,
                    this.animationRunner, this.gui);
            level.initialize();
            level.setScore(this.score.getValue());

            while (level.hasBlocksAndBalls()) {
                level.run(); // Run the level
            }

            this.score = level.getScore();
            if (!level.hasBalls()) {
                break;
            }
        }

        endGame(level);
    }

    public void endGame(GameLevel level) {
        Animation animation = level.endScreen();

        while (!animation.shouldStop()) {
            this.animationRunner.run(animation);
        }
        gui.close();
    }
}
