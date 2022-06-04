package Animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Adi Ben Yehuda 211769757
 * @since 2022-06-02
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean isAlreadyPressed;
    private boolean stop;

    /**
     * The function constructs a new KeyPressStoppableAnimation.
     *
     * @param sensor
     * @param key
     * @param animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key,
                                      Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.isAlreadyPressed = true;
        this.stop = false;
    }

    /**
     * The function does one frame.
     *
     * @param d
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);

        if (sensor.isPressed(key) && !isAlreadyPressed) {
            this.stop = true;
        }
        if (!sensor.isPressed(key)) {
            isAlreadyPressed = false;
        }
    }

    /**
     * The function stops displaying the screen.
     *
     * @return true if you need to stop displaying the screen, otherwise false.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
