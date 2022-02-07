package com.javarush.games.racer;

import com.javarush.engine.cell.Game;

import java.util.ArrayList;
import java.util.List;

public class RoadMarking {

    private final List<GameObject> roadMarking = new ArrayList<>();

    public RoadMarking() {
        for (int i = -4; i <= RacerGame.HEIGHT + 4; i += 8) {
            roadMarking.add(new GameObject(RacerGame.CENTER_X - 9, i, ShapeMatrix.ROAD_MARKING));
            roadMarking.add(new GameObject(RacerGame.CENTER_X + 9, i, ShapeMatrix.ROAD_MARKING));
        }
    }

    public void move(int boost) {
        final int offset = RacerGame.HEIGHT + 8;

        roadMarking.forEach(gameObject -> gameObject.y
                = boost
                + (gameObject.y > RacerGame.HEIGHT
                ? gameObject.y - offset
                : 0)
        );
    }

    public void draw(Game game) {
        roadMarking.forEach(gameObject -> gameObject.draw(game));
    }
}
