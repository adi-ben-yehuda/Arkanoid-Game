package game;

import collidable_and_sprites.Block;
import collision_detection.HitListener;
import different_sprites.Ball;

/**
 * @author Adi Ben Yehuda 211769757
 * @since 2022-05-12
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    /**
     * The function constructs a new block-remover object.
     * A BlockRemover is in charge of removing blocks from the game,
     * as well as keeping count of the number of blocks that remain.
     *
     * @param game
     * @param removedBlocks
     */
    public BlockRemover(Game game, Counter removedBlocks) {
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
        game.removeCollidable(beingHit);
        game.removeSprite(beingHit);

        // Decrease the number of blocks.
        this.remainingBlocks.decrease(1);
    }
}
