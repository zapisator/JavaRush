package com.javarush.games.spaceinvaders;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;
import com.javarush.engine.cell.Key;
import com.javarush.games.spaceinvaders.gameobjects.Bullet;
import com.javarush.games.spaceinvaders.gameobjects.EnemyFleet;
import com.javarush.games.spaceinvaders.gameobjects.PlayerShip;
import com.javarush.games.spaceinvaders.gameobjects.Star;
import java.util.ArrayList;
import java.util.List;

public class SpaceInvadersGame extends Game {

    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;
    public static final int COMPLEXITY = 5;
    private static final int PLAYER_BULLETS_MAX = 1;
    private List<Star> stars;
    private EnemyFleet enemyFleet;
    private List<Bullet> enemyBullets;
    private PlayerShip playerShip;
    private List<Bullet> playerBullets;
    private boolean isGameStopped;
    private int animationsCount;
    private int score;

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    @Override
    public void onTurn(int step) {
        moveSpaceObjects();
        check();
        enemyShipFire();
        setScore(score);
        drawScene();
    }

    private void enemyShipFire() {
        final Bullet bullet = enemyFleet.fire(this);

        if (bullet != null) {
            enemyBullets.add(bullet);
        }
    }

    @Override
    public void onKeyPress(Key key) {
        if (key == Key.SPACE) {
            if (isGameStopped) {
                createGame();
            } else {
                playerShipFire();
            }
        } else {
            playerShipSetDirection(key);
        }
    }

    private void playerShipFire() {
        final Bullet bullet = playerShip.fire();

        if (bullet != null && playerBullets.size() < PLAYER_BULLETS_MAX) {
            playerBullets.add(bullet);
        }
    }

    private void playerShipSetDirection(Key key) {
        if (key == Key.LEFT) {
            playerShip.setDirection(Direction.LEFT);
        } else if (key == Key.RIGHT) {
            playerShip.setDirection(Direction.RIGHT);
        }
    }

    @Override
    public void onKeyReleased(Key key) {
        if (key == Key.LEFT
                && playerShip.getDirection() == Direction.LEFT
                || key == Key.RIGHT
                && playerShip.getDirection() == Direction.RIGHT) {
            playerShip.setDirection(Direction.UP);
        }
    }

    private void createGame() {
        createStars();
        enemyFleet = new EnemyFleet();
        enemyBullets = new ArrayList<>();
        playerShip = new PlayerShip();
        playerBullets = new ArrayList<>();
        isGameStopped = false;
        animationsCount = 0;
        drawScene();
        setTurnTimer(40);
        score = 0;
    }

    private void drawScene() {
        drawField();
        playerBullets.forEach(bullet -> bullet.draw(this));
        playerShip.draw(this);
        enemyBullets.forEach(bullet -> bullet.draw(this));
        enemyFleet.draw(this);
    }

    private void drawField() {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                setCellValueEx(x, y, Color.CORAL, "");
            }
        }
        stars.forEach((star) -> star.draw(this));
    }

    private void createStars() {
        stars = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            stars.add(new Star(getRandomNumber(WIDTH), getRandomNumber(HEIGHT)));
        }
    }

    private void moveSpaceObjects() {
        enemyFleet.move();
        enemyBullets.forEach(Bullet::move);
        playerShip.move();
        playerBullets.forEach(Bullet::move);
    }

    @Override
    public void setCellValueEx(int x, int y, Color cellColor, String value) {
        if (x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT) {
            super.setCellValueEx(x, y, cellColor, value);
        }
    }

    private void removeDeadBullets() {
        enemyBullets.removeIf(bullet -> !bullet.isAlive || bullet.y >= HEIGHT - 1);
        playerBullets.removeIf(bullet -> !bullet.isAlive || bullet.y + bullet.height < 0);
    }

    private void check() {
        playerShip.verifyHit(enemyBullets);
        score += enemyFleet.verifyHit(playerBullets);
        enemyFleet.deleteHiddenShips();
        removeDeadBullets();
        if (enemyFleet.getBottomBorder() >= playerShip.y) {
            playerShip.kill();
        }
        if (!playerShip.isAlive) {
            stopGameWithDelay();
        }
        if (enemyFleet.getShipsCount() == 0) {
            playerShip.win();
        }
    }

    private void stopGame(boolean isWin) {
        isGameStopped = true;
        stopTurnTimer();
        if (isWin) {
            showMessageDialog(Color.RED, "win", Color.GREEN, 5);
        } else {
            showMessageDialog(Color.GREEN, "lost", Color.RED, 5);
        }
    }

    private void stopGameWithDelay() {
        animationsCount++;
        if (animationsCount >= 10) {
            stopGame(playerShip.isAlive);
        }
    }
}
