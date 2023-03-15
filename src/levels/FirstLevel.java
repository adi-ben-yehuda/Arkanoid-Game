package levels;

import biuoop.DrawSurface;
import collidable_and_sprites.Block;
import collision_detection.Velocity;
import different_sprites.Sprite;
import game.LevelInformation;
import geometry_primitives.Point;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Adi Ben Yehuda 
 * @since 2022-05-28
 */
public class FirstLevel implements LevelInformation {
    private int numberOfBalls;
    private List<Block> blocks;

    /**
     * The function constructs a new FirstLevel object.
     */
    public FirstLevel() {
        this.numberOfBalls = 1;
        this.blocks = new ArrayList<>();
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
        int dy = -5, dx = 0;
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(new Velocity(dx, dy));
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
        return "Lego man";
    }

    /**
     * The function returns a sprite with the background of the level.
     *
     * @return a sprite with the background of the level.
     */
    @Override
    public Sprite getBackground() {
        return new Sprite() {
            /**
             * The function draws the sprite to the screen.
             *
             * @param d
             */
            @Override
            public void drawOn(DrawSurface d) {
                // Shirt
                d.setColor(Color.red);
                d.fillRectangle(377, 190, 80, 80);
                d.setColor(Color.black);
                // Jeans
                d.setColor(Color.BLUE);
                d.fillRectangle(377, 270, 30, 80);
                d.fillRectangle(427, 270, 30, 80);
                // Hands
                d.setColor(Color.red);
                d.fillRectangle(347, 190, 30, 60);
                d.fillRectangle(457, 190, 30, 60);
                // Fingers
                d.setColor(Color.yellow);
                d.fillCircle(360, 260, 10);
                d.fillCircle(475, 260, 10);

                d.setColor(Color.black);
                // Jeans
                d.drawRectangle(377, 270, 30, 80);
                d.drawRectangle(427, 270, 30, 80);
                // Hands
                d.drawRectangle(347, 190, 30, 60);
                d.drawRectangle(457, 190, 30, 60);
                // Fingers
                d.drawCircle(360, 260, 10);
                d.drawCircle(475, 260, 10);
                // Shirt
                d.drawRectangle(377, 190, 80, 80);
                // Eyes
                d.fillCircle(410, 165, 4);
                d.fillCircle(430, 165, 4);
                // Mouse
                d.fillRectangle(410, 175, 20, 3);
                d.drawRectangle(410, 175, 20, 3);
                // Shoes
                d.drawRectangle(377, 330, 30, 20);
                d.drawRectangle(427, 330, 30, 20);

                // Text
                d.drawText(200, 100, "Hit me", 20);
                d.drawText(180, 125, "in the head", 20);
                d.drawCircle(230, 115, 60);
                d.drawCircle(325, 130, 15);
                d.drawCircle(365, 150, 10);
            }

            /**
             * The function notifies the sprite that time has passed.
             */
            @Override
            public void timePassed() {

            }
        };
    }

    /**
     * The function initializes the blocks that make up this level,
     * each block contains its size, color and location.
     *
     * @return a list of blocks.
     */
    @Override
    public List<Block> blocks() {
        int xBlock = 395, yBlock = 150, blockSize = 50;

        if (blocks.size() == 0) {
            blocks.add(new Block(new Point(xBlock, yBlock), blockSize,
                    blockSize, Color.YELLOW));
        }

        return this.blocks;
    }

    /**
     * The function returns the number of blocks that should be removed.
     *
     * @return the number of blocks that should be removed.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
