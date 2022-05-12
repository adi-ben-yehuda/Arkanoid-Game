import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * @author Adi Ben Yehuda 211769757
 * @since 2022-03-24
 */
public class BouncingBallAnimation {
    private static final int WIDTH = 200;
    private static final int HEIGHT = 200;
    private static final int SIZE = 30;
    private static final int SLEEP_TIME = 50;
    private static final int ARRAY_SIZE = 4;

    /**
     * The function draws the point and moves it inside the surface.
     *
     * @param start
     * @param dx
     * @param dy
     */
    private static void drawAnimation(Point start, double dx, double dy) {
        DrawSurface d = null;
        GUI gui = new GUI("title", WIDTH, HEIGHT);
        Sleeper sleeper = new Sleeper();
        Ball ball = new Ball(start.getX(), start.getY(), SIZE,
                java.awt.Color.BLACK);
        ball.setVelocity(dx, dy);

        while (true) {
            ball.timePassed();
            d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(SLEEP_TIME);  // wait a few milliseconds.
        }
    }

    /**
     * The function draws the point it receives from the args and moves it to
     * the surface.
     *
     * @param args
     */
    public static void main(String[] args) {
        double x = 0.0, y = 0.0;

        if (args.length == ARRAY_SIZE) {
            x = Double.parseDouble(args[0]);
            y = Double.parseDouble(args[1]);

            // Check if the ball is positioned off the surface.
            if (x < SIZE) {
                x = SIZE;
            } else if (x > WIDTH - SIZE) {
                x = WIDTH - SIZE;
            }
            if (y < SIZE) {
                y = SIZE;
            } else if (y > HEIGHT - SIZE) {
                y = HEIGHT - SIZE;
            }

            drawAnimation(new Point(x, y), Integer.parseInt(args[2]),
                    Integer.parseInt(args[3]));
        }
    }
}
