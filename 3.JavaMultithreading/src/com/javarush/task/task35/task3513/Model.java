package com.javarush.task.task35.task3513;

import java.awt.Rectangle;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Model {

    private static final int FIELD_WIDTH = 4;
    private final Move[] moves = new Move[]{this::left, this::right, this::up, this::down};
    private final Stack<Tile[][]> previousStates = new Stack<>();
    private final Stack<Integer> previousScores = new Stack<>();
    private boolean isSaveNeeded = true;
    private Tile[][] gameTiles;
    int score;
    int maxTile;

    public Model() {
        resetGameTiles();
    }

    public void autoMove() {
        final PriorityQueue<MoveEfficiency> queue
                = new PriorityQueue<>(4, Collections.reverseOrder());

        for (Move move : moves) {
            queue.offer(getMoveEfficiency(move));
        }
        queue.peek().getMove().move();
    }

    public boolean hasBoardChanged() {
        return !previousStates.isEmpty() && tilesWeight(gameTiles) != tilesWeight(previousStates.peek());
    }

    private int tilesWeight(Tile[][] gameTiles) {
        return Arrays.stream(gameTiles)
                .mapToInt(tiles -> Arrays.stream(tiles)
                        .mapToInt(Tile::getValue)
                        .sum())
                .sum();
    }

    public MoveEfficiency getMoveEfficiency(Move move) {
        move.move();
        final MoveEfficiency moveEfficiency = !hasBoardChanged()
                ? new MoveEfficiency(-1, 0, move)
                : new MoveEfficiency(getEmptyTiles().size(), score, move);
        rollback();
        return moveEfficiency;
    }

    public void randomMove() {
        moves[((int) (Math.random() * 100)) % 4].move();
    }

    private void saveState(Tile[][] state) {
        previousStates.push(copy(state));
        previousScores.push(score);
        isSaveNeeded = false;
    }

    public void rollback() {
        if (!previousStates.isEmpty() && !previousScores.isEmpty()) {
            gameTiles = previousStates.pop();
            score = previousScores.pop();
        }
    }
//
//    public static void main(String[] args) {
//        Model model = new Model();
//
////         для merge
//        final Tile[][] tilesS = new Tile[][]{
//                {new Tile(8), new Tile(0), new Tile(0), new Tile(0)},
//                {new Tile(4), new Tile(2), new Tile(2), new Tile(4)},
//                {new Tile(4), new Tile(4), new Tile(4), new Tile(0)},
//                {new Tile(4), new Tile(4), new Tile(4), new Tile(4)}
//        };
//        model.gameTiles = tilesS;
//        model.saveState(tilesS);
//        tilesS[0][0].value = 99;
//        System.out.println(Arrays.deepToString(model.gameTiles));
//        System.out.println();
//        System.out.println(Arrays.deepToString(model.previousStates.peek()));
//
////        // До
////        for (Tile[] tile : model.gameTiles) {
////            System.out.println(Arrays.toString(tile));
////        }
////        System.out.println();
////        model.rotateClockwise();
//////        model.left();
//////        for (Tile[] tile : tiles) {
//////            System.out.println(model.mergeTiles(tile));
//////        }
////        System.out.println();
////        //После
////        for (Tile[] tile : model.gameTiles) {
////            System.out.println(Arrays.toString(tile));
////        }
//    }

    public void left() {
        if (isSaveNeeded) {
            saveState(gameTiles);
        }
        final boolean hasChanged = Arrays.stream(gameTiles)
                .map(this::mergeTiles)
                .reduce(false, Boolean::logicalOr);

        if (hasChanged) {
            addTile();
            isSaveNeeded = true;
        }
    }

    public void up() {
        saveState(gameTiles);
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
        left();
        rotateClockwise();
    }

    public void down() {
        saveState(gameTiles);
        rotateClockwise();
        left();
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
    }

    public void right() {
        saveState(gameTiles);
        rotateClockwise();
        rotateClockwise();
        left();
        rotateClockwise();
        rotateClockwise();
    }

    private void rotateClockwise() {
        final Tile[][] result = newField();

        IntStream.range(0, FIELD_WIDTH)
                .forEach(y -> IntStream.range(0, FIELD_WIDTH)
                        .forEach(x -> result[x][FIELD_WIDTH - y - 1].value = gameTiles[y][x].value)
                );

        System.out.println("result");
        for (Tile[] tiles : result) {
            System.out.println(Arrays.toString(tiles));
        }
        System.out.println();

        gameTiles = result;
    }

    private boolean compressTiles(Tile[] tiles) {
        final Tile[] copy = copy(tiles);
        int zero = IntStream.range(0, tiles.length)
                .filter(number -> tiles[number].value == 0)
                .findFirst()
                .orElse(tiles.length);

        for (int i = zero + 1; i < tiles.length; i++) {
            if (tiles[i].value != 0) {
                tiles[zero].value = tiles[i].value;
                tiles[i].value = 0;
                zero++;
            }
        }
        return !Arrays.equals(copy, tiles);
    }

    private boolean mergeTiles(Tile[] tiles) {
        int previous = 0;
        boolean hasChanged;

        hasChanged = compressTiles(tiles);
        final Tile[] copy = copy(tiles);
        for (int current = previous + 1; current < tiles.length; current++) {
            if (tiles[previous].value != 0 && tiles[previous].value == tiles[current].value) {
                final int newValue = tiles[current].value * 2;
                tiles[previous].value = newValue;
                tiles[current].value = 0;
                score += newValue;
                maxTile = Math.max(maxTile, newValue);
            }
            previous++;
        }
        hasChanged = hasChanged || !Arrays.equals(copy, tiles);
        return hasChanged | compressTiles(tiles);
    }

    private List<Tile> getEmptyTiles() {
        return Arrays.stream(gameTiles)
                .flatMap(gameTile -> Arrays.stream(gameTile)
                        .filter(tile -> tile.value == 0))
                .collect(Collectors.toList());
    }

    public void addTile() {
        final List<Tile> emptyTiles = getEmptyTiles();

        if (!emptyTiles.isEmpty()) {
            emptyTiles.get((int) (emptyTiles.size() * Math.random())).value
                    = (Math.random() < 0.9 ? 2 : 4);
        }
    }

    public void resetGameTiles() {
        gameTiles = newField();
        addTile();
        addTile();
        score = 0;
        maxTile = 0;
    }

    private Tile[][] newField() {
        final Tile[][] newField = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        int bound = newField.length;

        for (int i = 0; i < bound; i++) {
            int y = i;
            IntStream.range(0, newField[y].length)
                    .forEach(x -> newField[y][x] = new Tile());
        }
        return newField;
    }

    private Tile[][] copy(Tile[][] gameTiles) {
        return Arrays.stream(gameTiles)
                .map(this::copy)
                .toArray(Tile[][]::new);
    }

    private Tile[] copy(Tile[] tiles) {
        return Arrays.stream(tiles)
                .map(tile -> new Tile(tile.value))
                .toArray(Tile[]::new);
    }

    public boolean canMove() {
        return hasZero() || canCompress();
    }

    private boolean hasZero() {
        return IntStream.range(0, FIELD_WIDTH)
                .mapToObj(this::isRowWithZero)
                .anyMatch(Boolean::booleanValue);
    }

    private boolean isRowWithZero(int y) {
        return IntStream.range(0, FIELD_WIDTH)
                .mapToObj(x -> gameTiles[y][x].value == 0)
                .anyMatch(Boolean::booleanValue);
    }

    private boolean canCompress() {
        return IntStream.range(0, FIELD_WIDTH)
                .mapToObj(this::hasPair)
                .anyMatch(Boolean::booleanValue);
    }

    private boolean hasPair(int y) {
        return IntStream.range(0, FIELD_WIDTH)
                .mapToObj(x -> {
                    boolean result = false;
                    final Rectangle rectangle = new Rectangle(FIELD_WIDTH, FIELD_WIDTH);
                    if (gameTiles[y][x].value != 0) {
                        if (rectangle.contains(x - 1, y)) {
                            result = gameTiles[y][x].value == gameTiles[y][x - 1].value;
                        }
                        if (rectangle.contains(x, y - 1)) {
                            result = result || gameTiles[y][x].value == gameTiles[y - 1][x].value;
                        }
                    }
                    return result;
                })
                .anyMatch(Boolean::booleanValue);
    }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }
}
