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
public class ThirdLevel implements LevelInformation {
    private int numberOfBalls;
    private List<Block> blocks;

    /**
     * The function constructs a new ThirdLevel object.
     */
    public ThirdLevel() {
        this.numberOfBalls = 2;
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
        int increaseVelocityBy = 4;
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
        return "Save the lady";
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
                // Jeans up
                d.setColor(Color.BLUE);
                d.fillRectangle(330, 150, 30, 20);
                // Jeans down
                d.fillRectangle(310, 150, 20, 50);
                // Shoe
                d.setColor(Color.DARK_GRAY);
                d.fillRectangle(290, 200, 40, 20);
                // Shirt
                d.setColor(Color.magenta);
                d.fillRectangle(340, 100, 20, 50);
                // Hands
                d.fillRectangle(310, 100, 30, 20);
                // Head
                d.setColor(Color.pink);
                d.fillCircle(350, 84, 16);
                d.setColor(Color.black);
                // Hair
                d.setColor(Color.yellow);
                d.fillRectangle(366, 67, 20, 55);
                d.fillRectangle(326, 59, 60, 10);
                d.fillRectangle(326, 69, 10, 5);

                d.setColor(Color.black);

                // Eye
                d.fillCircle(347, 80, 3);
                // Mouse
                d.fillRectangle(340, 90, 8, 1);

                d.setColor(Color.black);
                // Jeans up
                d.drawRectangle(330, 150, 30, 20);
                // Jeans down
                d.drawRectangle(310, 150, 20, 50);
                // Shoe
                d.drawRectangle(290, 200, 40, 20);
                // Shirt
                d.drawRectangle(340, 100, 20, 50);
                // Hands
                d.drawRectangle(310, 100, 30, 20);
                // Head
                d.drawCircle(350, 84, 16);

                // Lamppost
                d.fillRectangle(80, 250, 20, 350);
                // Lamp
                d.setColor(Color.orange);
                d.fillCircle(87, 220, 30);

                // Monkey
                d.setColor(new Color(201, 141, 38)); // Brown
                // Left hand
                d.fillRectangle(100, 300, 20, 20);
                d.fillRectangle(120, 320, 20, 20);
                // Body
                d.fillRectangle(140, 340, 50, 60);
                // Left leg
                d.fillRectangle(120, 400, 20, 20);
                d.fillRectangle(100, 420, 20, 20);
                // Right leg
                d.fillRectangle(170, 400, 20, 20);
                d.fillRectangle(150, 420, 20, 20);
                // Left hand
                d.fillRectangle(190, 340, 20, 20);
                d.fillRectangle(210, 340, 20, 20);
                // Head
                d.fillCircle(170, 320, 20);
                // Eyes
                d.setColor(Color.black);
                d.fillCircle(162, 316, 4);
                d.fillCircle(178, 316, 4);
                // Mouse
                d.fillRectangle(164, 328, 13, 2);

                // Monkey
                d.setColor(Color.black);
                // Left hand
                d.drawRectangle(100, 300, 20, 20);
                d.drawRectangle(120, 320, 20, 20);
                // Body
                d.drawRectangle(140, 340, 50, 60);
                // Left leg
                d.drawRectangle(120, 400, 20, 20);
                d.drawRectangle(100, 420, 20, 20);
                // Right leg
                d.drawRectangle(170, 400, 20, 20);
                d.drawRectangle(150, 420, 20, 20);
                // Left hand
                d.drawRectangle(190, 340, 20, 20);
                d.drawRectangle(210, 340, 20, 20);
                // Head
                d.drawCircle(170, 320, 20);

                // Text
                d.drawText(260, 290, "I'm", 20);
                d.drawText(243, 305, "coming", 20);
                d.drawCircle(273, 292, 40);
                d.drawCircle(212, 310, 10);
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
        int blockWidth = 45, startX = 735, blockHeight = 23,
                blocksInRow = 10, startY = 170, numberOfRows = 5;

        Color[] colors = new Color[5];

        // Define colors.
        colors[0] = Color.red;
        colors[1] = Color.magenta;
        colors[2] = Color.orange;
        colors[3] = Color.yellow;
        colors[4] = Color.CYAN;

        // Define the blocks.
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < (blocksInRow - i) * blockWidth; j += blockWidth) {
                blocks.add(new Block(new Point(startX - j,
                        startY + i * blockHeight), blockWidth, blockHeight,
                        colors[i]));
            }
        }

        return blocks;
    }

    /**
     * The function returns the number of blocks that should be removed.
     *
     * @return the number of blocks that should be removed.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }
}
