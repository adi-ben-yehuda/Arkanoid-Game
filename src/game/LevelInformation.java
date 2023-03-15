package game;

import collidable_and_sprites.Block;
import collision_detection.Velocity;
import different_sprites.Sprite;

import java.util.List;

/**
 * @author Adi Ben Yehuda 
 * @since 2022-05-28
 */
public interface LevelInformation {
    /**
     * The function returns the number of balls that exist in the level.
     *
     * @return the number of balls
     */
    int numberOfBalls();

    /**
     * The function initializes the initial velocity of each ball.
     *
     * @return list of velocities.
     */
    List<Velocity> initialBallVelocities();

    /**
     * The function returns the paddle speed.
     *
     * @return the paddle speed.
     */
    int paddleSpeed();

    /**
     * The function returns the paddle width.
     *
     * @return the paddle width.
     */
    int paddleWidth();

    /**
     * The function returns the level name displayed at the top of the screen.
     *
     * @return the level name
     */
    String levelName();

    /**
     * The function returns a sprite with the background of the level.
     *
     * @return a sprite with the background of the level.
     */
    Sprite getBackground();

    /**
     * The function initializes the blocks that make up this level,
     * each block contains its size, color and location.
     *
     * @return a list of blocks.
     */
    List<Block> blocks();

    /**
     * The function returns the number of blocks that should be removed.
     *
     * @return the number of blocks that should be removed.
     */
    int numberOfBlocksToRemove();
}
