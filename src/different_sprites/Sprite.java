package different_sprites;

import biuoop.DrawSurface;

/**
 * @author Adi Ben Yehuda 
 * @since 2022-04-15
 */
public interface Sprite {
    /**
     * The function draws the sprite to the screen.
     *
     * @param d
     */
    void drawOn(DrawSurface d);

    /**
     * The function notifies the sprite that time has passed.
     */
    void timePassed();
}
