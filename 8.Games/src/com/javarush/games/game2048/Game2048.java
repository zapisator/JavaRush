package com.javarush.games.game2048;

import com.javarush.engine.cell.*;
import java.awt.Rectangle;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public class Game2048 extends Game {

    private static final int WIN_CRITERIA = 2048;
    private static final int SIDE = 4;
    private int[][] gameField;
    private boolean isGameStopped = false;
    private int score;

    @Override
    public void initialize() {
        setScreenSize(SIDE, SIDE);
        createGame();
        drawScene();
    }

    @Override
    public void onKeyPress(Key key) {
        if (!isGameStopped) {
            if (canUserMove()) {
                switch (key) {
                    case LEFT:
                        moveLeft();
                        break;
                    case RIGHT:
                        moveRight();
                        break;
                    case UP:
                        moveUp();
                        break;
                    case DOWN:
                        moveDown();
                        break;
                }
                if (key == Key.LEFT || key == Key.RIGHT || key == Key.UP || key == Key.DOWN) {
                    drawScene();
                }
            } else {
                gameOver();
            }
        } else {
            if (key == Key.SPACE) {
                isGameStopped = false;
                createGame();
                drawScene();
            }
        }
    }

    private boolean canUserMove() {
        return hasZero() || canCompress();
    }

    private boolean canCompress() {
        return IntStream.range(0, SIDE)
                .mapToObj(this::hasPair)
                .anyMatch(Boolean::booleanValue);
    }

    private boolean hasPair(int y) {
        return IntStream.range(0, SIDE)
                .mapToObj(x -> {
                    boolean result = false;
                    final Rectangle rectangle = new Rectangle(SIDE, SIDE);
                    if (gameField[y][x] != 0) {
                        if (rectangle.contains(x - 1, y)) {
                            result = gameField[y][x] == gameField[y][x - 1];
                        }
                        if (rectangle.contains(x, y - 1)) {
                            result = result || gameField[y][x] == gameField[y - 1][x];
                        }
                    }
                    return result;
                })
                .anyMatch(Boolean::booleanValue);
    }

    private boolean hasZero() {
        return IntStream.range(0, SIDE)
                .mapToObj(this::isRowWithZero)
                .anyMatch(Boolean::booleanValue);
    }

    private boolean isRowWithZero(int y) {
        return IntStream.range(0, SIDE)
                .mapToObj(x -> gameField[y][x] == 0)
                .anyMatch(Boolean::booleanValue);
    }

    private void win() {
        isGameStopped = true;
        showMessageDialog(Color.ORANGE, "WIN", Color.INDIGO, 80);
    }

    private void gameOver() {
        isGameStopped = true;
        showMessageDialog(Color.ORANGE, "GAME OVER", Color.INDIGO, 80);
    }

    private int getMaxTileValue() {
        return IntStream.range(0, SIDE)
                .map(this::maxByRow)
                .max()
                .orElseThrow(RuntimeException::new);
    }

    private int maxByRow(int y) {
        return Arrays.stream(gameField[y], 0, SIDE)
                .max()
                .orElseThrow(RuntimeException::new);
    }

    private void rotateClockwise() {
        final int[][] result = new int[SIDE][SIDE];

        IntStream.range(0, SIDE)
                .forEach(y -> IntStream.range(0, SIDE)
                        .forEach(x -> result[x][SIDE - y - 1] = gameField[y][x])
                );
        gameField = result;
    }

    private void moveLeft() {
        AtomicBoolean hadSmth = new AtomicBoolean(false);

        IntStream.range(0, SIDE)
                .forEach(y -> {
                    boolean doSomething;

                    doSomething = compressRow(gameField[y]);
                    doSomething = mergeRow(gameField[y]) || doSomething;
                    if (doSomething) {
                        compressRow(gameField[y]);
                    }
                    hadSmth.set(hadSmth.get() || doSomething);
                });
        if (hadSmth.get()) {
            createNewNumber();
        }
    }

    private void moveDown() {
        move(1);
    }

    private void moveRight() {
        move(2);
    }

    private void moveUp() {
        move(3);
    }

    private void move(int rotateBeforeMoveLeft) {
        for (int i = 1; i <= rotateBeforeMoveLeft; i++) {
            rotateClockwise();
        }
        moveLeft();
        for (int i = 1; i <= 4 - rotateBeforeMoveLeft; i++) {
            rotateClockwise();
        }
    }


    private void createGame() {
        gameField = new int[SIDE][SIDE];
        score = 0;
        setScore(score);
        createNewNumber();
        createNewNumber();
    }

    private void drawScene() {
        IntStream.range(0, SIDE)
                .forEach(y ->
                        IntStream.range(0, SIDE)
                                .forEach(x -> setCellColoredNumber(x, y, gameField[y][x])));
    }

    private void createNewNumber() {
        if (getMaxTileValue() == WIN_CRITERIA) {
            win();
        }

        int x = getRandomNumber(SIDE);
        int y = getRandomNumber(SIDE);

        while (gameField[y][x] != 0) {
            x = getRandomNumber(SIDE);
            y = getRandomNumber(SIDE);
        }
        gameField[y][x] = getRandomNumber(10) == 0 ? 4 : 2;
    }

    private Color getColorByValue(int value) {
        return new Color[]{
                Color.WHITE,
                Color.PLUM,
                Color.SLATEBLUE,
                Color.DODGERBLUE,
                Color.DARKTURQUOISE,
                Color.MEDIUMSEAGREEN,
                Color.LIMEGREEN,
                Color.DARKORANGE,
                Color.SALMON,
                Color.ORANGERED,
                Color.DEEPPINK,
                Color.MEDIUMVIOLETRED
        }[value < 2 ? 0 : Integer.numberOfTrailingZeros(value)];
    }

    private void setCellColoredNumber(int x, int y, int value) {
        setCellValueEx(x, y, getColorByValue(value), value == 0 ? "" : String.valueOf(value));
    }

    private boolean compressRow(int[] row) {
        final IntPredicate filterZero = (int number) -> row[number] == 0;
        final IntPredicate filterNotZero = (int number) -> row[number] != 0;
        int zeroIndex = indexOf(row, filterZero);
        int notZeroIndex = indexOf(row, filterNotZero);
        boolean doCompression = zeroIndex != -1 && notZeroIndex != -1;

        if (doCompression && zeroIndex > notZeroIndex) {
            notZeroIndex = indexOf(row, zeroIndex, filterNotZero);
            doCompression = notZeroIndex != -1;
        }

        while (zeroIndex != -1 && notZeroIndex != -1) {
            swap(row, zeroIndex, notZeroIndex);
            zeroIndex++;
            notZeroIndex = indexOf(row, notZeroIndex, filterNotZero);
        }
        return doCompression;
    }

    private void swap(int[] row, int zeroIndex, int notZeroIndex) {
        row[zeroIndex] = row[notZeroIndex];
        row[notZeroIndex] = 0;
    }

    private int indexOf(int[] row, int startIndex, IntPredicate filter) {
        return IntStream.range(startIndex, row.length)
                .filter(filter)
                .findFirst()
                .orElse(-1);
    }

    private int indexOf(int[] row, IntPredicate filter) {
        return indexOf(row, 0, filter);
    }

    private boolean mergeRow(int[] row) {
        int laggingIndex = 0;
        boolean doMerge = false;

        for (int i = 1; i < row.length; i++) {
            if (row[i] != 0 && row[laggingIndex] == row[i]) {
                row[laggingIndex] *= 2;
                score += row[laggingIndex];
                setScore(score);
                row[i] = 0;
                doMerge = true;
            }
            laggingIndex++;
        }
        return doMerge;
    }

}
