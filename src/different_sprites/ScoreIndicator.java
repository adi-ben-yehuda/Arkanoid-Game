package different_sprites;

import biuoop.DrawSurface;
import game.Counter;
import game.ScoreTrackingListener;

/**
 * @author Adi Ben Yehuda 
 * @since 2022-05-13
 */
public class ScoreIndicator implements Sprite {
    private ScoreTrackingListener scoreTrackingListener;

    /**
     * The function constructs a new ScoreIndicator object.
     *
     * @param counter
     */
    public ScoreIndicator(Counter counter) {
        this.scoreTrackingListener = new ScoreTrackingListener(counter);
    }

    /**
     * The function draws the score on the given DrawSurface.
     *
     * @param d
     */
    @Override
    public void drawOn(DrawSurface d) {
        int x = 390, y = 20, size = 15;
        String text = "Score: "
                + this.scoreTrackingListener.getCurrentScore().getValue();

        d.drawText(x, y, text, size);
    }

    /**
     * The function calls timePassed() on all sprites.
     */
    @Override
    public void timePassed() {

    }
}
