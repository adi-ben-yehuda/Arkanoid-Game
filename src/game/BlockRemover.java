package game;

import collidable_and_sprites.Block;
import collision_detection.HitListener;
import different_sprites.Ball;

/**
 * @author Adi Ben Yehuda 211769757
 * @since 2022-05-12
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * The function constructs a new block-remover object.
     * A BlockRemover is in charge of removing blocks from the game,
     * as well as keeping count of the number of blocks that remain.
     *
     * @param game
     * @param removedBlocks
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * The function removes from the game the block that are hit.
     * In addition, removes this listener from the block that is being removed
     * from the game.
     *
     * @param beingHit
     * @param hitter
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        /* Remove this listener from the block that is being removed from the
         game. */
        beingHit.getHitListeners().remove(this);

        // Remove from the game the block that are hit.
        beingHit.removeFromGame(game);

        // Decrease the number of blocks.
        this.remainingBlocks.decrease(1);

        // When there are no more blocks in the game, add 100 points to the score.
        if (this.remainingBlocks.getValue() == 0) {
            this.game.setScore(100);
        }
    }
}
