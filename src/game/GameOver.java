package game;

import Animation.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Adi Ben Yehuda 
 * @since 2022-06-02
 */
public class GameOver implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private Counter score;

    /**
     * The function constructs a new GameOver object.
     *
     * @param k
     * @param score
     */
    public GameOver(KeyboardSensor k, Counter score) {
        this.keyboard = k;
        this.stop = false;
        this.score = score;
    }

    /**
     * The function does one frame.
     *
     * @param d
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        int xLose = 275, yMss = 250, textSize = 50, xScore = 230, yScore = 320;
        String loseText = "Game Over.",
                scoreText = "Your score is " + score.getValue();

        d.drawText(xLose, yMss, loseText, textSize);
        d.drawText(xScore, yScore, scoreText, textSize);

        /* The "end screen" should persist until the space key is pressed.
        After the space key is pressed, your program should terminate. */
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    /**
     * The function stops displaying the game over screen.
     *
     * @return true if you need to stop displaying the game over screen,
     * otherwise false.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
