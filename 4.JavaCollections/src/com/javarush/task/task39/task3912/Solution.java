package com.javarush.task.task39.task3912;

/* 
Максимальная площадь
*/

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.sqrt;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Solution {

    public static void main(String[] args) {
        final Random random = new Random();
        final int[][] matrix = new int[random.nextInt(10) + 4][random.nextInt(10) + 4];

        fill(matrix);
        print(matrix);
        System.out.println("===\n" + maxSquare(matrix));
    }

    private static void fill(int[][] matrix) {
        final Random random = new Random();

        Arrays.stream(matrix)
                .forEach(row -> IntStream.range(0, row.length)
                        .forEach(x -> row[x]
                                = (random.nextInt(10)) == 0 ? 0 : 1));
    }

    private static void print(int[][] matrix) {
        for (int[] row : matrix) {
            for (int cell : row) {
                System.out.printf("%4s ", cell);
            }
            System.out.println();
        }
    }

    public static int maxSquare(int[][] matrix) {
        final int[][] copy = new int[matrix.length][matrix[0].length];
        int max = 0;

        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[0].length; x++) {
                final int len = min(x, y) + 1;

                copy[y][x] = matrix[y][x];
                copy[y][x] = localMaxSquare(copy, x, y, len);
                max = max(max, copy[y][x]);
            }
        }
        return max;
    }

    private static int localMaxSquare(int[][] copy, int x, int y, int len) {
        if (len == 1 || copy[y][x] == 0) {
            return copy[y][x];
        }

        final int min = min(
                copy[y - 1][x - 1],
                min(copy[y][x - 1], copy[y - 1][x])
        );
        final int result = (int) sqrt(min) + 1;

        return result * result;
    }
}
