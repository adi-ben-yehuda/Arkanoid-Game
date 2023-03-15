package game;

/**
 * @author Adi Ben Yehuda 
 * @since 2022-05-12
 */
public class Counter {
    private int counter;

    /**
     * The function constructs a new counter object.
     */
    public Counter() {
        this.counter = 0;
    }

    /**
     * The function adds number to current count.
     *
     * @param number
     */
    public void increase(int number) {
        this.counter = this.counter + number;
    }

    /**
     * The function subtracts number from current count.
     *
     * @param number
     */
    public void decrease(int number) {
        this.counter = this.counter - number;
    }

    /**
     * The function returns the current count.
     *
     * @return the current count.
     */
    public int getValue() {
        return this.counter;
    }
}
