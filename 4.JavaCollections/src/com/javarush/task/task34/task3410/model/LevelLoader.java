package com.javarush.task.task34.task3410.model;

import static com.javarush.task.task34.task3410.model.Model.FIELD_CELL_SIZE;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class LevelLoader {

    private final Path levels;
    private Map<Integer, GameObjects> levelGameObjects;

    public LevelLoader(Path levels) {
        this.levels = levels;
        levelGameObjects = new HashMap<>();
        fillLevelGameObjects();
    }

    public static void main(String[] args) {
        LevelLoader levelLoader = new LevelLoader(Paths.get(
                System.getProperty("user.dir")
                        + "/4.JavaCollections/src/com/javarush/task/task34/task3410/res/levels.txt"
        ));

        for (int i = 1; i <= 60; i++) {
            if (levelLoader.getLevel(i) != levelLoader.getLevel(i + 60)) {
                System.out.println(i);
            }
        }
        printLevel(levelLoader, 1);
    }

    private static void printLevel(LevelLoader levelLoader, int level) {
        final GameObjects gameObjects = levelLoader.getLevel(level);

        Stream.of(gameObjects.getAll())
                .flatMap(Set::stream)
                .sorted(Comparator.comparingInt(GameObject::getY).thenComparing(GameObject::getX))
                .forEach(gameObject -> System.out.printf(
                        "%6s |x:%3d|\t|y:%3d|%n",
                        gameObject.getClass().getSimpleName(),
                        value(gameObject.x),
                        value(gameObject.y)));
    }

    private static int value(int value) {
        return (value + FIELD_CELL_SIZE / 2) / FIELD_CELL_SIZE;
    }

    public GameObjects getLevel(int level) {
        if (level <= 0) {
            throw new ParseGameObjectException(level);
        }
        --level;
        level %= levelGameObjects.size();
        ++level;
        return levelGameObjects.get(level);
    }

    private void fillLevelGameObjects() {
        try {
            final List<String> levelMapLines = Files.readAllLines(levels);
            State state = State.INITIAL;
            RawLevel rawLevel = new RawLevel();
            State nextState;

            for (String levelMapLine : levelMapLines) {
                if (state == State.INITIAL) {
                    rawLevel = new RawLevel();
                    state = stateSwitcher(state, levelMapLine);
                } else if (state == State.MAZE) {
                    nextState = stateSwitcher(state, levelMapLine);
                    rawLevel.level = parseNumber(state, levelMapLine);
                    state = nextState;
                } else if (isSilentState(state)) {
                    state = stateSwitcher(state, levelMapLine);
                } else if (state == State.SIZE_X) {
                    nextState = stateSwitcher(state, levelMapLine);
                    rawLevel.sizeX = parseNumber(state, levelMapLine);
                    state = nextState;
                } else if (state == State.SIZE_Y) {
                    nextState = stateSwitcher(state, levelMapLine);
                    rawLevel.sizeY = parseNumber(state, levelMapLine);
                    state = nextState;
                } else {
                    state = parseLevelMapOrFinish(rawLevel, levelMapLine);
                }
            }
        } catch (IOException e) {
            throw new ParseGameObjectException(levels);
        }
    }

    private State parseLevelMapOrFinish(RawLevel rawLevel, String levelMapLine) {
        if (rawLevel.currentY <= rawLevel.sizeY) {
            rowSymbolsToGameObjects(rawLevel, levelMapLine);
            rawLevel.currentX = 1;
            ++rawLevel.currentY;
            return State.MAP;
        }
        if (hasEveryGameObjectsDescription(rawLevel)) {
            rawLevelToGameObjectsThenPut(rawLevel);
        }
        return State.INITIAL;
    }

    private int parseNumber(State state, String levelMapLine) {
        return Integer.parseInt(
                levelMapLine.substring(expectedStringStart(state).length()).trim()
        );
    }

    private boolean isSilentState(State state) {
        return state == State.FILE_OFFSET
                || state == State.END
                || state == State.LENGTH
                || state == State.SPACE;
    }

    private State stateSwitcher(State state, String levelMapLine) {
        if (!isExpectedString(state).test(levelMapLine)) {
            throw new ParseGameObjectException(state, levelMapLine);
        }
        switch (state) {
            case INITIAL:
                return State.MAZE;
            case MAZE:
                return State.FILE_OFFSET;
            case FILE_OFFSET:
                return State.SIZE_X;
            case SIZE_X:
                return State.SIZE_Y;
            case SIZE_Y:
                return State.END;
            case END:
                return State.LENGTH;
            case LENGTH:
                return State.SPACE;
            case SPACE:
                return State.MAP;
            default:
                throw new ParseGameObjectException(state);
        }
    }

    private void rawLevelToGameObjectsThenPut(RawLevel rawLevel) {
        final GameObjects objects = new GameObjects(
                rawLevel.walls,
                rawLevel.boxes,
                rawLevel.homes,
                rawLevel.player
        );

        if (!levelGameObjects.containsKey(rawLevel.level)) {
            levelGameObjects.put(rawLevel.level, objects);
        } else {
            throw new ParseGameObjectException(levels.getFileName(), rawLevel.level);
        }
    }

    private boolean hasEveryGameObjectsDescription(RawLevel rawLevel) {
        return !rawLevel.walls.isEmpty()
                && !rawLevel.boxes.isEmpty()
                && !rawLevel.homes.isEmpty()
                && rawLevel.player != null;
    }

    private Predicate<String> isExpectedString(State state) {
        if (state == State.MAP) {
            return s -> true;
        }
        if (state == State.SPACE) {
            return String::isEmpty;
        }
        return s -> s.startsWith(expectedStringStart(state));
    }

    private String expectedStringStart(State state) {
        switch (state) {
            case INITIAL:
                return "*****";
            case MAZE:
                return "Maze: ";
            case FILE_OFFSET:
                return "File offset: ";
            case SIZE_X:
                return "Size X: ";
            case SIZE_Y:
                return "Size Y: ";
            case END:
                return "End: ";
            case LENGTH:
                return "Length: ";
            case SPACE:
                return "<Empty string>";
            default:
                return "<Any string>";
        }
    }

    private void rowSymbolsToGameObjects(RawLevel rawLevel, String levelMapLine) {
        while (rawLevel.currentX <= rawLevel.sizeX) {
            switch (levelMapLine.charAt(rawLevel.currentX - 1)) {
                case 'X':
                    rawLevel.walls.add(
                            new Wall(computedN(rawLevel.currentX), computedN(rawLevel.currentY)));
                    break;
                case '&':
                    rawLevel.boxes.add(
                            new Box(computedN(rawLevel.currentX), computedN(rawLevel.currentY)));
                    rawLevel.homes.add(
                            new Home(computedN(rawLevel.currentX), computedN(rawLevel.currentY)));
                    break;
                case '*':
                    rawLevel.boxes.add(
                            new Box(computedN(rawLevel.currentX), computedN(rawLevel.currentY)));
                    break;
                case '.':
                    rawLevel.homes.add(
                            new Home(computedN(rawLevel.currentX), computedN(rawLevel.currentY)));
                    break;
                case '@':
                    rawLevel.player =
                            new Player(computedN(rawLevel.currentX), computedN(rawLevel.currentY));
                    break;
                default:
            }
            ++rawLevel.currentX;
        }
    }

    private int computedN(int currentValue) {
        final int offset = FIELD_CELL_SIZE / 2;
        return currentValue * FIELD_CELL_SIZE - offset;
    }

    private enum State {
        INITIAL, MAZE, FILE_OFFSET, SIZE_X, SIZE_Y, END, LENGTH, SPACE, MAP
        }

    private static class RawLevel {
        int level;
        int sizeX;
        int sizeY;
        int currentX = 1;
        int currentY = 1;
        final Set<Wall> walls = new HashSet<>();
        final Set<Box> boxes = new HashSet<>();
        final Set<Home> homes = new HashSet<>();

        Player player;

    }

    public class ParseGameObjectException extends RuntimeException {

        public ParseGameObjectException(State state, String levelMapLine) {
            super(String.format(
                    "Expected \"%s ...\", but has [%s].",
                    expectedStringStart(state), levelMapLine)
            );
        }

        public ParseGameObjectException(Path fileName, int level) {
            super(String.format(
                    "There are more then one level description in the %s of level %d.",
                    fileName, level)
            );
        }

        public ParseGameObjectException(Path fileName) {
            super("Could not read level file [" + fileName + "].");
        }

        public ParseGameObjectException(State state) {
            super("Illegal state switching for [" + state + "].");
        }

        public ParseGameObjectException(int level) {
            super("Level value [" + level + "] should be between more then 1.");
        }

    }

}
