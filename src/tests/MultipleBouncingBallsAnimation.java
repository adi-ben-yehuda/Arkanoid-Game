package tests;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import collision_detection.Velocity;
import different_sprites.Ball;

import java.awt.Color;
import java.util.Random;

/**
 * @author Adi Ben Yehuda 211769757
 * @since 2022-03-24
 */
public class MultipleBouncingBallsAnimation {
    private static final int WIDTH = 200;
    private static final int HEIGHT = 200;
    private static final int SLEEP_TIME = 10;
    private static final int MAX_SIZE_FOR_VELOCITY = 50;
    private static final int MAX_ANGLE = 359;
    private static final int LEFT_WIDTH = 0;
    private static final int TOP_HEIGHT = 0;
    private static Random rand = new Random();

    /**
     * The function draws all the balls from the array on the surface.
     *
     * @param ballsArr an array of balls
     */
    private static void drawAnimation(Ball[] ballsArr) {
        DrawSurface d = null;
        GUI gui = new GUI("title", WIDTH, HEIGHT);
        Sleeper sleeper = new Sleeper();

        while (true) {
            d = gui.getDrawSurface();
            // Moving each ball from the array one step on the surface.
            for (int i = 0; i < ballsArr.length; i++) {
                if (ballsArr[i] != null) {
                    ballsArr[i].timePassed();
                    ballsArr[i].drawOn(d);
                }
            }
            gui.show(d);
            sleeper.sleepFor(SLEEP_TIME);  // wait a few milliseconds.
        }
    }

    /**
     * The function updates the velocity of the ball according to his size.
     *
     * @param ball
     */
    public static void updateVelocity(Ball ball) {
        Velocity velocity = null;

        /* All the balls that larger than the maximum size, their velocity
         will be the maximum size. The angel will be determined at random. */
        if (ball.getSize() > MAX_SIZE_FOR_VELOCITY) {
            velocity = Velocity.fromAngleAndSpeed(
                    rand.nextInt(MAX_ANGLE), 1);
        } else {
            // The smaller the ball, the greater its speed.
            velocity = Velocity.fromAngleAndSpeed(
                    rand.nextInt(MAX_ANGLE),
                    MAX_SIZE_FOR_VELOCITY / ball.getSize());
        }
        ball.setVelocity(velocity);
    }

    /**
     * The function initializes balls at a random position, with a random
     * color. The velocity is determined by the size of the ball.
     *
     * @param sizesArr an array of sizes of balls
     * @return an array of balls.
     */
    public static Ball[] initializeBallsArray(String[] sizesArr) {
        float r = 0, g = 0, b = 0;
        int size = 0;
        double x = 0.0, y = 0.0;
        Ball[] ballsArr = new Ball[sizesArr.length];

        // Initialize all the balls.
        for (int i = 0; i < ballsArr.length; i++) {
            size = Integer.parseInt(sizesArr[i]);

            if (size == 0) {
                ballsArr[i] = null;
            } else {
                // Get a random color.
                r = rand.nextFloat();
                g = rand.nextFloat();
                b = rand.nextFloat();

                /* Check if the size of the ball is larger than what can be
                 shown on the surface. */
                if (size >= Math.min(WIDTH, HEIGHT) / 2) {
                    size = (Math.min(WIDTH, HEIGHT) / 2) - 1;
                }

                /* Build a new ball with random location and color. The size is
                determined from the args.*/
                x = rand.nextInt(WIDTH - 2 * size) + size;
                y = rand.nextInt(HEIGHT - 2 * size) + size;
                ballsArr[i] = new Ball(x, y, size, new Color(r, g, b));
                updateVelocity(ballsArr[i]);
            }
        }

        return ballsArr;
    }

    /**
     * The function initializes balls at a random position on the surface, with
     * a random color. The velocity is determined by the size of the ball.
     * Then, shows these balls on the surface.
     *
     * @param args an array of sizes of balls.
     */
    public static void main(String[] args) {
        Ball[] ballsArr = initializeBallsArray(args);
        drawAnimation(ballsArr);
    }
}
