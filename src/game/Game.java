package game;

import Animation.Animation;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import collidable_and_sprites.Block;
import collidable_and_sprites.Paddle;
import collision_detection.Collidable;
import different_sprites.ScoreIndicator;
import geometry_primitives.Point;
import different_sprites.Ball;
import different_sprites.Sprite;
import different_sprites.SpriteCollection;
import Animation.AnimationRunner;
import Animation.PauseScreen;
import Animation.CountdownAnimation;

import java.awt.Color;
import java.util.ArrayList;

/**
 * @author Adi Ben Yehuda 211769757
 * @since 2022-04-14
 */
public class Game implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Counter availableBlocks;
    private Counter availableBalls;
    private Counter score;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;

    /**
     * The function constructs a new game.
     *
     * @param gui
     */
    public Game(GUI gui) {
        this.gui = gui;
        this.availableBlocks = new Counter();
        this.availableBalls = new Counter();
        this.score = new Counter();
        this.runner = new AnimationRunner(gui);
        this.keyboard = gui.getKeyboardSensor();
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
     * The function creates balls and adds them to the list of balls.
     *
     * @param balls
     */
    private void defineBalls(ArrayList<Ball> balls) {
        int radius = 10, ballVelocity = 5, xPaddle = 325, yPaddle = 560;

        // Create new balls.
        balls.add(new Ball(xPaddle + 30, yPaddle - 10, radius, Color.GREEN));
        balls.add(new Ball(xPaddle + 80, yPaddle - 10, radius, Color.blue));
        balls.add(new Ball(xPaddle + 130, yPaddle - 10, radius, Color.pink));

        // Add the balls to the game.
        for (int i = 0; i < 3; i++) {
            balls.get(i).setVelocity(ballVelocity, ballVelocity);
            balls.get(i).setGameEnvironment(this.environment);
            balls.get(i).addToGame(this);
            this.availableBalls.increase(1);
        }
    }

    /**
     * The function creates balls on top of the paddle and adds them to the
     * list of balls.
     */
    private void createBallsOnTopOfPaddle() {
        BallRemover ballRemover;
        ArrayList<Ball> balls = new ArrayList<>();

        defineBalls(balls);
        ballRemover = new BallRemover(this, availableBalls);
        for (Ball ball : balls) {
            ball.addHitListener(ballRemover);
        }
    }

    /**
     * The function adds the number to the score.
     *
     * @param number
     */
    public void setScore(int number) {
        this.score.increase(number);
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
     * The function removes the sprite from the list.
     *
     * @param s
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * The function removes the Collidable from the list.
     *
     * @param c
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
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
     * The function initializes a new game: create the Blocks, and
     * Paddle and add them to the game.
     */
    public void initialize() {
        int paddleWidth = 150, paddleHeight = 20, yBlock = 25;
        ArrayList<Block> blocks = new ArrayList<>();
        BlockRemover blockRemover;

        ScoreTrackingListener scoreTrackingListener = new
                ScoreTrackingListener(this.score);

        defineBlocks(blocks);
        for (Block block : blocks) {
            block.addHitListener(scoreTrackingListener);
            this.availableBlocks.increase(1);
        }

        Block deathRegion = new Block(new Point(0,
                gui.getDrawSurface().getHeight() - paddleHeight),
                gui.getDrawSurface().getWidth(), paddleHeight);
        blocks.add(deathRegion);

        blockRemover = new BlockRemover(this, availableBlocks);
        for (Block block : blocks) {
            block.addHitListener(blockRemover);
        }

        // Define the borders of the page.

        // Right block
        blocks.add(new Block(new Point(
                gui.getDrawSurface().getWidth() - paddleHeight, yBlock),
                paddleHeight, gui.getDrawSurface().getHeight(), Color.BLACK));
        // Upper block
        blocks.add(new Block(new Point(0, yBlock),
                gui.getDrawSurface().getWidth(), paddleHeight, Color.BLACK));
        // Left block
        blocks.add(new Block(new Point(0, yBlock), paddleHeight,
                gui.getDrawSurface().getHeight(),
                Color.BLACK));

        for (Block block : blocks) {
            // Add the block to the game.
            block.addToGame(this);
        }

        // Create a new paddle and add it to the game.
        Paddle paddle = new Paddle(gui.getKeyboardSensor(),
                new Point(gui.getDrawSurface().getWidth() / 2
                        - paddleWidth / 2, gui.getDrawSurface().getHeight()
                        - paddleHeight * 2), paddleWidth, paddleHeight, gui,
                Color.CYAN);
        paddle.addToGame(this);

        ScoreIndicator scoreIndicator = new ScoreIndicator(score);
        this.sprites.addSprite(scoreIndicator);
    }

    /**
     * The function runs the game.
     */
    public void run() {
        int numOfSeconds = 2000, countFrom = 3;

        this.createBallsOnTopOfPaddle(); // or a similar method
        this.runner.run(new CountdownAnimation(numOfSeconds, countFrom,
                this.sprites)); // countdown before turn starts.
        this.running = true;
        this.runner.run(this);
        gui.close();
    }

    /**
     * The function does one frame or ends the game.
     *
     * @param d
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.availableBalls.getValue() > 0
                && this.availableBlocks.getValue() > 0) {
            if (this.keyboard.isPressed("p")) {
                this.runner.run(new PauseScreen(this.keyboard));
            }
            this.sprites.drawAllOn(d);
            this.sprites.notifyAllTimePassed();
        } else {
            this.running = false;
        }
    }

    /**
     * The function stops the game.
     *
     * @return true if the game is over, otherwise false.
     */
    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}
