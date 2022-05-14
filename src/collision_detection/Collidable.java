package collision_detection;

import different_sprites.Ball;
import geometry_primitives.Point;
import geometry_primitives.Rectangle;

/**
 * @author Adi Ben Yehuda 211769757
 * @since 2022-04-08
 */
public interface Collidable {
    /**
     * The function returns the "collision shape" of the object.
     *
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * The function checks which side of the block the collision point is on
     * and returns the new velocity according to it.
     *
     * @param hitter
     * @param collisionPoint
     * @param currentVelocity
     * @return the new velocity expected after the hit
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
