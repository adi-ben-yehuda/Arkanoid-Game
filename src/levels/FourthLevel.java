package levels;

import biuoop.DrawSurface;
import collidable_and_sprites.Block;
import collision_detection.Velocity;
import different_sprites.Sprite;
import game.LevelInformation;
import geometry_primitives.Point;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FourthLevel implements LevelInformation {
    private int numberOfBalls;
    private List<Block> blocks;

    /**
     * The function constructs a new FirstLevel.
     */
    public FourthLevel() {
        this.numberOfBalls = 3;
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
        int increaseVelocityBy = 3;
        List<Velocity> velocities = new ArrayList<>();

        for (int i = 0; i < numberOfBalls; i++) {
            velocities.add(new Velocity(i + increaseVelocityBy,
                    i + increaseVelocityBy));
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
        return 9;
    }

    /**
     * The function returns the paddle width.
     *
     * @return the paddle width.
     */
    @Override
    public int paddleWidth() {
        return 140;
    }

    /**
     * The function returns the level name displayed at the top of the screen.
     *
     * @return the level name
     */
    @Override
    public String levelName() {
        return "The daily sentence";
    }

    @Override
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                int sizeText = 25;

                d.setColor(Color.BLACK);
                d.drawText(50, 80, "The daily sentence is :" +
                        " (Hit all the blocks to know)", sizeText);
                d.setColor(Color.magenta);
                d.drawText(100, 139, "Never give up", sizeText);
                d.setColor(Color.orange);
                d.drawText(150, 185, "because great things", sizeText);
                d.setColor(Color.CYAN);
                d.drawText(230, 235, "take time", sizeText);
            }

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
        int blockWidth = 51, startX = 735, blockHeight = 23,
                blocksInRow = 15, startY = 98, numberOfRows = 7;
        Color[] colors = new Color[7];

        // Define colors.
        colors[0] = Color.red;
        colors[1] = Color.magenta;
        colors[2] = Color.PINK;
        colors[3] = Color.orange;
        colors[4] = Color.yellow;
        colors[5] = Color.CYAN;
        colors[6] = Color.green;

        // Define the blocks.
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < blocksInRow * blockWidth; j += blockWidth) {
                blocks.add(new Block(new Point(startX - j,
                        startY + i * blockHeight), blockWidth, blockHeight,
                        colors[i]));
            }
        }

        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 105;
    }
}
