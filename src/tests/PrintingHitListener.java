package tests;

import collidable_and_sprites.Block;
import collision_detection.HitListener;
import different_sprites.Ball;

/**
 * @author Adi Ben Yehuda 211769757
 * @since 2022-05-12
 */
public class PrintingHitListener implements HitListener {

    /**
     * The function prints a message when the ball hits a block.
     *
     * @param beingHit
     * @param hitter
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block was hit.");
    }
}
