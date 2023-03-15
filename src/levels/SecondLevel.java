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
public class SecondLevel implements LevelInformation {
    private int numberOfBalls;
    private List<Block> blocks;

    /**
     * The function constructs a new SecondLevel object.
     */
    public SecondLevel() {
        this.numberOfBalls = 10;
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
        int increaseVelocityBy = 2;
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
        return 600;
    }

    /**
     * The function returns the level name displayed at the top of the screen.
     *
     * @return the level name
     */
    @Override
    public String levelName() {
        return "Play on the road";
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
                int disBetweenCars = 150, xFirstWheel = 65, xSecondWheel = 85,
                        yWheel = 192, sizeWheel = 8, xRoad = 20, yRoad = 200,
                        widthRoad = 760, heightRoad = 1;
                Color[] colors = new Color[5];

                // Define colors.
                colors[0] = Color.red;
                colors[1] = Color.magenta;
                colors[2] = Color.orange;
                colors[3] = Color.yellow;
                colors[4] = Color.CYAN;

                for (int i = 0; i < 5; i++) {
                    d.setColor(colors[i]);
                    // Fill the car
                    d.fillRectangle(50 + disBetweenCars * i, 172, 50,
                            20);

                    d.setColor(Color.black);
                    // Draw the car.
                    d.drawRectangle(50 + disBetweenCars * i, 172, 50,
                            20);
                    // Wheels of the car
                    d.fillCircle(xFirstWheel + disBetweenCars * i, yWheel,
                            sizeWheel);
                    d.fillCircle(xSecondWheel + disBetweenCars * i, yWheel,
                            sizeWheel);
                }

                // Road
                d.fillRectangle(xRoad, yRoad, widthRoad, heightRoad);
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
        int xPoint = 20, yPoint = 200, blockWidth = 51, blockHeight = 20;

        if (blocks.size() == 0) {
            for (int i = 0; i < numberOfBlocksToRemove(); i++) {
                blocks.add(new Block(new Point(xPoint + (i * blockWidth),
                        yPoint), blockWidth, blockHeight, Color.lightGray));
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
        return 15;
    }
}
