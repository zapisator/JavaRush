package com.javarush.games.minigames.mini09;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;

/* 
Таймер
*/

public class TimerGame extends Game {

    private static final int width = 3;
    private static final int height = 3;

    @Override
    public void initialize() {
        setScreenSize(width, height);
        setTurnTimer(1000);
    }

    @Override
    public void onTurn(int step) {
        final Color color = step % 2 == 0 ? Color.GREEN : Color.ORANGE;
        colorize(color);
        setCellNumber(1, 1, step);
    }

    private void colorize(Color color) {
        for (int x = 0; x < width; x++) {
            colorizeColumn(x, color);
        }
    }

    private void colorizeColumn(int x, Color color) {
        for (int y = 0; y < height; y++) {
            setCellColor(x, y, color);
        }
    }
}
