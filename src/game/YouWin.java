package game;

import Animation.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Adi Ben Yehuda 
 * @since 2022-06-02
 */
public class YouWin implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private Counter score;

    /**
     * The function constructs a new YouWin object.
     *
     * @param k
     * @param score
     */
    public YouWin(KeyboardSensor k, Counter score) {
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
        int xWin = 280, yMss = 250, textSize = 50, xScore = 230, yScore = 320;
        String winText = "YOU WIN!",
                scoreText = "Your score is " + score.getValue();

        d.drawText(xWin, yMss, winText, textSize);
        d.drawText(xScore, yScore, scoreText, textSize);

        /* The "end screen" should persist until the space key is pressed.
        After the space key is pressed, your program should terminate. */
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    /**
     * The function stops displaying the you-win screen.
     *
     * @return true if you need to stop displaying the you-win screen,
     * otherwise false.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
