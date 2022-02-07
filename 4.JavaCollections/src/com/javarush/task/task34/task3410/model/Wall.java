package com.javarush.task.task34.task3410.model;

import java.awt.Color;
import java.awt.Graphics;

public class Wall extends CollisionObject {

    public Wall(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(new Color(105, 80, 0));

        graphics.drawRect(x - width / 2, y - height / 2, width, height);
    }
}
