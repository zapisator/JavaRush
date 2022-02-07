package com.javarush.games.minigames.mini04;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;

/* 
Считаем клетки
*/

public class CountGame extends Game {

    private static final int width = 10;
    private static final int height = 10;

    @Override
    public void initialize() {
        setScreenSize(10, 10);
        showGrid(false);
        for (int j = 0; j < 50; j++) {
            int x = getRandomNumber(10);
            int y = getRandomNumber(10);
            setCellColor(x, y, Color.GREEN);
            setCellNumber(x, y, getRandomNumber(100));
        }
        showResult();
    }

    public void showResult() {
        final Properties properties = new Properties();

        sumAndCountForAllX(properties);

        printSum(properties.sum);
        printCountOfGreenCells(properties.count);
    }

    private void sumAndCountForAllX(Properties properties) {
        for (int x = 0; x < width; x++) {
            sumAndCountForAllY(x, properties);
        }
    }

    private void sumAndCountForAllY(int x, Properties properties) {
        for (int y = 0; y < height; y++) {
            properties.sum += getCellNumber(x, y);
            properties.count += getCellColor(x, y) == Color.GREEN ? 1 : 0;
        }
    }

    private void printSum(int sum) {
        System.out.println("Сумма всех чисел = " + sum);
    }

    private void printCountOfGreenCells(int count) {
        System.out.println("Количество зеленых клеток = " + count);
    }

    private static class Properties {
        int sum = 0;
        int count = 0;
    }
}
