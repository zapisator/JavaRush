package com.javarush.task.task12.task1233;

/* 
Изоморфы наступают
*/

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {

    public static void main(String[] args) throws Exception {
        int[] data = new int[]{1, 2, 3, 5, -2, -8, 0, 77, 5, 5};

        Pair<Integer, Integer> result = getMinimumAndIndex(data);

        System.out.println("The minimum is " + result.x);
        System.out.println("The index of the minimum element is " + result.y);
    }

    public static Pair<Integer, Integer> getMinimumAndIndex(int[] array) {
        if (array == null || array.length == 0) {
            return new Pair<Integer, Integer>(null, null);
        }
        final List<Integer> pairs = Arrays.stream(array)
                .boxed()
                .collect(Collectors.toList());
        int x = pairs.get(0);
        int y = 0;
        for (int i = y; i < pairs.size(); i++) {
            int xPossble = pairs.get(i);
            if (x > xPossble) {
                x = xPossble;
                y = i;
            }
        }
        return new Pair<>(x, y);
    }


    public static class Pair<X, Y> {
        public X x;
        public Y y;

        public Pair(X x, Y y) {
            this.x = x;
            this.y = y;
        }
    }
}
