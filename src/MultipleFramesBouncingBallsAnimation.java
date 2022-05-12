import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.Random;

/**
 * @author Adi Ben Yehuda 211769757
 * @since 2022-03-24
 */
public class MultipleFramesBouncingBallsAnimation {
    private static final int POINT_ONE_IN_FIRST_SPACE = 50;
    private static final int WIDTH_OF_FIRST_SPACE = 450;
    private static final int HEIGHT_OF_FIRST_SPACE = 450;
    private static final int POINT_ONE_IN_SECOND_SPACE = 450;
    private static final int WIDTH_OF_SECOND_SPACE = 150;
    private static final int HEIGHT_OF_SECOND_SPACE = 150;
    private static final int SLEEP_TIME = 10;
    private static final int WIDTH = 700;
    private static final int HEIGHT = 650;

    /**
     * The function draws all the balls from the array on the surface.
     *
     * @param ballsArr
     */
    private static void drawAnimation(Ball[] ballsArr) {
        DrawSurface d = null;
        GUI gui = new GUI("title", WIDTH, HEIGHT);
        Sleeper sleeper = new Sleeper();

        while (true) {
            d = gui.getDrawSurface();
            d.setColor(Color.gray);
            d.fillRectangle(POINT_ONE_IN_FIRST_SPACE, POINT_ONE_IN_FIRST_SPACE,
                    WIDTH_OF_FIRST_SPACE, HEIGHT_OF_FIRST_SPACE);
            d.setColor(Color.yellow);
            d.fillRectangle(POINT_ONE_IN_SECOND_SPACE,
                    POINT_ONE_IN_SECOND_SPACE, WIDTH_OF_SECOND_SPACE,
                    HEIGHT_OF_SECOND_SPACE);

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
     * The function initializes balls at a random position, with a random
     * color. The velocity is determined by the size of the ball.
     *
     * @param sizesArr an array of sizes of balls
     * @return an array of balls.
     */
    public Ball[] initializeBallsArray(String[] sizesArr) {
        float r = 0, g = 0, b = 0;
        int size = 0, minSize = 0;
        double x = 0.0, y = 0.0;

        Ball[] ballsArr = new Ball[sizesArr.length];
        Random rand = new Random();

        // Initialize all the balls.
        for (int i = 0; i < ballsArr.length; i++) {
            size = Integer.parseInt(sizesArr[i]);

            if (size == 0) {
                ballsArr[i] = null;
            } else {
                // First half of the balls.
                if (i < ballsArr.length / 2) {
                    minSize = Math.min(WIDTH_OF_FIRST_SPACE,
                            HEIGHT_OF_FIRST_SPACE) / 2;
                    /* Check if the size of the ball is larger than what can be
                    shown on the surface. */
                    if (size >= minSize) {
                        size = minSize - 1;
                    }

                    x = (rand.nextInt(WIDTH_OF_FIRST_SPACE - 2 * size)
                            + POINT_ONE_IN_FIRST_SPACE) + size;
                    y = (rand.nextInt(HEIGHT_OF_FIRST_SPACE - 2 * size)
                            + POINT_ONE_IN_FIRST_SPACE) + size;
                } else {
                    // Second half of the balls.
                    minSize = Math.min(WIDTH_OF_SECOND_SPACE,
                            HEIGHT_OF_SECOND_SPACE) / 2;
                    /* Check if the size of the ball is larger than what can be
                    shown on the surface. */
                    if (size >= minSize) {
                        size = minSize - 1;
                    }

                    x = (rand.nextInt(WIDTH_OF_SECOND_SPACE - 2 * size)
                            + POINT_ONE_IN_SECOND_SPACE) + size;
                    y = (rand.nextInt(HEIGHT_OF_SECOND_SPACE - 2 * size)
                            + POINT_ONE_IN_SECOND_SPACE) + size;
                }

                // Get a random color.
                r = rand.nextFloat();
                g = rand.nextFloat();
                b = rand.nextFloat();

                /* Build a new ball with random location and color. The size is
                determined from the args.*/
                ballsArr[i] = new Ball(x, y, size, new Color(r, g, b));

                MultipleBouncingBallsAnimation.updateVelocity(ballsArr[i]);
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
        MultipleFramesBouncingBallsAnimation m = new
                MultipleFramesBouncingBallsAnimation();
        Ball[] ballsArr = m.initializeBallsArray(args);
        drawAnimation(ballsArr);
    }
}
