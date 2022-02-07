package com.javarush.games.minigames.mini03;

import com.javarush.engine.cell.Game;
import com.javarush.engine.cell.Color;

/* 
Простая программа
*/

public class SymbolGame extends Game {

    @Override
    public void initialize() {
        final int width = 8;
        final int height = 3;
        final String JavaRush = "JAVARUSH";

        setScreenSize(width, height);
        for (int x = 0; x < width; x++) {
            setCellValueEx(x, 1, Color.ORANGE, String.valueOf(JavaRush.charAt(x)));
        }
    }
}

