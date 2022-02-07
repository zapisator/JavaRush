package com.javarush.games.minesweeper;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;

import java.util.ArrayList;
import java.util.List;

public class MinesweeperGame extends Game {

    private static final int SIDE = 9;
    private static final String MINE = "\uD83D\uDCA3";
    private static final String FLAG = "\uD83D\uDEA9";

    private GameObject[][] gameField = new GameObject[SIDE][SIDE];
    private int countMinesOnField;
    private int countFlags;
    private int countClosedTiles = SIDE * SIDE;
    private int score;
    private boolean isGameStopped;

    @Override
    public void initialize() {
        setScreenSize(SIDE, SIDE);
        createGame();
    }

    private void createGame() {
        for (int y = 0; y < SIDE; y++) {
            for (int x = 0; x < SIDE; x++) {
                boolean isMine = getRandomNumber(10) < 1;
                if (isMine) {
                    countMinesOnField++;
                }
                gameField[y][x] = new GameObject(x, y, isMine);
                setCellColor(x, y, Color.ORANGE);
                setCellValue(x, y, "");
            }
        }
        countFlags = countMinesOnField;
        countMineNeighbors();
//        isGameStopped = false;
    }

    private void restart() {
        isGameStopped = false;
        countClosedTiles = SIDE * SIDE;
        countMinesOnField = 0;
        score = 0;
        setScore(score);
        createGame();
    }

    @Override
    public void onMouseRightClick(int x, int y) {
        if (!isGameStopped) {
            markTile(x, y);
        }
    }

    private void markTile(int x, int y) {
        final GameObject gameObject = gameField[y][x];

        if (!gameObject.isOpen) {
            if (!gameObject.isFlag && countFlags > 0) {
                gameField[y][x].isFlag = true;
                countFlags--;
                setCellValue(x, y, FLAG);
                setCellColor(x, y, Color.YELLOW);
            } else if (gameObject.isFlag) {
                gameObject.isFlag = false;
                countFlags++;
                setCellValue(x, y, "");
                setCellColor(x, y, Color.ORANGE);
            }
        }
    }

    @Override
    public void onMouseLeftClick(int x, int y) {
        if (!isGameStopped) {
            openTile(x, y);
        } else {
            restart();
        }
    }

    private void openTile(int x, int y) {
        final GameObject gameObject = gameField[y][x];

        if (!gameObject.isFlag && !gameObject.isOpen) {
            gameObject.isOpen = true;
            if (gameObject.isMine) {
                setCellValueEx(x, y, Color.RED, MINE);
                gameOver();
            } else {
                score += 5;
                setScore(score);
                setCellColor(x, y, Color.WHITE);
                countClosedTiles--;
                winIfClosedTilesEqualsMinesOnField();
                if (gameObject.countMineNeighbors == 0) {
                    for (GameObject object : getNeighbors(gameObject)) {
                        if (!object.isOpen) {
                            openTile(object.x, object.y);
                        }
                    }
                    setCellValue(x, y, "");
                } else {
                    setCellNumber(x, y, gameObject.countMineNeighbors);
                }
            }
        }
    }

    private void gameOver() {
        isGameStopped = true;
        showMessageDialog(Color.AQUA, "You lost!", Color.BLACK, 100);
    }

    private void winIfClosedTilesEqualsMinesOnField() {
        if (countClosedTiles == countMinesOnField) {
            isGameStopped = true;
            showMessageDialog(Color.AQUA, "You have won, succer!", Color.BLACK, 100);
        }
    }

    private void countMineNeighbors() {
        for (int y = 0; y < SIDE; y++) {
            for (int x = 0; x < SIDE; x++) {
                final GameObject gameObject = gameField[y][x];

                if (!gameObject.isMine) {
                    gameObject.countMineNeighbors = getNeighbors(gameObject).stream()
                            .mapToInt(object -> object.isMine ? 1 : 0)
                            .sum();
//                    setCellNumber(x, y, gameObject.countMineNeighbors);
                }
            }
        }
    }

    private List<GameObject> getNeighbors(GameObject gameObject) {
        List<GameObject> result = new ArrayList<>();
        for (int y = gameObject.y - 1; y <= gameObject.y + 1; y++) {
            for (int x = gameObject.x - 1; x <= gameObject.x + 1; x++) {
                if (y < 0 || y >= SIDE) {
                    continue;
                }
                if (x < 0 || x >= SIDE) {
                    continue;
                }
                if (gameField[y][x] == gameObject) {
                    continue;
                }
                result.add(gameField[y][x]);
            }
        }
        return result;
    }
}