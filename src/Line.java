import java.util.List;

/**
 * @author Adi Ben Yehuda 211769757
 * @since 2022-03-24
 */
public class Line {
    private Point start;
    private Point end;

    /**
     * The function constructs a new line-type object.
     *
     * @param start
     * @param end
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * The function constructs a new line-type object.
     *
     * @param x1 The x of the first point.
     * @param y1 The y of the first point.
     * @param x2 The x of the second point.
     * @param y2 The y of the second point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        start = new Point(x1, y1);
        end = new Point(x2, y2);
    }

    /**
     * The function checks if point q lies on line segment 'pr'.
     *
     * @param p
     * @param q
     * @param r
     * @return true if point q lies on line segment 'pr', false otherwise
     */
    private boolean onSegment(Point p, Point q, Point r) {
        if (q.getX() <= Math.max(p.getX(), r.getX())
                && q.getX() >= Math.min(p.getX(), r.getX())
                && q.getY() <= Math.max(p.getY(), r.getY())
                && q.getY() >= Math.min(p.getY(), r.getY())) {
            return true;
        }

        return false;
    }

    /**
     * The function finds orientation of ordered triplet (p, q, r).
     *
     * @param p
     * @param q
     * @param r
     * @return 0 if p, q and r are collinear, 1 if Clockwise and 2 if
     * Counterclockwise.
     */
    private int orientation(Point p, Point q, Point r) {
        // Calculating the orientation
        int val = (int) ((q.getY() - p.getY()) * (r.getX() - q.getX())
                - (q.getX() - p.getX()) * (r.getY() - q.getY()));

        if (val == 0) {
            return 0; // collinear
        }
        return (val > 0) ? 1 : 2; // clock or counterclockwise
    }

    /**
     * The function calculates the line equation for the both lines.
     *
     * @param other
     * @return an array that include - in the first cell: 1 if the equations
     * are equal, 0 otherwise. The other cells include the slope and
     * intersection point with Y-axis of each line respectively.
     */
    private double[] lineEquation(Line other) {
        int arrSize = 5;
        double m1 = 0.0, m2 = 0.0; // The slope of the two lines.
        // The point of intersection of the line with the Y-axis.
        double b1 = 0.0, b2 = 0.0;
        double[] arr = new double[arrSize];

        /* Calculating the slope and intersection point with Y-axis of the
             first line. */
        m1 = (start.getY() - end.getY()) / (start.getX() - end.getX());
        b1 = start.getY() - m1 * start.getX();

            /* Calculating the slope and intersection point with Y-axis of the
             second line. */
        m2 = (other.start.getY() - other.end.getY())
                / (other.start.getX() - other.end.getX());
        b2 = other.start.getY() - m2 * other.start.getX();

        if (m1 == m2 && b1 == b2) {
            arr[0] = 1; // Meaning the equations are equal.
        } else {
            arr[0] = 0; // Meaning the equations are different.
        }

        arr[1] = m1;
        arr[2] = b1;
        arr[3] = m2;
        arr[4] = b2;

        return arr;
    }

    /**
     * The function checks if lines with same equation are foreign.
     *
     * @param other
     * @return true if the lines are foreign, false otherwise.
     */
    private boolean foreignLines(Line other) {
        if (((start.getY() > other.start.getY()
                && start.getY() > other.end.getY())
                && (end.getY() > other.start.getY()
                && end.getY() > other.end.getY()))
                || (start.getY() < other.start.getY()
                && start.getY() < other.end.getY())
                && (end.getY() < other.start.getY()
                && end.getY() < other.end.getY())) {
            return true;
        }
        return false;
    }

