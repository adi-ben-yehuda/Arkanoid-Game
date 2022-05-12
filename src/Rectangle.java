import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Adi Ben Yehuda 211769757
 * @since 2022-04-03
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    private Color color;

    /**
     * The function constructs a new rectangle with location and width/height.
     *
     * @param upperLeft
     * @param width
     * @param height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * The function constructs a new rectangle.
     *
     * @param upperLeft
     * @param width
     * @param height
     * @param color
     */
    public Rectangle(Point upperLeft, double width, double height,
                     Color color) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    /**
     * The function adds the point to the list if the point does not exist in
     * the list.
     *
     * @param pointList list of points
     * @param point
     */
    private void addPointToList(List<Point> pointList, Point point) {
        boolean check = false;

        if (point != null) {
            for (int i = 0; i < pointList.size(); i++) {
                // Check if the point exists in the list.
                if (pointList.get(i).equals(point)) {
                    check = true;
                    break;
                }
            }

            // Meaning, the point does not exist in the list.
            if (!check) {
                pointList.add(point);
            }
        }
    }

    /**
     * The function calculates the intersection points between the rectangle
     * and the line.
     *
     * @param line
     * @return a (possibly empty) List of intersection points with the
     * specified line.
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> pointList = new ArrayList<>();
        Point point;

        /* Calculating the intersection points between the upper line of the
         rectangle and the line. */
        Point upperRight = new Point(upperLeft.getX() + width,
                upperLeft.getY());
        Line upperLine = new Line(upperLeft, upperRight);
        point = upperLine.intersectionWith(line);
        addPointToList(pointList, point);

        /* Calculating the intersection points between the left line of the
         rectangle and the line. */
        Point bottomLeft = new Point(upperLeft.getX(),
                upperLeft.getY() + height);
        Line leftLine = new Line(bottomLeft, upperLeft);
        point = leftLine.intersectionWith(line);
        addPointToList(pointList, point);

        /* Calculating the intersection points between the bottom line of the
         rectangle and the line. */
        Point bottomRight = new Point(bottomLeft.getX() + width,
                bottomLeft.getY());
        Line bottomLine = new Line(bottomRight, bottomLeft);
        point = bottomLine.intersectionWith(line);
        addPointToList(pointList, point);

        /* Calculating the intersection points between the right line of the
         rectangle and the line. */
        Line rightLine = new Line(bottomRight, upperRight);
        point = rightLine.intersectionWith(line);
        addPointToList(pointList, point);

        return pointList;
    }

    /**
     * The function returns the width of the rectangle.
     *
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return width;
    }

    /**
     * The function returns the height of the rectangle.
     *
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return height;
    }

    /**
     * The function returns the upper-left point of the rectangle.
     *
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return upperLeft;
    }

    /**
     * The function returns the color of the rectangle.
     *
     * @return the color of the rectangle.
     */
    public Color getColor() {
        return color;
    }
}
