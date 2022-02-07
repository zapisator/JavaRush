package com.javarush.games.snake;

import com.javarush.engine.cell.*;
import java.util.stream.IntStream;

public class SnakeGame extends Game {

    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;
    private static final int GOAL = 28;
    private Snake snake;
    private Apple apple;
    private int turnDelay;
    private boolean isGameStopped;
    private int score;

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    @Override
    public void onTurn(int step) {
        snake.move(apple);
        if (!apple.isAlive) {
            createNewApple();
            score += 5;
            setScore(score);
            turnDelay -= 10;
            setTurnTimer(turnDelay);
        }
        if (!snake.isAlive) {
            gameOver();
        }
        if (snake.getLength() > GOAL) {
            win();
        }

        drawScene();
    }

    @Override
    public void onKeyPress(Key key) {
        switch (key) {
            case LEFT:
                snake.setDirection(Direction.LEFT);
                break;
            case RIGHT:
                snake.setDirection(Direction.RIGHT);
                break;
            case UP:
                snake.setDirection(Direction.UP);
                break;
            case DOWN:
                snake.setDirection(Direction.DOWN);
                break;
            case SPACE:
                if (isGameStopped) {
                    createGame();
                }
        }
    }

    private void createGame() {
        score = 0;
        setScore(score);
        snake = new Snake(WIDTH / 2, HEIGHT / 2);
        turnDelay = 300;
        setTurnTimer(turnDelay);
        createNewApple();
        isGameStopped = false;
        drawScene();
    }

    private void drawScene() {
        colorizeField();
        snake.draw(this);
        apple.draw(this);
    }

    private void colorizeField() {
        IntStream.range(0, WIDTH)
            .forEach(this::setColorForEachRow);
    }

    private void setColorForEachRow(int y) {
        IntStream.range(0, HEIGHT)
                .forEach(x -> setCellValueEx(x, y, Color.DARKSEAGREEN, ""));
    }

    private void createNewApple() {
        Apple apple = newApple();

        while (snake.checkCollision(apple)) {
            apple = newApple();
        }
        this.apple = apple;
    }

    private Apple newApple() {
        final int x = getRandomNumber(WIDTH);
        final int y = getRandomNumber(HEIGHT);

        return new Apple(x, y);
    }

    private void gameOver() {
        stopGame(Color.ANTIQUEWHITE, "GAME OVER", Color.CHOCOLATE, 75);
    }

    private void win() {
        stopGame(Color.AQUA, "YOU WIN", Color.GREEN, 75);
    }

    private void stopGame(Color backgroundColor, String message, Color textColor, int textSize) {
        stopTurnTimer();
        isGameStopped = true;
        showMessageDialog(backgroundColor, message, textColor, textSize);
    }
}
