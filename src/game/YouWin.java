package game;

import Animation.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class YouWin implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private Counter score;

    /**
     * The function constructs a new PauseScreen.
     *
     * @param k
     */
    public YouWin(KeyboardSensor k, Counter score) {
        this.keyboard = k;
        this.stop = false;
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        int xWin = 280, yMss = 250, textSize = 50, xScore = 230, yScore = 320;
        String winText = "YOU WIN!",
                scoreText = "Your score is " + score.getValue();

        d.drawText(xWin, yMss, winText, textSize);
        d.drawText(xScore, yScore, scoreText, textSize);

        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
