package game;

import Animation.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class GameOver implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private Counter score;

    /**
     * The function constructs a new PauseScreen.
     *
     * @param k
     */
    public GameOver(KeyboardSensor k, Counter score) {
        this.keyboard = k;
        this.stop = false;
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        int xLose = 275, yMss = 250, textSize = 50, xScore = 230, yScore = 320;
        String loseText = "Game Over.",
                scoreText = "Your score is " + score.getValue();

        d.drawText(xLose, yMss, loseText, textSize);
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
