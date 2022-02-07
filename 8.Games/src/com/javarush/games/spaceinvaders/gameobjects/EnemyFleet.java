package com.javarush.games.spaceinvaders.gameobjects;

import com.javarush.engine.cell.Game;
import com.javarush.games.spaceinvaders.Direction;
import com.javarush.games.spaceinvaders.ShapeMatrix;
import com.javarush.games.spaceinvaders.SpaceInvadersGame;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.util.Pair;

public class EnemyFleet {

    private static final int ROWS_COUNT = 3;
    private static final int COLUMNS_COUNT = 10;
    private static final int STEP = ShapeMatrix.ENEMY.length + 1;
    private List<EnemyShip> ships;
    private Direction direction = Direction.RIGHT;

    public EnemyFleet() {
        createShips();
    }

    private void createShips() {
        ships = new ArrayList<>();
        final EnemyShip boss = new Boss(
                STEP * COLUMNS_COUNT / 2 - ShapeMatrix.BOSS_ANIMATION_FIRST.length / 2 - 1, 5);

        for (int x = 0; x < COLUMNS_COUNT; x++) {
            for (int y = 0; y < ROWS_COUNT; y++) {
                ships.add(new EnemyShip(x * STEP, y * STEP + 12));
            }
        }
        ships.add(boss);
    }

    public void draw(Game game) {
        ships.forEach(ship -> ship.draw(game));
    }

    private double getSpeed() {
        return Math.min(2.0, 3.0 / ships.size());
    }

    public void move() {
        if (ships.size() != 0) {
            final double speed = getSpeed();

            if (direction == Direction.LEFT && getLeftBorder() < 0) {
                direction = Direction.RIGHT;
                ships.forEach(ship -> ship.move(Direction.DOWN, speed));
            } else if (direction == Direction.RIGHT && getRightBorder() > SpaceInvadersGame.WIDTH) {
                direction = Direction.LEFT;
                ships.forEach(ship -> ship.move(Direction.DOWN, speed));
            } else {
                ships.forEach(ship -> ship.move(direction, speed));
            }
        }
    }

    private double getLeftBorder() {
        return ships.stream()
                .map(ship -> ship.x)
                .min(Double::compareTo)
                .orElseThrow(RuntimeException::new);
    }

    private double getRightBorder() {
        return ships.stream()
                .map(ship -> ship.x + ship.width)
                .max(Double::compareTo)
                .orElseThrow(RuntimeException::new);
    }

    public Bullet fire(Game game) {
        Bullet result = null;

        if (ships != null && ships.size() != 0) {
            final boolean doFire = game.getRandomNumber(100 / SpaceInvadersGame.COMPLEXITY) <= 0;

            if (doFire) {
                final int shipId = game.getRandomNumber(ships.size());

                result = ships.get(shipId).fire();
            }
        }
        return result;
    }

    public int verifyHit(List<Bullet> bullets) {
        AtomicInteger score = new AtomicInteger();

        if (bullets.size() > 0) {
            bullets.stream()
                    .filter(bullet -> bullet.isAlive)
                    .flatMap(bullet ->
                            ships.stream()
                                    .filter(enemyShip -> enemyShip.isAlive)
                                    .map(enemyShip -> new Pair<>(bullet, enemyShip))
                    )
                    .filter(bulletEnemyShipPair ->
                            bulletEnemyShipPair.getKey()
                                    .isCollision(bulletEnemyShipPair.getValue()))
                    .forEach(bulletEnemyShipPair -> {
                        bulletEnemyShipPair.getKey().kill();
                        bulletEnemyShipPair.getValue().kill();
                        score.addAndGet(bulletEnemyShipPair.getValue().score);
                    });
        }
        return score.get();
    }

    public void deleteHiddenShips() {
        ships.removeIf(enemyShip -> !enemyShip.isVisible());
    }


    public double getBottomBorder() {
        return ships.stream()
                .map(enemyShip -> enemyShip.y + enemyShip.height)
                .max(Double::compare)
                .orElse(0.0);
    }

    public int getShipsCount() {
        return ships.size();
    }
}
