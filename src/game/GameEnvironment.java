package game;

import collision_detection.Collidable;
import collision_detection.CollisionInfo;
import geometry_primitives.Line;
import geometry_primitives.Point;
import geometry_primitives.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adi Ben Yehuda 211769757
 * @since 2022-04-08
 */
public class GameEnvironment {
    private List<Collidable> collectibleObjects;

    /**
     * The function adds the object that can be collided, to the environment.
     *
     * @param c a new object that the ball can collide with.
     */
    public void addCollidable(Collidable c) {
        if (collectibleObjects == null) {
            collectibleObjects = new ArrayList<>();
        }
        collectibleObjects.add(c);
    }

    /**
     * The function checks if a ball moving on the line does not collide with
     * any of the objects in this collection. Otherwise, compute the closest
     * collision that is about to occur.
     *
     * @param trajectory
     * @return If this object will not collide with any of the collidable in
     * this collection, return null. Else, return the information about the
     * closest collision that is going to occur.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point minPoint = null, point = null;
        Collidable object = null;

        if (collectibleObjects == null) {
            return null;
        }

        for (int i = 0; i < collectibleObjects.size(); i++) {
            // Get the closest intersection point to the start of the line.
            point = trajectory.closestIntersectionToStartOfLine(
                    (Rectangle) collectibleObjects.get(i));
            // Meaning, there is an intersection point.
            if (point != null) {
                if (minPoint == null) {
                    minPoint = point;
                    object = collectibleObjects.get(i);
                } else {
                    /* Check if the point of intersection is closer to the
                     beginning of the line from the minimum point. */
                    if (trajectory.start().distance(point)
                            < trajectory.start().distance(minPoint)) {
                        minPoint = point;
                        object = collectibleObjects.get(i);
                    }
                }
            }
        }

        // Meaning, there is no intersection point.
        if (minPoint == null && object == null) {
            return null;
        }

        return new CollisionInfo(minPoint, object);
    }
}
