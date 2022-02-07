package com.javarush.games.minigames.mini05;

import com.javarush.engine.cell.Game;
import com.javarush.engine.cell.Color;

/* 
Цвета радуги
*/

public class RainbowGame extends Game {

    private static final int width = 10;
    private static final int height = 7;
    private static final Color[] colors = {
            Color.RED,
            Color.ORANGE,
            Color.YELLOW,
            Color.GREEN,
            Color.BLUE,
            Color.INDIGO,
            Color.VIOLET
    };

    @Override
    public void initialize() {
        setScreenSize(width, height);
        colorize();
    }

    private void colorize() {
        for (int color = 0; color < colors.length; color++) {
            colorizeLine(color);
        }
    }

    private void colorizeLine(int y) {
        final Color color = colors[y];

        for (int x = 0; x < width; x++) {
            setCellColor(x, y, color);
        }
    }
}
