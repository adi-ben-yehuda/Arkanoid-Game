package game;

import collidable_and_sprites.Block;
import collision_detection.HitListener;
import different_sprites.Ball;

public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }

    public Counter getCurrentScore() {
        return currentScore;
    }
}