    /**
     * The function checks if one of the lines (part or all of the line) is
     * included in the second line.
     *
     * @param other
     * @return true if there is overlapping section between the lines, false
     * otherwise.
     */
    private boolean pointBetweenTwoLines(Line other) {
        /* Checking if the start point is located on other the line, between
         the start and end point of the line. */
        if ((start.getX() > other.start.getX()
                && start.getX() < other.end.getX())
                || (start.getX() < other.start.getX()
                && start.getX() > other.end.getX())
                && ((start.getY() > other.start.getY()
                && start.getY() < other.end.getY())
                || (start.getY() < other.start.getY()
                && start.getY() > other.end.getY()))) {
            return true;
        } else if ((end.getX() > other.start.getX()
                && end.getX() < other.end.getX())
                || (end.getX() < other.start.getX()
                && end.getX() > other.end.getX())
                && ((end.getY() > other.start.getY()
                && end.getY() < other.end.getY())
                || (end.getY() < other.start.getY()
                && end.getY() > other.end.getY()))) {
            /* Checking if the end point is located on the other line, between
             the start and end point of the line. */
            return true;
        }

        return false;
    }

    /**
     * The function checks if two lines with the same equation, have one
     * intersection point.
     *
     * @param other
     * @return the intersection point, null if there is no intersection point.
     */
    private Point samePoint(Line other) {
        /* Check if the starting point is equal to the starting point of the
         other line. Also check that the end point of the two lines is located
         to the right and left of the equal point. */
        if ((start.getX() == other.start.getX()
                && start.getY() == other.start.getY()) // same point
                && ((start.getY() > other.end.getY()
                && end.getY() > start.getY())
                || (start.getY() < other.end.getY()
                && end.getY() < start.getY()))) {
            return start;
        }

         /* Check if the starting point is equal to the ending point of the
         other line. Also check that the end point of the two lines is located
         to the right and left of the equal point. */
        if ((start.getX() == other.end.getX()
                && start.getY() == other.end.getY()) // same point
                && ((start.getY() > other.end.getY()
                && end.getY() > start.getY())
                || (start.getY() < other.start.getY()
                && end.getY() < start.getY()))) {
            return start;
        } else if ((end.getX() == other.end.getX()
                && end.getY() == other.end.getY()) // same point
                && ((end.getY() > other.start.getY()
                && start.getY() > end.getY())
                || (end.getY() < other.start.getY()
                && start.getY() < end.getY()))) {
            /* Check if the ending point is equal to the ending point of the
             other line. Also check that the start point of the two lines is
              located to the right and left of the equal point. */
            return end;
        } else if ((end.getX() == other.start.getX()
                && end.getY() == other.start.getY()) // same point
                && ((end.getY() > other.end.getY()
                && start.getY() > end.getY())
                || ((end.getY() < other.end.getY()
                && start.getY() < end.getY())))) {
            /* Check if the ending point is equal to the starting point of the
            other line. Also check that the start point of the two lines is
             located to the right and left of the equal point. */
            return end;
        }

        return null;
    }

    /**
     * The function calculates the length of the line.
     *
     * @return the length of the line.
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * The function calculates the middle point of the line.
     *
     * @return the middle point of the line.
     */
    public Point middle() {
        double x = (start.getX() + end.getX()) / 2;
        double y = (start.getY() + end.getY()) / 2;
        return new Point(x, y);
    }

    /**
     * The function returns the start point of the line.
     *
     * @return the start point of the line.
     */
    public Point start() {
        return start;
    }

    /**
     * The function returns the end point of the line.
     *
     * @return the end point of the line.
     */
    public Point end() {
        return end;
    }

    /**
     * The function checks if the lines intersect.
     *
     * @param other
     * @return true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        // Check if both lines have the same equation.
        if (lineEquation(other)[0] == 1) {
            if (foreignLines(other)) {
                return false; // The lines are foreign.
            }
            return true; // The lines aren't foreign.
        } else {
            // Find the four orientations needed for general and special cases.
            int firstOrientation = orientation(start, end, other.start());
            int secondOrientation = orientation(start, end, other.end());
            int thirdOrientation = orientation(other.start(), other.end(),
                    start);
            int fourthOrientation = orientation(other.start(), other.end(),
                    end);

            // General case
            if ((firstOrientation != secondOrientation)
                    && (thirdOrientation != fourthOrientation)) {
                return true;
            }

            // Special Cases

            /* Start, end and start of the other line are collinear and the
            start of the other line lies on our line. */
            if ((firstOrientation == 0)
                    && (onSegment(start, other.start(), end))) {
                return true;
            }

