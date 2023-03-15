package tests;

import biuoop.DrawSurface;
import biuoop.GUI;
import geometry_primitives.Line;
import geometry_primitives.Point;

import java.awt.Color;
import java.util.Random;

/**
 * @author Adi Ben Yehuda
 * @since 2022-03-24
 */
public class AbstractArtDrawing {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private static final int SIZE = 3;
    private static final int MAX_NUMBER_OF_LINES = 30;

    private static Random rand = new Random();

    /**
     * The function creates four locations at random and creates a new line
     * with these locations.
     *
     * @return A line with a random location to its points.
     */
    public Line generateRandomLine() {
        double x1 = rand.nextInt(WIDTH) + 1;
        double y1 = rand.nextInt(HEIGHT) + 1;
        double x2 = rand.nextInt(WIDTH) + 1;
        double y2 = rand.nextInt(HEIGHT) + 1;

        return new Line(x1, y1, x2, y2);
    }

    /**
     * The function draws the line on the surface in black.
     *
     * @param l
     * @param d
     */
    public void drawLine(Line l, DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawLine((int) l.start().getX(), (int) l.start().getY(),
                (int) l.end().getX(), (int) l.end().getY());
    }

    /**
     * The function draws the middle point of the line on the surface in blue.
     *
     * @param l
     * @param d
     */
    public void drawMiddleCircle(Line l, DrawSurface d) {
        d.setColor(Color.BLUE);
        Point p = l.middle();
        d.fillCircle((int) p.getX(), (int) p.getY(), SIZE);
    }

    /**
     * The function draws the intersection point of two lines on the surface in
     * red.
     *
     * @param l1
     * @param l2
     * @param d
     */
    public void drawIntersectionCircle(Line l1, Line l2, DrawSurface d) {
        d.setColor(Color.red);
        Point p = l1.intersectionWith(l2);
        if (p != null) {
            d.fillCircle((int) p.getX(), (int) p.getY(), SIZE);
        }
    }

    /**
     * The function draws a random number of lines, their midpoints and points
     * of intersection on the surface.
     *
     * @param args
     */
    public static void main(String[] args) {
        AbstractArtDrawing abstractArtDrawing = new AbstractArtDrawing();
        GUI gui = new GUI("Random Circles Example", WIDTH, HEIGHT);
        DrawSurface d = gui.getDrawSurface();
        Line[] linesArr = new Line[rand.nextInt(MAX_NUMBER_OF_LINES) + 10];

        // Initialize the lines array.
        for (int i = 0; i < linesArr.length; i++) {
            // Create a random line.
            linesArr[i] = abstractArtDrawing.generateRandomLine();

            // Check if the points of the line is equal.
            while ((linesArr[i].start().getX() == linesArr[i].end().getX())
                    && (linesArr[i].start().getY() == linesArr[i].end().getY())
            ) {
                // Generate another random line.
                linesArr[i] = abstractArtDrawing.generateRandomLine();
            }
            // Draw the line
            abstractArtDrawing.drawLine(linesArr[i], d);
            // Draw the middle point of the line.
            abstractArtDrawing.drawMiddleCircle(linesArr[i], d);
        }

        for (int i = 0; i < linesArr.length; i++) {
            for (int j = i + 1; j < linesArr.length; j++) {
                /* Check if there is a point of intersection between the line
                 and all other lines. */
                abstractArtDrawing.drawIntersectionCircle(linesArr[i],
                        linesArr[j], d);
            }
        }

        gui.show(d);
    }
}
