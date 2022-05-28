package levels;

import collidable_and_sprites.Block;
import collision_detection.Velocity;
import different_sprites.Ball;
import different_sprites.Sprite;
import game.LevelInformation;
import geometry_primitives.Point;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Adi Ben Yehuda 211769757
 * @since 2022-05-28
 */
public class FirstLevel implements LevelInformation {
    private int numberOfBalls;
    private String levelName;
    private List<Block> blocks;
    private List<Ball> balls;
    private int numberOfBlocksToRemove;

    /**
     * The function constructs a new FirstLevel.
     *
     * @param levelName
     * @param numberOfBalls
     */
    public FirstLevel(int numberOfBalls, String levelName) {
        this.numberOfBalls = numberOfBalls;
        this.levelName = levelName;
        this.blocks = new ArrayList<>();
        this.balls = new ArrayList<>();
        this.numberOfBlocksToRemove = 0;
    }

    /**
     * The function returns the number of balls that exist in the level.
     *
     * @return the number of balls
     */
    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    /**
     * The function initializes the initial velocity of each ball.
     *
     * @return list of velocities.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        int increaseVelocityBy = 5;
        List<Velocity> velocities = new ArrayList<>();

        for (int i = 0; i < numberOfBalls; i++) {
            velocities.add(new Velocity((i + 1) * increaseVelocityBy,
                    (i + 1) * increaseVelocityBy));
        }

        return velocities;
    }

    /**
     * The function returns the paddle speed.
     *
     * @return the paddle speed.
     */
    @Override
    public int paddleSpeed() {
        return 7;
    }

    /**
     * The function returns the paddle width.
     *
     * @return the paddle width.
     */
    @Override
    public int paddleWidth() {
        return 130;
    }

    /**
     * The function returns the level name displayed at the top of the screen.
     *
     * @return the level name
     */
    @Override
    public String levelName() {
        return this.levelName;
    }

    @Override
    public Sprite getBackground() {
        int xBlock = 400, yBlock = 120, blockSize = 35;

        Block block = new Block(new Point(xBlock, yBlock), blockSize,
                blockSize, Color.YELLOW);

        return block;
    }

    /**
     * The function initializes the blocks that make up this level,
     * each block contains its size, color and location.
     *
     * @return a list of blocks.
     */
    @Override
    public List<Block> blocks() {
        int xBlock = 400, yBlock = 120, blockSize = 35;

        this.blocks.add(new Block(new Point(xBlock, yBlock), blockSize,
                blockSize, Color.YELLOW));
        this.numberOfBlocksToRemove++;
        return this.blocks;
    }

    /**
     * The function returns the number of blocks that should be removed.
     *
     * @return the number of blocks that should be removed.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }
}
