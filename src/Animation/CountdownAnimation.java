package Animation;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import different_sprites.SpriteCollection;

/**
 * @author Adi Ben Yehuda 
 * @since 2022-05-26
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private int count;

    /**
     * The function constructs a new CountdownAnimation.
     *
     * @param countFrom
     * @param gameScreen
     * @param numOfSeconds
     */
    public CountdownAnimation(double numOfSeconds, int countFrom,
                              SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.count = countFrom;
    }

    /**
     * The function draws on the surface the countdown from the count set to 1.
     *
     * @param d
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        int xText = 400, yText = 500, textSize = 70;
        Sleeper sleeper = new Sleeper();

        if (countFrom != count) {
            sleeper.sleepFor((long) (numOfSeconds * 1000 / countFrom));
        }

        d.drawText(xText, yText, Integer.toString(count), textSize);
        this.gameScreen.drawAllOn(d);
        this.count--;
    }

    /**
     * The function stops the countdown when the count is -1.
     *
     * @return true if should stop, otherwise false.
     */
    @Override
    public boolean shouldStop() {
        if (this.count == -1) {
            return true;
        }
        return false;
    }
}
