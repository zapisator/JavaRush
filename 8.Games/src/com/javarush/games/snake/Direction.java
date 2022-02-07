package com.javarush.games.snake;

import com.javarush.engine.cell.Key;
public enum Direction {
    UP {
        @Override
        public int x(GameObject gameObject) {
            return gameObject.x;
        }

        @Override
        public int y(GameObject gameObject) {
            return gameObject.y - 1;
        }
    },
    RIGHT {
        @Override
        public int x(GameObject gameObject) {
            return gameObject.x + 1;
        }

        @Override
        public int y(GameObject gameObject) {
            return gameObject.y;
        }
    },
    DOWN {
        @Override
        public int x(GameObject gameObject) {
            return gameObject.x;
        }

        @Override
        public int y(GameObject gameObject) {
            return gameObject.y + 1;
        }
    },
    LEFT {
        @Override
        public int x(GameObject gameObject) {
            return gameObject.x - 1;
        }

        @Override
        public int y(GameObject gameObject) {
            return gameObject.y;
        }
    };

    public abstract int x(GameObject gameObject);
    public abstract int y(GameObject gameObject);

}
