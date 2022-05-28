package levels;

import collidable_and_sprites.Block;
import collision_detection.Velocity;
import different_sprites.Sprite;
import game.LevelInformation;

import java.util.List;

public class SecondLevel implements LevelInformation {
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
}
