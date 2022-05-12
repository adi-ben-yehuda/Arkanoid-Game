package collidable_and_sprites;

import biuoop.DrawSurface;
import collision_detection.Collidable;
import collision_detection.HitListener;
import collision_detection.HitNotifier;
import collision_detection.Velocity;
import different_sprites.Ball;
import game.Game;
import geometry_primitives.Point;
import geometry_primitives.Rectangle;
import different_sprites.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Adi Ben Yehuda 211769757
 * @since 2022-04-08
 */
public class Block extends Rectangle implements Collidable, Sprite, HitNotifier {
    List<HitListener> hitListeners;

    /**
     * The function constructs a new block with location and width/height.
     *
     * @param upperLeft
     * @param width
     * @param height
     */
    public Block(Point upperLeft, double width, double height) {
        super(upperLeft, width, height);
        this.hitListeners = new ArrayList<>();
    }

    /**
     * The function constructs a new block with location and width/height.
     *
     * @param rectangle
     */
    public Block(Rectangle rectangle) {
        super(rectangle.getUpperLeft(), rectangle.getWidth(),
                rectangle.getHeight());
        this.hitListeners = new ArrayList<>();
    }

    /**
     * The function constructs a new block.
     *
     * @param upperLeft
     * @param width
     * @param height
     * @param color
     */
    public Block(Point upperLeft, double width, double height, Color color) {
        super(upperLeft, width, height, color);
        this.hitListeners = new ArrayList<>();
    }

    /**
     * The function notifies all listeners about a hit event.
     *
     * @param hitter
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);

        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * The function returns the hit listeners list.
     *
     * @return the hit listeners list.
     */
    public List<HitListener> getHitListeners() {
        return this.hitListeners;
    }

    /**
     * The function returns the "collision shape" of the object.
     *
     * @return the "collision shape" of the object.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this;
    }

    /**
     * The function checks which side of the block the collision point is on
     * and returns the new velocity according to it.
     *
     * @param collisionPoint
     * @param currentVelocity
     * @return the new velocity expected after the hit
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity velocity = new Velocity(currentVelocity.getDx(),
                currentVelocity.getDy());

        // Meaning the ball is close to the left side of the block.
        if (collisionPoint.getX() == super.getUpperLeft().getX()) {
            velocity.setDx((-1) * velocity.getDx());
        } else if (collisionPoint.getX()
                == super.getUpperLeft().getX() + super.getWidth()) {
            // Meaning the ball is close to the right side of the block.
            velocity.setDx((-1) * velocity.getDx());
        }

        // Meaning the ball is close to the top of the block.
        if (collisionPoint.getY() == super.getUpperLeft().getY()) {
            velocity.setDy((-1) * velocity.getDy());
        } else if (collisionPoint.getY()
                == super.getUpperLeft().getY() + super.getHeight()) {
            // Meaning the ball is close to the bottom of the block.
            velocity.setDy((-1) * velocity.getDy());
        }

        this.notifyHit(hitter);
        return velocity;
    }

    /**
     * The function draws the block on the given DrawSurface.
     *
     * @param d
     */
    @Override
    public void drawOn(DrawSurface d) {
        if (super.getColor() != null) {
            d.setColor(super.getColor());
            d.fillRectangle((int) this.getUpperLeft().getX(),
                    (int) this.getUpperLeft().getY(), (int) this.getWidth(),
                    (int) this.getHeight());

            d.setColor(Color.BLACK);
            d.drawRectangle((int) this.getUpperLeft().getX(),
                    (int) this.getUpperLeft().getY(), (int) this.getWidth(),
                    (int) this.getHeight());
        }
    }

    /**
     * The function calls timePassed() on all sprites.
     */
    @Override
    public void timePassed() {
    }

    /**
     * The function adds the block to the given game.
     *
     * @param g
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * The function removes the block from the game.
     *
     * @param game
     */
    public void removeFromGame(Game game) {
        game.removeSprite(this);
        game.removeCollidable(this);
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
}
