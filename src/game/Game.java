package game;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import collidable_and_sprites.Block;
import collidable_and_sprites.Paddle;
import collision_detection.Collidable;
import different_sprites.ScoreIndicator;
import geometry_primitives.Point;
import different_sprites.Ball;
import different_sprites.Sprite;
import different_sprites.SpriteCollection;
import tests.PrintingHitListener;

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
    private Counter availableBlocks;
    private Counter availableBalls;
    private Counter score;

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
        int xBall = 100, yBall = 200, radius = 10, ballVelocity = 5;

        // Create new balls.
        balls.add(new Ball(xBall, yBall, radius, Color.GREEN));
        balls.add(new Ball(xBall / 2, yBall / 2, radius, Color.blue));
        balls.add(new Ball(xBall / 3, yBall / 3, radius, Color.pink));

        // Add the balls to the game.
        for (int i = 0; i < 3; i++) {
            balls.get(i).setVelocity(ballVelocity, ballVelocity);
            balls.get(i).setGameEnvironment(this.environment);
            balls.get(i).addToGame(this);
            this.availableBalls.increase(1);
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
     * The function initializes a new game: create the Blocks, Balls, and
     * Paddle and add them to the game.
     */
    public void initialize() {
        int paddleWidth = 150, paddleHeight = 20, yBlock = 25;
        ArrayList<Block> blocks = new ArrayList<>();
        ArrayList<Ball> balls = new ArrayList<>();
        PrintingHitListener printingHitListener = new PrintingHitListener();
        BlockRemover blockRemover;
        BallRemover ballRemover;
        ScoreTrackingListener scoreTrackingListener = new
                ScoreTrackingListener(this.score);

        defineBlocks(blocks);
        for (Block block : blocks) {
            block.addHitListener(printingHitListener);
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

        defineBalls(balls);
        ballRemover = new BallRemover(this, availableBalls);
        for (Ball ball : balls) {
            ball.addHitListener(ballRemover);
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
        int xWin = 280, xLose = 180, yMss = 250, textSize = 50, xScore = 230,
                yScore = 320, framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        String winText = "YOU WIN", scoreText = "Your score is:",
                loseText = "THE GAME IS OVER";

        Sleeper sleeper = new Sleeper();

        while (this.availableBalls.getValue() > 0
                && this.availableBlocks.getValue() > 0) {
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

        DrawSurface d = gui.getDrawSurface();
        this.sprites.drawAllOn(d);
        gui.show(d);
        d = gui.getDrawSurface();

        scoreText += score.getValue();

        // That is, the player won the game
        if (this.availableBlocks.getValue() == 0) {
            d.drawText(xWin, yMss, winText, textSize);
            d.drawText(xScore, yScore, scoreText, textSize);
        } else if (this.availableBalls.getValue() == 0) {
            // That is, the player lost the game.
            d.drawText(xLose, yMss, loseText, textSize);
            d.drawText(xScore, yScore, scoreText, textSize);
        }

        gui.show(d);
        sleeper.sleepFor(1500);
        gui.close();
    }
}
