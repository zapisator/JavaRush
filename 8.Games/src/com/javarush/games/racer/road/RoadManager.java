package com.javarush.games.racer.road;

import com.javarush.engine.cell.Game;
import com.javarush.games.racer.PlayerCar;
import com.javarush.games.racer.RacerGame;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RoadManager {
    public static final int LEFT_BORDER = RacerGame.ROADSIDE_WIDTH;
    public static final int RIGHT_BORDER = RacerGame.WIDTH - RacerGame.ROADSIDE_WIDTH;
    private static final int FIRST_LANE_POSITION = 16;
    private static final int FOURTH_LANE_POSITION = 44;
    private static final int PLAYER_CAR_DISTANCE = 12;
    private List<RoadObject> items = new ArrayList<>();
    private int passedCarsCount = 0;

    private RoadObject createRoadObject(RoadObjectType type, int x, int y) {
        if (type == RoadObjectType.THORN) {
            return new Thorn(x, y);
        } else if (type == RoadObjectType.DRUNK_CAR) {
            return new MovingCar(x, y);
        } else {
            return new Car(type, x, y);
        }
    }

    private void addRoadObject(RoadObjectType roadObjectType, Game game) {
        int x = game.getRandomNumber(FIRST_LANE_POSITION, FOURTH_LANE_POSITION);
        int y = -1 * RoadObject.getHeight(roadObjectType);
        final RoadObject roadObject = createRoadObject(roadObjectType, x, y);

        if (isRoadSpaceFree(roadObject)) {
            items.add(roadObject);
        }
    }

    public void draw(Game game) {
        items.forEach(roadObject -> roadObject.draw(game));
    }

    public void move(int boost) {
        items.forEach(roadObject -> roadObject.move(boost + roadObject.speed, items));
        deletePassedItems();
    }

    private boolean isThornExists() {
        return items.stream()
                .anyMatch(Thorn.class::isInstance);
    }

    private void generateThorn(Game game) {
        final int randomNumber = game.getRandomNumber(100);

        if (randomNumber < 10 && !isThornExists()) {
            addRoadObject(RoadObjectType.THORN, game);
        }
    }

    public void generateNewRoadObjects(Game game) {
        generateThorn(game);
        generateRegularCar(game);
        generateMovingCar(game);
    }

    private void deletePassedItems() {
        final List<RoadObject> objectsToDelete = items.stream()
                .filter(roadObject -> roadObject.y >= RacerGame.HEIGHT)
                .collect(Collectors.toList());

        if (!objectsToDelete.isEmpty()) {
            passedCarsCount += objectsToDelete.stream()
                    .filter(roadObject -> !(roadObject instanceof Thorn))
                    .count();
            items.removeAll(objectsToDelete);
        }
    }

    public boolean checkCrush(PlayerCar playerCar) {
        return items.stream()
                .anyMatch(roadObject -> roadObject.isCollision(playerCar));
    }

    private void generateRegularCar(Game game) {
        if (game.getRandomNumber(100) < 30) {
            final int carTypeNumber = game.getRandomNumber(4);

            addRoadObject(RoadObjectType.values()[carTypeNumber], game);
        }
    }

    private boolean isRoadSpaceFree(RoadObject object) {
        return items.stream()
                .noneMatch(roadObject
                        -> roadObject.isCollisionWithDistance(object, PLAYER_CAR_DISTANCE)
                );
    }

    private boolean isMovingCarExists() {
        return items.stream()
                .anyMatch(MovingCar.class::isInstance);
    }

    private void generateMovingCar(Game game) {
        if (game.getRandomNumber(100) < 10 && !isMovingCarExists()) {
            addRoadObject(RoadObjectType.DRUNK_CAR, game);
        }
    }

    public int getPassedCarsCount() {
        return passedCarsCount;
    }
}
