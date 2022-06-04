package game;

import biuoop.DrawSurface;
import different_sprites.Sprite;

/**
 * @author Adi Ben Yehuda 211769757
 * @since 2022-05-28
 */
public class LevelName implements Sprite {
    private String levelName;

    /**
     * The function constructs a new LevelName.
     *
     * @param levelName
     */
    public LevelName(String levelName) {
        this.levelName = levelName;
    }

    /**
     * The function draws the sprite to the screen.
     *
     * @param d
     */
    @Override
    public void drawOn(DrawSurface d) {
        int x = 550, y = 20, size = 15;
        String text = "Level Name: " + this.levelName;

        d.drawText(x, y, text, size);
    }

    /**
     * The function notifies the sprite that time has passed.
     */
    @Override
    public void timePassed() {

    }
}
