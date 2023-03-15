package Animation;

import biuoop.DrawSurface;

/**
 * @author Adi Ben Yehuda 
 * @since 2022-05-26
 */
public interface Animation {
    /**
     * The function does one frame or ends the game.
     *
     * @param d
     */
    void doOneFrame(DrawSurface d);

    /**
     * The function stops the game.
     *
     * @return true if the game is over, otherwise false.
     */
    boolean shouldStop();
}
