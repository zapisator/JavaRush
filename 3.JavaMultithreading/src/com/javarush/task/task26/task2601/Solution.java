package com.javarush.task.task26.task2601;

import java.util.Arrays;
import java.util.Comparator;

/* 
Почитать в инете про медиану выборки
*/

public class Solution {

    public static void main(String[] args) {

    }

    public static Integer[] sort(Integer[] array) {
        final double median = median(array);
        return Arrays.stream(array)
                .sorted(Comparator
                        .comparing(number -> (int)(Math.abs(median - (Integer)number)))
                        .thenComparing(number -> (Integer)number))
                        .toArray(Integer[]::new);
    }

    private static double median(Integer[] array) {
        Arrays.sort(array);
        return array.length % 2 == 0 ? medianAtEvenArray(array) : array[array.length / 2];
    }

    private static double medianAtEvenArray(Integer[] array) {
        return ((double)(array[array.length / 2 - 1] + array[array.length / 2])) / 2;
    }
}
