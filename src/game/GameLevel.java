package game;

import Animation.Animation;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import collidable_and_sprites.Block;
import collidable_and_sprites.Paddle;
import collision_detection.Collidable;
import collision_detection.Velocity;
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
import java.util.List;

/**
 * @author Adi Ben Yehuda 211769757
 * @since 2022-04-14
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Counter availableBlocks;
    private Counter availableBalls;
    private Counter score;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation levelInformation;

    /**
     * The function constructs a new game.
     *
     * @param gui
     */
    public GameLevel(GUI gui, LevelInformation levelInformation) {
        this.gui = gui;
        this.availableBlocks = new Counter();
        this.availableBalls = new Counter();
        this.score = new Counter();
        this.runner = new AnimationRunner(gui);
        this.keyboard = gui.getKeyboardSensor();
        this.levelInformation = levelInformation;
    }

    /**
     * The function creates balls and adds them to the list of balls.
     *
     * @param balls
     */
    private void defineBalls(ArrayList<Ball> balls) {
        int radius = 10, xPaddle = 380, yPaddle = 560;
        List<Velocity> velocities = this.levelInformation.initialBallVelocities();

        for (int i = 0; i < this.levelInformation.numberOfBalls(); i++) {
            // Create new ball and add it to the balls list.
            balls.add(new Ball(xPaddle + (i + 1) * 30, yPaddle - 10,
                    radius, Color.gray));
            // Set the velocity according to the level.
            balls.get(i).setVelocity(velocities.get(i));
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
        // Define each ball to remove blocks.
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
     * The function initializes a new game: create the Blocks and Paddle
     * and add them to the game.
     */
    public void initialize() {
        int paddleHeight = 20, yBlock = 25;
        ArrayList<Block> blocks = new ArrayList<>();
        BlockRemover blockRemover;

        ScoreTrackingListener scoreTrackingListener = new
                ScoreTrackingListener(this.score);
        this.availableBlocks.increase(levelInformation.numberOfBlocksToRemove());

        for (int i = 0; i < levelInformation.numberOfBlocksToRemove(); i++) {
            // Add the blocks of the level to the blocks list.
            blocks.add(levelInformation.blocks().get(i));
            // Each block has a score when hit.
            levelInformation.blocks().get(i).
                    addHitListener(scoreTrackingListener);
        }

        for (int i = levelInformation.numberOfBlocksToRemove();
             i < levelInformation.blocks().size(); i++) {
            // Add the blocks of the level to the blocks list.
            blocks.add(levelInformation.blocks().get(i));
        }

        // Define the bottom border block.
        Block deathRegion = new Block(new Point(0,
                gui.getDrawSurface().getHeight() - paddleHeight),
                gui.getDrawSurface().getWidth(), paddleHeight);
        blocks.add(deathRegion);

        blockRemover = new BlockRemover(this, availableBlocks);
        for (Block block : blocks) {
            // The block should be removed when hitting it.
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
                        - this.levelInformation.paddleWidth() / 2,
                        gui.getDrawSurface().getHeight() - paddleHeight * 2),
                this.levelInformation.paddleWidth(), paddleHeight, gui,
                Color.BLACK, this.levelInformation.paddleSpeed());
        paddle.addToGame(this);

        // Print the score for the user.
        ScoreIndicator scoreIndicator = new ScoreIndicator(score);
        this.sprites.addSprite(scoreIndicator);

        // Print the level name for the user.
        LevelName levelName = new LevelName(this.levelInformation.levelName());
        this.sprites.addSprite(levelName);

        this.sprites.addSprite(this.levelInformation.getBackground());
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
