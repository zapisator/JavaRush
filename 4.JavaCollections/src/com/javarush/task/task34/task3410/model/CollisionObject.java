package com.javarush.task.task34.task3410.model;

import static com.javarush.task.task34.task3410.model.Model.FIELD_CELL_SIZE;

public abstract class CollisionObject extends GameObject {

    public CollisionObject(int x, int y) {
        super(x, y);
    }

    public boolean isCollision(GameObject gameObject, Direction direction) {
        switch (direction) {
            case LEFT:
                return (x - FIELD_CELL_SIZE) == gameObject.getX() && y == gameObject.getY();
            case RIGHT:
                return (x + FIELD_CELL_SIZE) == gameObject.getX() && y == gameObject.getY();
            case UP:
                return x == gameObject.getX() && (y - FIELD_CELL_SIZE) == gameObject.getY();
            case DOWN:
                return x == gameObject.getX() && (y + FIELD_CELL_SIZE) == gameObject.getY();
            default:
                return false;
        }
    }

}
