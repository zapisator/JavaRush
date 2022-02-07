package com.javarush.task.task34.task3410.model;

import static com.javarush.task.task34.task3410.model.Model.FIELD_CELL_SIZE;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends CollisionObject implements Movable {

    public Player(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.ORANGE);

        graphics.drawOval(x - width / 2, y - height / 2, width, height);
    }

    @Override
    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }

    @Override
    public void move(Direction direction) {
        switch (direction) {
            case LEFT:
                move (-FIELD_CELL_SIZE, 0);
                break;
            case RIGHT:
                move (+FIELD_CELL_SIZE, 0);
                break;
            case UP:
                move(0, -FIELD_CELL_SIZE);
                break;
            case DOWN:
                move(0, +FIELD_CELL_SIZE);
                break;
            default:
        }
    }

}
