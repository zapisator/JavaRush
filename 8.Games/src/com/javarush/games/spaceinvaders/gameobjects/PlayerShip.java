package com.javarush.games.spaceinvaders.gameobjects;

import static com.javarush.games.spaceinvaders.ShapeMatrix.DEAD_PLAYER;
import static com.javarush.games.spaceinvaders.ShapeMatrix.KILL_PLAYER_ANIMATION_FIRST;
import static com.javarush.games.spaceinvaders.ShapeMatrix.KILL_PLAYER_ANIMATION_SECOND;
import static com.javarush.games.spaceinvaders.ShapeMatrix.KILL_PLAYER_ANIMATION_THIRD;

import com.javarush.games.spaceinvaders.Direction;
import com.javarush.games.spaceinvaders.ShapeMatrix;
import com.javarush.games.spaceinvaders.SpaceInvadersGame;
import java.util.List;

public class PlayerShip extends Ship {

    private Direction direction = Direction.UP;

    public PlayerShip() {
        super(SpaceInvadersGame.WIDTH / 2.0,
                SpaceInvadersGame.HEIGHT - ShapeMatrix.PLAYER.length - 1);
        setStaticView(ShapeMatrix.PLAYER);
    }

    public Direction getDirection() {
        return direction;
    }

    public void verifyHit(List<Bullet> bullets) {
        if (bullets != null
                && bullets.size() > 0
                && isAlive) {
            bullets.stream()
                    .filter(bullet -> this.isAlive && bullet.isAlive && isCollision(bullet))
                    .peek(bullet -> {
                        if (isAlive) {
                            kill();
                        }
                    })
                    .forEach(Bullet::kill);
        }
    }

    @Override
    public Bullet fire() {
        return isAlive
                ? new Bullet(x + 2, y - ShapeMatrix.BULLET.length, Direction.UP)
                : null;
    }

    @Override
    public void kill() {
        if (isAlive) {
            isAlive = false;
            setAnimatedView(
                    false,
                    KILL_PLAYER_ANIMATION_FIRST,
                    KILL_PLAYER_ANIMATION_SECOND,
                    KILL_PLAYER_ANIMATION_THIRD,
                    DEAD_PLAYER
            );
        }
    }

    public void setDirection(Direction newDirection) {
        this.direction = newDirection == Direction.DOWN ? direction : newDirection;
    }

    public void move() {
        if (isAlive) {
            if (direction == Direction.LEFT) {
                x--;
            } else if (direction == Direction.RIGHT) {
                x++;
            }
            if (x < 0) {
                x = 0;
            } else if (x + width > SpaceInvadersGame.WIDTH) {
                x = SpaceInvadersGame.WIDTH - width;
            }
        }
    }

    public void win() {
        setStaticView(ShapeMatrix.WIN_PLAYER);
    }
}
