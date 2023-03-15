package collision_detection;

import collidable_and_sprites.Block;
import different_sprites.Ball;

/**
 * @author Adi Ben Yehuda 
 * @since 2022-05-12
 */
public interface HitListener {
    /**
     * The function is called whenever the beingHit object is hit.
     *
     * @param beingHit
     * @param hitter - the Ball that's doing the hitting
     */
    void hitEvent(Block beingHit, Ball hitter);
}
