package different_sprites;

import biuoop.DrawSurface;
import collision_detection.CollisionInfo;
import collision_detection.HitListener;
import collision_detection.HitNotifier;
import collision_detection.Velocity;
import game.Game;
import game.GameEnvironment;
import geometry_primitives.Line;
import geometry_primitives.Point;
import geometry_primitives.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Adi Ben Yehuda 211769757
 * @since 2022-03-24
 */
public class Ball implements Sprite, HitNotifier {
    private Point center;
    private int size;
    private Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;
    private List<HitListener> hitListeners;

    /**
     * The function constructs a new ball-type object.
     *
     * @param center
     * @param r
     * @param color
     */
    public Ball(Point center, int r, Color color) {
        this.center = center;
        size = r;
        this.color = color;
        velocity = null;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * The function constructs a new ball-type object.
     *
     * @param x
     * @param y
     * @param r
     * @param color
     */
    public Ball(double x, double y, int r, Color color) {
        center = new Point(x, y);
        size = r;
        this.color = color;
        velocity = null;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * The function returns the x of the location.
     *
     * @return the x of the location.
     */
    public int getX() {
        return (int) center.getX();
    }

    /**
     * The function returns the y of the location.
     *
     * @return the y of the location.
     */
    public int getY() {
        return (int) center.getY();
    }

    /**
     * The function returns the center point of the ball.
     *
     * @return the center point of the ball.
     */
    public Point getCenter() {
        return center;
    }

    /**
     * The function returns the size of the ball.
     *
     * @return the size of the ball.
     */
    public int getSize() {
        return size;
    }

    /**
     * The function returns the color of the ball.
     *
     * @return the color of the ball.
     */
    public java.awt.Color getColor() {
        return color;
    }

    /**
     * The function returns the velocity of the ball.
     *
     * @return the velocity of the ball.
     */
    public Velocity getVelocity() {
        return velocity;
    }

    /**
     * The function updates the velocity of the ball.
     *
     * @param v
     */
    public void setVelocity(Velocity v) {
        velocity = v;
    }

    /**
     * The function updates the velocity of the ball.
     *
     * @param dx
     * @param dy
     */
    public void setVelocity(double dx, double dy) {
        if (velocity == null) {
            velocity = new Velocity(dx, dy);
        } else {
            velocity.setDx(dx);
            velocity.setDy(dy);
        }
    }

    /**
     * The function updates the x and y of the point of the ball.
     *
     * @param x
     * @param y
     */
    public void setPoint(double x, double y) {
        center.setX(x);
        center.setY(y);
    }

    /**
     * The function updates the game environment of the ball.
     *
     * @param gameEnvironment
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * The function draws the ball on the given DrawSurface.
     *
     * @param surface
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle((int) center.getX(), (int) center.getY(), size);
    }

    /**
     * The function moves the ball one step.
     */
    @Override
    public void timePassed() {
        Velocity velocity = null;
        Line trajectory = null;
        CollisionInfo collisionInfo = null;
        Point upperLeftOfRectangle = null;
        Rectangle rectangle = null;

        // Compute the ball trajectory.
        trajectory = new Line(this.center,
                this.velocity.applyToPoint(this.center));

        /* Check (using the game environment) if moving on this trajectory
        will hit anything. */
        collisionInfo = this.gameEnvironment
                .getClosestCollision(trajectory);

        // If no, then move the ball to the end of the trajectory.
        if (collisionInfo == null || collisionInfo.collisionObject() == null) {
            // Update the position of the ball.
            this.center = this.velocity.applyToPoint(this.center);
        } else {
            // There is a hit.

            upperLeftOfRectangle = collisionInfo.collisionObject().
                    getCollisionRectangle().getUpperLeft();
            rectangle = collisionInfo.collisionObject().
                    getCollisionRectangle();
            // Check if the ball is placed inside the object.
            if (this.center.getY() > upperLeftOfRectangle.getY()
                    && this.center.getY() < upperLeftOfRectangle.getY()
                    + rectangle.getHeight()
                    && this.center.getX() > upperLeftOfRectangle.getX()
                    && this.center.getX() < upperLeftOfRectangle.getX()
                    + rectangle.getWidth()) {
                this.center = new Point(this.center.getX(),
                        upperLeftOfRectangle.getY() - this.size / 2);
            } else {
                 /* Move the ball to "almost" the hit point, but just slightly
                 before it. */
                this.center = new Point(collisionInfo.collisionPoint().getX()
                        - (this.velocity.getDx() / this.size),
                        collisionInfo.collisionPoint().getY()
                                - (this.velocity.getDy() / this.size));
            }

            /* Notify the hit object (using its hit() method) that a collision
             occurred. */
            velocity = collisionInfo.collisionObject().hit(this,
                    collisionInfo.collisionPoint(), this.velocity);

            /* Update the velocity to the new velocity returned by the hit()
             method. */
            this.velocity = velocity;
        }
    }

    /**
     * The function adds the ball to the given game.
     *
     * @param g
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }

    /**
     * The function adds the hit listener to the list.
     *
     * @param hl
     */
    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    /**
     * The function removes the hit listener from the list.
     *
     * @param hl
     */
    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    public void removeFromGame(Game g) {
        g.removeSprite(this);
    }

    /**
     * The function returns the hit listeners list.
     *
     * @return the hit listeners list.
     */
    public List<HitListener> getHitListeners() {
        return this.hitListeners;
    }
}