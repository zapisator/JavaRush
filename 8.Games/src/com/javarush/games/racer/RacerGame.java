package com.javarush.games.racer;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;
import com.javarush.engine.cell.Key;
import com.javarush.games.racer.road.RoadManager;

public class RacerGame extends Game {

    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;
    public static final int CENTER_X = WIDTH / 2;
    public static final int ROADSIDE_WIDTH = 14;
    private static final int RACE_GOAL_CARS_COUNT = 40;

    private PlayerCar player;
    private RoadMarking roadMarking;
    private RoadManager roadManager;
    private FinishLine finishLine;
    private ProgressBar progressBar;
    private int score;

    private boolean isGameStopped;

    @Override
    public void initialize() {
        showGrid(false);
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    @Override
    public void onTurn(int step) {
        if (roadManager.checkCrush(player)) {
            gameOver();
        } else if (finishLine.isCrossed(player)) {
            win();
        } else {
            roadManager.generateNewRoadObjects(this);
            if (roadManager.getPassedCarsCount() >= RACE_GOAL_CARS_COUNT) {
                finishLine.show();
            }
            moveAll();
        }
        score -= 5;
        setScore(score);
        drawScene();
    }

    private void createGame() {
        roadMarking = new RoadMarking();
        player = new PlayerCar();
        roadManager = new RoadManager();
        finishLine = new FinishLine();
        progressBar = new ProgressBar(RACE_GOAL_CARS_COUNT);
        score = 3500;
        drawScene();
        setTurnTimer(40);
        isGameStopped = false;
    }

    private void win() {
        isGameStopped = true;
        showMessageDialog(Color.YELLOWGREEN, "WIN", Color.RED, 50);
        stopTurnTimer();
    }

    private void drawScene() {
        drawField();
        roadMarking.draw(this);
        roadManager.draw(this);
        player.draw(this);
        finishLine.draw(this);
        progressBar.draw(this);
    }

    private void drawField() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                if (x == CENTER_X) {
                    setCellColor(x, y, Color.WHITE);
                } else if (x >= ROADSIDE_WIDTH && x < WIDTH - ROADSIDE_WIDTH) {
                    setCellColor(x, y, Color.DIMGREY);
                } else {
                    setCellColor(x, y, Color.GREEN);
                }
            }
        }
    }

    private void moveAll() {
        roadMarking.move(player.speed);
        roadManager.move(player.speed);
        player.move();
        finishLine.move(player.speed);
        progressBar.move(roadManager.getPassedCarsCount());
    }

    private void gameOver() {
        isGameStopped = true;
        showMessageDialog(Color.BLACK, "GAME OVER!", Color.RED, 50);
        stopTurnTimer();
        player.stop();
    }

    @Override
    public void setCellColor(int x, int y, Color color) {
        if (0 < x && x < WIDTH && 0 < y && y < HEIGHT) {
            super.setCellColor(x, y, color);
        }
    }

    @Override
    public void onKeyPress(Key key) {
        switch (key) {
            case SPACE:
                if (isGameStopped) {
                    createGame();
                }
                break;
            case UP:
                player.speed = 2;
                break;
            case RIGHT:
                player.setDirection(Direction.RIGHT);
                break;
            case LEFT:
                player.setDirection(Direction.LEFT);
                break;
            default:
        }
    }

    @Override
    public void onKeyReleased(Key key) {
        switch (key) {
            case UP:
                player.speed = 1;
                break;
            case RIGHT:
                if (player.getDirection() == Direction.RIGHT) {
                    player.setDirection(Direction.NONE);
                }
                break;
            case LEFT:
                if (player.getDirection() == Direction.LEFT) {
                    player.setDirection(Direction.NONE);
                }
                break;
            default:
        }
    }
}