            /* Start and the other line of the other line are collinear and end
             of the other line lies on our line. */
            if ((secondOrientation == 0)
                    && (onSegment(start, other.end(), end))) {
                return true;
            }

            /* End, start and end of the other line are collinear and start
            lies on the other line. */
            if ((thirdOrientation == 0)
                    && (onSegment(other.start(), start, other.end()))) {
                return true;
            }

            /* End and the other line are collinear and start of the other line
             lies on the other line. */
            if ((fourthOrientation == 0)
                    && (onSegment(other.start(), end, other.end()))) {
                return true;
            }

            return false; // Doesn't fall in any of the above cases
        }
    }

    /**
     * The function calculates the intersection point of two lines.
     *
     * @param other
     * @return the intersection point if the lines intersect, and null
     * otherwise.
     */
    public Point intersectionWith(Line other) {
        double x = 0.0, y = 0.0; // The x and y of the intersection point.
        double[] arr = lineEquation(other);

        // Checking if the lines intersect.
        if (isIntersecting(other)) {
            // The lines have a different equation line.
            if (arr[0] == 0) {
                // One slope is 0 and the second is 0 or infinity.
                if ((arr[1] == 0)
                        && (arr[3] == 0
                        || other.start.getX() == other.end.getX())) {
                    x = other.start.getX();
                    y = start.getY();
                } else if ((arr[3] == 0)
                        && (arr[1] == 0 || start.getX() == end.getX())) {
                    // One slope is 0 and the second is 0 or infinity.
                    x = start.getX();
                    y = other.start.getY();
                } else if ((other.start.getX() == other.end.getX())
                        && (start.getX() == end.getX())) {
                    // Both slopes are infinity.
                    return samePoint(other);
                } else if ((other.start.getX() == other.end.getX())
                        && (start.getX() != end.getX())) {
                    // One slope is infinity and the other one is a number.
                    x = other.start.getX();
                    y = arr[1] * x + arr[2];
                } else if ((start.getX() == end.getX())
                        && (other.start.getX() != other.end.getX())) {
                    // One slope is infinity and the other one is a number.
                    x = start.getX();
                    y = arr[3] * x + arr[4];
                } else {
                    // Calculating the x and y of the intersection point.
                    x = (arr[4] - arr[2]) / (arr[1] - arr[3]);
                    y = arr[1] * ((arr[4] - arr[2]) / (arr[1] - arr[3]))
                            + arr[2];
                }

                return new Point(x, y);
            } else {
                // The lines have a same equation line.

                /* One of the lines (part or all of the line) is included in
                 the second line. */
                if (pointBetweenTwoLines(other)) {
                    return null;
                } else {
                    // Checking if the lines have one intersection point.
                    return samePoint(other);
                }
            }
        }

        return null; // If there isn't an intersection point.
    }

    /**
     * The function checks if the lines are equal.
     *
     * @param other
     * @return true if the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        if ((start.equals(other.start()) && end.equals(other.end()))
                || (start.equals(other.end()) && end.equals(other.start()))) {
            return true;
        }
        return false;
    }

    /**
     * The function checks if this line does not intersect with the rectangle,
     * return null. Otherwise, return the closest intersection point to the
     * start of the line.
     *
     * @param rect
     * @return null if the line does not intersect with the rectangle.
     * Otherwise, the closest intersection point.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        double min = -1, distance = 0.0;
        Point closetPoint = new Point(-1, -1);
        List<Point> pointList = rect.intersectionPoints(new Line(this.start,
                this.end));

        /* Check what is the closest intersection point to the start of the
         line. */
        for (int i = 0; i < pointList.size(); i++) {
            distance = pointList.get(i).distance(start);

            // This is the first time.
            if (min == -1) {
                min = distance;
                closetPoint.setX(pointList.get(i).getX());
                closetPoint.setY(pointList.get(i).getY());
            } else {
                // That is, there is a point closer to the starting point.
                if (distance < min) {
                    min = distance;
                    closetPoint.setX(pointList.get(i).getX());
                    closetPoint.setY(pointList.get(i).getY());
                }
            }
        }

        // If the line does not intersect with the rectangle.
        if (closetPoint.getX() == -1) {
            return null;
        }

        return closetPoint;
    }
}