package different_sprites;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adi Ben Yehuda 211769757
 * @since 2022-04-14
 */
public class SpriteCollection {
    private List<Sprite> spriteList;

    /**
     * The function adds the object that can be drawn, to the list.
     *
     * @param s a new object that can be drawn on the surface.
     */
    public void addSprite(Sprite s) {
        if (spriteList == null) {
            spriteList = new ArrayList<>();
        }
        spriteList.add(s);
    }

    /**
     * The function removes the sprite from the list.
     *
     * @param s
     */
    public void removeSprite(Sprite s) {
        if (spriteList != null) {
            spriteList.remove(s);
        }
    }

    /**
     * The function calls timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < spriteList.size(); i++) {
            spriteList.get(i).timePassed();
        }
    }

    /**
     * The function calls drawOn(d) on all sprites.
     *
     * @param d The surface on which we draw the sprites.
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < spriteList.size(); i++) {
            spriteList.get(i).drawOn(d);
        }
    }
}
