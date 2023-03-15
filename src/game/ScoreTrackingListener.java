package game;

import collidable_and_sprites.Block;
import collision_detection.HitListener;
import different_sprites.Ball;

/**
 * @author Adi Ben Yehuda 
 * @since 2022-05-14
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * The function constructs a new ScoreTrackingListener.
     *
     * @param scoreCounter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * The function adds 5 to the score.
     *
     * @param beingHit
     * @param hitter
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }

    /**
     * The function returns the current score.
     *
     * @return the current score.
     */
    public Counter getCurrentScore() {
        return currentScore;
    }
}
