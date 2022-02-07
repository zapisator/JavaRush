package com.javarush.games.minigames.mini08;

import com.javarush.engine.cell.*;

/* 
Работа с клавиатурой
*/

public class KeyboardGame extends Game {

    private static final int width = 3;
    private static final int height = 3;

    @Override
    public void initialize() {
        setScreenSize(width, height);
        colorizeWithWhite();
    }

    private void colorizeWithWhite() {
        for (int x = 0; x < width; x++) {
            colorizeColumn(x);
        }
    }

    private void colorizeColumn(int x) {
        for (int y = 0; y < height; y++) {
            setCellColor(x, y, Color.WHITE);
        }
    }

    @Override
    public void onKeyPress(Key key) {
        switch (key) {
            case LEFT:
                colorizeDown(0);
                break;
            case RIGHT:
                colorizeDown(width - 1);
                break;
            case UP:
                colorizeRight(0);
                break;
            case DOWN:
                colorizeRight(height - 1);
                break;
        }
    }

    private void colorizeDown(int x) {
        for (int y = 0; y < height; y++) {
            setCellColor(x, y, Color.GREEN);
        }
    }

    private void colorizeRight(int y) {
        for (int x = 0; x < height; x++) {
            setCellColor(x, y, Color.GREEN);
        }
    }

    @Override
    public void onKeyReleased(Key key) {
        switch (key) {
            case LEFT:
            case RIGHT:
            case UP:
            case DOWN:
                colorizeWithWhite();
                break;
        }
    }

}
