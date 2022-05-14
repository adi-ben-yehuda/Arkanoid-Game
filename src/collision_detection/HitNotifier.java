package collision_detection;

/**
 * @author Adi Ben Yehuda 211769757
 * @since 2022-05-12
 */
public interface HitNotifier {
    /**
     * The function adds hl as a listener to hit events.
     *
     * @param hl
     */
    void addHitListener(HitListener hl);

    /**
     * The function removes hl from the list of listeners to hit events.
     *
     * @param hl
     */
    void removeHitListener(HitListener hl);
}
