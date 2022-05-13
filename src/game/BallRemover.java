package game;

import collidable_and_sprites.Block;
import collision_detection.HitListener;
import different_sprites.Ball;

/**
 * @author Adi Ben Yehuda 211769757
 * @since 2022-04-14
 */
public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;

    /**
     * The function constructs a new BallRemover object.
     * A BallRemover is in charge of removing balls from the game,
     * as well as keeping count of the number of balls that remain.
     *
     * @param game
     * @param remainingBalls
     */
    public BallRemover(Game game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * The function removes from the game the ball that hit the bottom block.
     * In addition, removes this listener from the ball that is being removed
     * from the game.
     *
     * @param beingHit
     * @param hitter
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        /* Remove this listener from the ball that is being removed from the
         game. */
        hitter.getHitListeners().remove(this);

        // Remove from the game the ball that hit the bottom block.
        hitter.removeFromGame(game);

        // Decrease the number of balls.
        this.remainingBalls.decrease(1);
    }
}
