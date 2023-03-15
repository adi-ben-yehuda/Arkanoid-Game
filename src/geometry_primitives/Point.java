package geometry_primitives;

/**
 * @author Adi Ben Yehuda 
 * @since 2022-03-24
 */
public class Point {
    private double x;
    private double y;

    /**
     * The function constructs a new point-type object.
     *
     * @param x
     * @param y
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * The function calculates the distance of this point to the other point.
     *
     * @param other
     * @return the distance of this point to the other point.
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow(x - other.getX(), 2)
                + Math.pow(y - other.getY(), 2));
    }

    /**
     * The function checks if the points are equal.
     *
     * @param other
     * @return true if the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        if (x == other.getX() && y == other.getY()) {
            return true;
        }
        return false;
    }

    /**
     * The function returns the x value of this point.
     *
     * @return the x value of this point.
     */
    public double getX() {
        return x;
    }

    /**
     * The function returns the y value of this point.
     *
     * @return the y value of this point.
     */
    public double getY() {
        return y;
    }

    /**
     * The function updates the x value of this point.
     *
     * @param x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * The function updates the y value of this point.
     *
     * @param y
     */
    public void setY(double y) {
        this.y = y;
    }
}
