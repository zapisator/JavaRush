package com.javarush.task.task20.task2026;

/* 
Алгоритмы-прямоугольники
*/

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        byte[][] a1 = new byte[][]{
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 1}
        };
        byte[][] a2 = new byte[][]{
                {1, 0, 0, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {1, 0, 0, 1}
        };

        int count1 = getRectangleCount(a1);
        System.out.println("count = " + count1 + ". Должно быть 2");
        int count2 = getRectangleCount(a2);
        System.out.println("count = " + count2 + ". Должно быть 4");
    }

    public static int getRectangleCount(byte[][] a) {
        final Map<Point, Integer> ids = new HashMap<>();
        int maxId = 0;

        for (int x = 0; x < a.length; x++) {
            for (int y = 0; y < a[0].length; y++) {
                final int id = id(
                        Math.max(neighbourId(ids, x - 1, y), neighbourId(ids, x, y - 1)),
                        maxId,
                        a[x][y]
                );

                ids.put(new Point(x, y), id);
                maxId = Math.max(maxId, id);
            }
        }
        return maxId;
    }

    private static int neighbourId(Map<Point, Integer> ids, int x, int y) {
        return ids.getOrDefault(new Point(x, y), 0);
    }

    private static Integer id(int maxNeighbourId, int maxId, int value) {
        int id = 0;

        if (value != 0) {
            id = maxNeighbourId > 0 ? maxNeighbourId : maxId + 1;
        }
        return id;
    }

}
