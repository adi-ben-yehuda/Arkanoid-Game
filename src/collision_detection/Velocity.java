package collision_detection;

import geometry_primitives.Point;

/**
 * @author Adi Ben Yehuda 
 * @since 2022-03-24
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * The function constructs a new velocity-type object.
     *
     * @param dx
     * @param dy
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * The function returns the dx.
     *
     * @return the dx.
     */
    public double getDx() {
        return dx;
    }

    /**
     * The function returns the dy.
     *
     * @return the dy.
     */
    public double getDy() {
        return dy;
    }

    /**
     * The function updates the dx of the velocity.
     *
     * @param dx
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * The function updates the dy of the velocity.
     *
     * @param dy
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * The function converts the angle and speed to x and y and creates a new
     * velocity.
     *
     * @param angle
     * @param speed
     * @return a new velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = speed * Math.cos(Math.toRadians(angle));
        return new Velocity(dx, -dy);
    }

    /**
     * The function Take a point with position (x,y) and return a new point
     * with position (x+dx, y+dy).
     *
     * @param p
     * @return a new point with position (x+dx, y+dy)
     */
    public Point applyToPoint(Point p) {
        return new Point(dx + p.getX(), dy + p.getY());
    }
}
