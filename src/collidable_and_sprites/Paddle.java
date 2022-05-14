package collidable_and_sprites;

import biuoop.GUI;
import biuoop.KeyboardSensor;
import collision_detection.Collidable;
import collision_detection.Velocity;
import game.Game;
import geometry_primitives.Point;
import different_sprites.Sprite;

import java.awt.Color;

/**
 * @author Adi Ben Yehuda 211769757
 * @since 2022-04-14
 */
public class Paddle extends Block implements Sprite, Collidable {
    private KeyboardSensor keyboard;
    private GUI gui;

    /**
     * The function constructs a new collidable_and_sprites.Paddle-type object.
     *
     * @param keyboard
     * @param upperLeft
     * @param width
     * @param height
     * @param gui
     */
    public Paddle(KeyboardSensor keyboard, Point upperLeft, double width,
                  double height, GUI gui) {
        super(upperLeft, width, height);
        this.keyboard = keyboard;
        this.gui = gui;
    }

    /**
     * The function constructs a new collidable_and_sprites.Paddle-type object.
     *
     * @param keyboard
     * @param upperLeft
     * @param width
     * @param height
     * @param gui
     * @param color
     */
    public Paddle(KeyboardSensor keyboard, Point upperLeft, double width,
                  double height, GUI gui, Color color) {
        super(upperLeft, width, height, color);
        this.keyboard = keyboard;
        this.gui = gui;
    }

    /**
     * The function moves the paddle one step to the left side.
     */
    public void moveLeft() {
        int moveLeftNumber = 7;
        int widthOfLeftBlock = 20;

        if (super.getUpperLeft().getX() - moveLeftNumber > widthOfLeftBlock) {
            super.getUpperLeft().setX(super.getUpperLeft().getX() - moveLeftNumber);
        } else {
            super.getUpperLeft().setX(widthOfLeftBlock);
        }
    }

    /**
     * The function moves the paddle one step to the right side.
     */
    public void moveRight() {
        int moveLeftNumber = 7, widthOfRightBlock = 20;

        if (super.getUpperLeft().getX() + moveLeftNumber
                < gui.getDrawSurface().getWidth() - widthOfRightBlock
                        - super.getWidth()) {
            super.getUpperLeft().setX(super.getUpperLeft().getX()
                    + moveLeftNumber);
        } else {
            super.getUpperLeft().setX(gui.getDrawSurface().getWidth()
                    - widthOfRightBlock - super.getWidth());
        }
    }

    /**
     * The function moves the paddle one step according to the user's click.
     */
    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * The function adds the paddle to the given game.
     *
     * @param g
     */
    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * The function checks which side of the block the collision point is on
     * and returns the new velocity according to it.
     *
     * @param collisionPoint
     * @param currentVelocity
     * @return the new velocity expected after the hit
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        int numberOfRegions = 5, addToDegree = 30, circle = 360;
        int regionNumber = 0, angle = 300, currentAngle = 0;
        double speed = 0;
        Velocity velocity = currentVelocity;

        // Check if the collision point is on the left side of the paddle.
        if (collisionPoint.getX() == super.getUpperLeft().getX()
                && collisionPoint.getY() > super.getUpperLeft().getY()
                && collisionPoint.getY() < super.getUpperLeft().getY()
                + super.getHeight()) {
            return new Velocity(currentVelocity.getDx() * (-1),
                    currentVelocity.getDy());
        }

        //Check if the collision point is on the right side of the paddle.
        if (collisionPoint.getX() == super.getUpperLeft().getX()
                + super.getWidth()
                && collisionPoint.getY() > super.getUpperLeft().getY()
                && collisionPoint.getY() < super.getUpperLeft().getY()
                + super.getHeight()) {
            return new Velocity(currentVelocity.getDx() * (-1),
                    currentVelocity.getDy());
        }

        speed = Math.sqrt(Math.pow(currentVelocity.getDx(), 2)
                + Math.pow(currentVelocity.getDy(), 2));

        for (int i = (int) super.getUpperLeft().getX();
             i < gui.getDrawSurface().getWidth();
             i += super.getWidth() / numberOfRegions) {
            // Check if the ball is in this part of the paddle.
            if (collisionPoint.getX() >= i && collisionPoint.getX()
                    <= super.getWidth() / numberOfRegions + i) {
                // Calculate the angle according to the part where the ball is.
                if (regionNumber == 0) {
                    currentAngle = angle;
                } else {
                    currentAngle = angle + regionNumber * addToDegree;
                }

                if (currentAngle >= circle) {
                    currentAngle -= circle;
                }

                if (currentAngle == 0) {
                    velocity = new Velocity(currentVelocity.getDx(),
                            currentVelocity.getDy() * (-1));
                } else {
                    velocity = Velocity.fromAngleAndSpeed(currentAngle, speed);
                }

                break;
            }
            regionNumber++;
        }

        return velocity;
    }
}
