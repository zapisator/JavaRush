package com.javarush.task.task34.task3410.model;

import java.awt.Color;
import java.awt.Graphics;

public class Home extends GameObject {

    public Home(int x, int y) {
        super(x, y, 2, 2);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.RED);

        graphics.drawRect(x - width / 2, y - height / 2, width, height);
    }
}
