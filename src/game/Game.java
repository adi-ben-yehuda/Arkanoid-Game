package game;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import collidable_and_sprites.Block;
import collidable_and_sprites.Paddle;
import collision_detection.Collidable;
import geometry_primitives.Point;
import different_sprites.Ball;
import different_sprites.Sprite;
import different_sprites.SpriteCollection;

import java.awt.Color;
import java.util.ArrayList;

/**
 * @author Adi Ben Yehuda 211769757
 * @since 2022-04-14
 */
public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;

    /**
     * The function constructs a new game.
     *
     * @param gui
     */
    public Game(GUI gui) {
        this.gui = gui;
    }

    /**
     * The function creates blocks and adds them to the list of blocks.
     *
     * @param blocks
     */
    private void defineBlocks(ArrayList<Block> blocks) {
        int blockWidth = 45, startX = 735, blockHeight = 20;
        int blocksInRow = 12, yOfPoint = 100;

        for (int i = 0; i < blocksInRow * blockWidth; i += blockWidth) {
            blocks.add(new Block(new Point(startX - i, yOfPoint),
                    blockWidth, blockHeight, Color.red));
        }

        blocksInRow--;
        yOfPoint += blockHeight;
        for (int i = 0; i < blocksInRow * blockWidth; i += blockWidth) {
            blocks.add(new Block(new Point(startX - i, yOfPoint),
                    blockWidth, blockHeight, Color.orange));
        }

        blocksInRow--;
        yOfPoint += blockHeight;
        for (int i = 0; i < blocksInRow * blockWidth; i += blockWidth) {
            blocks.add(new Block(new Point(startX - i, yOfPoint),
                    blockWidth, blockHeight, Color.yellow));
        }

        blocksInRow--;
        yOfPoint += blockHeight;
        for (int i = 0; i < blocksInRow * blockWidth; i += blockWidth) {
            blocks.add(new Block(new Point(startX - i, yOfPoint),
                    blockWidth, blockHeight, Color.pink));
        }

        blocksInRow--;
        yOfPoint += blockHeight;
        for (int i = 0; i < blocksInRow * blockWidth; i += blockWidth) {
            blocks.add(new Block(new Point(startX - i, yOfPoint),
                    blockWidth, blockHeight, Color.magenta));
        }

        blocksInRow--;
        yOfPoint += blockHeight;
        for (int i = 0; i < blocksInRow * blockWidth; i += blockWidth) {
            blocks.add(new Block(new Point(startX - i, yOfPoint),
                    blockWidth, blockHeight, Color.GRAY));
        }
    }

    /**
     * The function adds the object that can be collided, to the environment.
     *
     * @param c a new object that the ball can collide with.
     */
    public void addCollidable(Collidable c) {
        if (environment == null) {
            environment = new GameEnvironment();
        }
        environment.addCollidable(c);
    }

    /**
     * The function adds the object that can be drawn, to the sprites.
     *
     * @param s a new object that can be drawn on the surface.
     */
    public void addSprite(Sprite s) {
        if (sprites == null) {
            sprites = new SpriteCollection();
        }
        sprites.addSprite(s);
    }

    /**
     * The function returns the environment.
     *
     * @return the environment.
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * The function initializes a new game: create the Blocks and sprites.Ball
     * (and collidable_and_sprites.Paddle) and add them to the game.
     */
    public void initialize() {
        int xOfFirstBall = 600, yOfFirstBall = 450, radius = 10,
                xOfSecondBall = 100, yOfSecondBall = 150, ballVelocity = 5,
                paddleWidth = 90, paddleHeight = 20;
        ArrayList<Block> blocks = new ArrayList<>();

        defineBlocks(blocks);

        // Define the borders of the page.
        blocks.add(new Block(new Point(
                gui.getDrawSurface().getWidth() - paddleHeight, 0),
                paddleHeight, gui.getDrawSurface().getHeight(), Color.BLACK));
        blocks.add(new Block(new Point(0, 0),
                gui.getDrawSurface().getWidth(), paddleHeight, Color.BLACK));
        blocks.add(new Block(new Point(0,
                gui.getDrawSurface().getHeight() - paddleHeight),
                gui.getDrawSurface().getWidth(), paddleHeight, Color.BLACK));
        blocks.add(new Block(new Point(0, 0), paddleHeight,
                gui.getDrawSurface().getHeight(),
                Color.BLACK));

        for (Block block : blocks) {
            // Add the block to the game.
            block.addToGame(this);
        }

        // Create a new ball and add it to the game.
        Ball ball = new Ball(xOfFirstBall, yOfFirstBall, radius, Color.GREEN);
        ball.setVelocity(ballVelocity, ballVelocity);
        ball.setGameEnvironment(this.environment);
        ball.addToGame(this);

        // Create a new ball and add it to the game.
        Ball secondBall = new Ball(xOfSecondBall, yOfSecondBall, radius,
                Color.blue);
        secondBall.setVelocity(ballVelocity, ballVelocity);
        secondBall.setGameEnvironment(this.environment);
        secondBall.addToGame(this);

        // Create a new paddle and add it to the game.
        Paddle paddle = new Paddle(gui.getKeyboardSensor(),
                new Point(gui.getDrawSurface().getWidth() / 2
                        - paddleWidth / 2, gui.getDrawSurface().getHeight()
                        - paddleHeight * 2), paddleWidth, paddleHeight, gui,
                Color.CYAN);
        paddle.addToGame(this);
    }

    /**
     * The function runs the game.
     */
    public void run() {
        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;

        while (true) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
