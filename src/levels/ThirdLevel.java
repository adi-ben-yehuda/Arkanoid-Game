package levels;

import collidable_and_sprites.Block;
import collision_detection.Velocity;
import different_sprites.Sprite;
import game.LevelInformation;
import geometry_primitives.Point;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ThirdLevel implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 0;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return null;
    }

    @Override
    public int paddleSpeed() {
        return 0;
    }

    @Override
    public int paddleWidth() {
        return 0;
    }

    @Override
    public String levelName() {
        return null;
    }

    @Override
    public Sprite getBackground() {
        return null;
    }

    @Override
    public List<Block> blocks() {
        return null;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 0;
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
}
