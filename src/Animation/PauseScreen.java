package Animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Adi Ben Yehuda 211769757
 * @since 2022-05-26
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * The function constructs a new PauseScreen.
     *
     * @param k
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    /**
     * The function writes a message to the user on the surface and continues
     * the game if the user clicks on the space.
     *
     * @param d
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        int xSize = 10, textSize = 32;
        String text = "paused -- press space to continue";

        d.drawText(xSize, d.getHeight() / 2, text, textSize);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    /**
     * The function stops the game.
     *
     * @return true if the game is over, otherwise false.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
