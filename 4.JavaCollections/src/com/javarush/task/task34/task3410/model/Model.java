package com.javarush.task.task34.task3410.model;

import com.javarush.task.task34.task3410.controller.EventListener;
import java.nio.file.Paths;
import java.util.Set;

public class Model {

    public static final int FIELD_CELL_SIZE = 20;

    private EventListener eventListener;
    private GameObjects gameObjects;
    private int currentLevel = 1;
    private LevelLoader levelLoader = new LevelLoader(Paths.get(
            System.getProperty("user.dir")
                    + "/4.JavaCollections/src/com/javarush/task/task34/task3410/res/levels.txt"
    ));


    public void restartLevel(int level) {
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart() {
        restartLevel(currentLevel);
    }

    public void startNextLevel() {
        restartLevel(++currentLevel);
    }

    public GameObjects getGameObjects() {
        return gameObjects;
    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public void move(Direction direction) {
        final Player player = getGameObjects().getPlayer();

        if (!checkWallCollision(player, direction)
                && !checkBoxCollisionAndMoveIfAvailable(direction)) {
            player.move(direction);
            checkCompletion();
        }
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
        return gameObjects.getWalls().stream()
                .anyMatch(wall -> gameObject.isCollision(wall, direction));
    }

    public boolean checkBoxCollisionAndMoveIfAvailable(Direction direction) {
            final Box boxToBeMoved = gameObjects.getBoxes().stream()
                    .filter(box -> gameObjects.getPlayer().isCollision(box, direction))
                    .findAny()
                    .orElse(null);

            if (boxToBeMoved == null || notCollidesWithWallOrAnotherBox(boxToBeMoved, direction)) {
                if (boxToBeMoved != null) {
                    boxToBeMoved.move(direction);
                }
                return false;
            }
            return true;
    }

    public boolean notCollidesWithWallOrAnotherBox(Box boxToBeMoved, Direction direction) {
        return !checkWallCollision(boxToBeMoved, direction)
                && gameObjects.getBoxes().stream()
                .filter(box -> !boxToBeMoved.equals(box))
                .noneMatch(box -> boxToBeMoved.isCollision(box, direction));
    }

    public void checkCompletion() {
        final Set<Home> homes = gameObjects.getHomes();
        final boolean isComplete = gameObjects.getBoxes().stream()
                .allMatch(box -> homes.stream()
                        .anyMatch(home
                                -> home.getX() == box.getX()
                                && home.getY() == box.getY()
                        )
                );

        if (isComplete) {
            eventListener.levelCompleted(currentLevel);
        }
    }

}
