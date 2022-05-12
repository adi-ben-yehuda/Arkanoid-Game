import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Adi Ben Yehuda 211769757
 * @since 2022-04-08
 */
public class Block extends Rectangle implements Collidable, Sprite {

    /**
     * The function constructs a new block with location and width/height.
     *
     * @param upperLeft
     * @param width
     * @param height
     */
    public Block(Point upperLeft, double width, double height) {
        super(upperLeft, width, height);
    }

    /**
     * The function constructs a new block with location and width/height.
     *
     * @param rectangle
     */
    public Block(Rectangle rectangle) {
        super(rectangle.getUpperLeft(), rectangle.getWidth(),
                rectangle.getHeight());
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
    }

    /**
     * The function returns the "collision shape" of the object.
     *
     * @return the "collision shape" of the object.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return (Rectangle) this;
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
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
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
}
