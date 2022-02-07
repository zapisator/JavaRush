package com.javarush.task.task34.task3410.model;

public interface Movable {
    void move(int x, int y);

    void move(Direction direction);
}
