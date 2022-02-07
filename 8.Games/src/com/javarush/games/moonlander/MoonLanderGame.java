package com.javarush.games.moonlander;

import com.javarush.engine.cell.*;

public class MoonLanderGame extends Game {

    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;
    private Rocket rocket;
    private GameObject landscape;
    private GameObject platform;
    private boolean isUpPressed;
    private boolean isLeftPressed;
    private boolean isRightPressed;
    private boolean isGameStopped;
    private int score;

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        createGame();
        showGrid(false);
    }

    @Override
    public void onKeyReleased(Key key) {
        switch (key) {
            case UP:
                isUpPressed = false;
                break;
            case LEFT:
                isLeftPressed = false;
                break;
            case RIGHT:
                isRightPressed = false;
                break;
        }
    }

    @Override
    public void onKeyPress(Key key) {
        switch (key) {
            case UP:
                isUpPressed = true;
                break;
            case LEFT:
                isLeftPressed = true;
                isRightPressed = false;
                break;
            case RIGHT:
                isRightPressed = true;
                isLeftPressed = false;
                break;
            case SPACE:
                if (isGameStopped) {
                    createGame();
                }
                break;
        }
    }

    @Override
    public void setScore(int score) {
        super.setScore(score);
    }

    @Override
    public void onTurn(int step) {
        rocket.move(isUpPressed, isLeftPressed, isRightPressed);
        check();
        drawScene();
        if (score > 0) {
            score--;
        }
        setScore(score);
    }

    @Override
    public void setCellColor(int x, int y, Color color) {
        if (0 <= x && x < WIDTH && 0 <= y && y < HEIGHT) {
            super.setCellColor(x, y, color);
        }
    }

    private void createGame() {
        createGameObjects();
        drawScene();
        setTurnTimer(50);
        isUpPressed = false;
        isLeftPressed = false;
        isRightPressed = false;
        isGameStopped = false;
        score = 1000;
    }

    private void drawScene() {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                setCellColor(x, y, Color.ORANGE);
            }
        }
        rocket.draw(this);
        landscape.draw(this);
    }

    private void createGameObjects() {
        rocket = new Rocket(WIDTH / 2, 0);
        landscape = new GameObject(0, 25, ShapeMatrix.LANDSCAPE);
        platform = new GameObject(23, MoonLanderGame.HEIGHT - 1, ShapeMatrix.PLATFORM);
    }

    private void check() {
        if (rocket.isCollision(platform) && rocket.isStopped()) {
            win();
        } else if (rocket.isCollision(landscape)) {
            gameOver();
        }
    }

    private void win() {
        rocket.land();
        isGameStopped = true;
        showMessageDialog(Color.ALICEBLUE, "You win!", Color.BLACK, 50);
        stopTurnTimer();
    }

    private void gameOver() {
        rocket.crash();
        isGameStopped = true;
        showMessageDialog(Color.BISQUE, "Game over", Color.CHOCOLATE, 50);
        stopTurnTimer();
        score = 0;
    }
}
