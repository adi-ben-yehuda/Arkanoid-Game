package different_sprites;

import biuoop.DrawSurface;
import game.Counter;
import game.ScoreTrackingListener;

import java.awt.*;

public class ScoreIndicator implements Sprite {
    private ScoreTrackingListener scoreTrackingListener;

    public ScoreIndicator(Counter counter) {
        this.scoreTrackingListener = new ScoreTrackingListener(counter);
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.drawText(390, 20, "Score: "
                + this.scoreTrackingListener.getCurrentScore().getValue(), 15);
    }

    @Override
    public void timePassed() {

    }


}
