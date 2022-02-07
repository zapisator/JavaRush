package com.javarush.games.snake;

import com.javarush.engine.cell.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Snake {

    private static final String HEAD_SIGN = "\uD83D\uDC7E";
    private static final String BODY_SIGN = "\u26AB";
    private final List<GameObject> snakeParts = new ArrayList<>();
    public boolean isAlive = true;
    private Direction direction = Direction.LEFT;

    public Snake(int x, int y) {
        IntStream.range(x, x + 3)
                .forEach(x1 -> snakeParts.add(new GameObject(x1, y)));
    }

    public void draw(Game game) {
        final Color snakeColor = isAlive ? Color.GREEN : Color.RED;

        game.setCellValueEx(snakeParts.get(0).x, snakeParts.get(0).y, Color.NONE, HEAD_SIGN,
                snakeColor, 75);
        IntStream.range(1, snakeParts.size())
                .forEach(index -> {
                    final GameObject body = snakeParts.get(index);
                    game.setCellValueEx(body.x, body.y, Color.NONE, BODY_SIGN, snakeColor, 75);
                });
    }

    public void setDirection(Direction direction) {
        if (isChangingDirection(direction)) {
            this.direction = direction;
        }
    }

    private boolean isChangingDirection(Direction direction) {
        return !isOpposite(direction) &&
                (doWantMoveVertically(direction) && isMovingHorizontally()
                || !doWantMoveVertically(direction) && isMovingVertically());
    }

    private boolean isMovingVertically() {
        return snakeParts.get(0).y != snakeParts.get(1).y;
    }

    private boolean isMovingHorizontally() {
        return snakeParts.get(0).x != snakeParts.get(1).x;
    }

    private boolean doWantMoveVertically(Direction direction) {
        return direction == Direction.UP || direction == Direction.DOWN;
    }

    public void move(Apple apple) {
        final GameObject newHead = createNewHead();

        if (newHead.x < 0 || SnakeGame.WIDTH <= newHead.x || newHead.y < 0
                || SnakeGame.HEIGHT <= newHead.y || checkCollision(newHead)) {
            isAlive = false;
        } else {
            snakeParts.add(0, newHead);
            if (apple.x == newHead.x && apple.y == newHead.y) {
                apple.isAlive = false;
            } else {
                removeTail();
            }
        }
    }

    public GameObject createNewHead() {
        final GameObject head = snakeParts.get(0);

        return new GameObject(direction.x(head), direction.y(head));
    }

    public void removeTail() {
        snakeParts.remove(snakeParts.size() - 1);
    }

    public boolean isOpposite(Direction key) {
        boolean decision = false;

        switch (direction) {
            case LEFT:
                decision = key == Direction.RIGHT;
                break;
            case UP:
                decision = key == Direction.DOWN;
                break;
            case RIGHT:
                decision = key == Direction.LEFT;
                break;
            case DOWN:
                decision = key == Direction.UP;
                break;
        }
        return decision;
    }

    public boolean checkCollision(GameObject gameObject) {
        return snakeParts.stream()
                .anyMatch(snakePart -> snakePart.x == gameObject.x && snakePart.y == gameObject.y);
    }

    public int getLength() {
        return snakeParts.size();
    }
}
