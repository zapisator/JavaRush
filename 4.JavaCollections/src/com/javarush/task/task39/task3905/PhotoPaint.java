package com.javarush.task.task39.task3905;

import java.awt.Rectangle;

public class PhotoPaint {
    public boolean paintFill(Color[][] image, int x, int y, Color desiredColor) {
        final int leftX = x - 1;
        final int rightX = x + 1;
        final int upperY = y - 1;
        final int lowerY = y + 1;
        boolean hasColorized = false;

        if (new Rectangle(image[0].length, image.length).contains(x, y)
                && image[y][x] != desiredColor
        ) {
            final Color previous = image[y][x];

            paintFill(image, leftX, y, desiredColor, previous);
            paintFill(image, rightX, y, desiredColor, previous);
            paintFill(image, x, upperY, desiredColor, previous);
            paintFill(image, x, lowerY, desiredColor, previous);
            image[y][x] = desiredColor;
            hasColorized = true;
        }
        return hasColorized;
    }

    private void paintFill(Color[][] image, int x, int y, Color desired, Color previous) {
        final int leftX = x - 1;
        final int rightX = x + 1;
        final int upperY = y - 1;
        final int lowerY = y + 1;

        if (new Rectangle(image[0].length, image.length).contains(x, y)
                && image[y][x] != desired
                && image[y][x] == previous
        ) {
            image[y][x] = desired;
            paintFill(image, leftX, y, desired, previous);
            paintFill(image, rightX, y, desired, previous);
            paintFill(image, x, upperY, desired, previous);
            paintFill(image, x, lowerY, desired, previous);
        }
    }

}
