package com.javarush.task.task20.task2027;

import java.util.Objects;

public class Line {

    int startX;
    int startY;
    int endX;
    int endY;

    public Line(int startX, int startY, int endX, int endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    public Line reverse() {
        return new Line(endX, endY, startX, startY);
    }

    @Override
    public String toString() {
        return String.format("(%d, %d) - (%d, %d)", startX, startY, endX, endY);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startX, startY, endX, endY);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Line)) {
            return false;
        }

        Line line = (Line) obj;

        return line.startX == startX
                && line.startY == startY
                && line.endX == endX
                && line.endY == endY
                ;
    }
}
