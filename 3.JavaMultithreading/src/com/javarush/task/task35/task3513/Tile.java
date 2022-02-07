package com.javarush.task.task35.task3513;

import java.awt.Color;
import java.util.Objects;

public class Tile {

    int value;

    public Tile() {
        value = 0;
    }

    public Tile(int value) {
        this.value = value;
    }

    public boolean isEmpty() {
        return value == 0;
    }

    public Color getFontColor() {
        return new Color(value < 16 ? 0x776e65 : 0xf9f6f2);
    }

        public Color getTileColor() {
            int[] colors = new int[]{
                    (0xff0000),
                    (0xeee4da),
                    (0xede0c8),
                    (0xf2b179),
                    (0xf59563),
                    (0xf67c5f),
                    (0xf65e3b),
                    (0xedcf72),
                    (0xedcc61),
                    (0xedc850),
                    (0xedc53f),
                    (0xedc22e),
                    (0xcdc1b4)
            };

            return new Color(colors[index(colors.length)]);
        }

        private int index(int length) {
            int index = 0;

            if (value <= 2048 && Integer.bitCount(value) <= 1){
                index = value == 0 ? length - 1 : Integer.numberOfTrailingZeros(value);
            }
            return index;
        }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Tile)) {
            return false;
        }

        final Tile tile = (Tile) obj;
        return Objects.equals(tile.value, this.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public int getValue() {
        return value;
    }
}
