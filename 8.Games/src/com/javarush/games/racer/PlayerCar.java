package com.javarush.games.racer;

import static com.javarush.games.racer.road.RoadManager.LEFT_BORDER;
import static com.javarush.games.racer.road.RoadManager.RIGHT_BORDER;

import com.javarush.games.racer.road.RoadManager;

public class PlayerCar extends GameObject {
    private static int playerCarHeight = ShapeMatrix.PLAYER.length;
    public int speed = 1;

    private Direction direction;

    public PlayerCar() {
        super(RacerGame.WIDTH / 2 + 2, RacerGame.HEIGHT - playerCarHeight - 1, ShapeMatrix.PLAYER);
    }

    public void move() {
        if (x < LEFT_BORDER) {
            x = LEFT_BORDER;
        } else if (x > RIGHT_BORDER - width) {
            x = RIGHT_BORDER - width;
        }

        if (direction == Direction.LEFT) {
            --x;
        } else if (direction == Direction.RIGHT) {
            ++x;
        }
    }

    public void stop() {
        matrix = ShapeMatrix.PLAYER_DEAD;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }
}
