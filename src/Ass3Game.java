import biuoop.GUI;

/**
 * @author Adi Ben Yehuda 211769757
 * @since 2022-04-14
 */
public class Ass3Game {

    /**
     * The function creates a game object, initializes and runs it.
     *
     * @param args
     */
    public static void main(String[] args) {
        int width = 800, height = 600;
        GUI gui = new GUI("The best game ever", width, height);
        Game game = new Game(gui);

        game.initialize();
        game.run();
    }
}
