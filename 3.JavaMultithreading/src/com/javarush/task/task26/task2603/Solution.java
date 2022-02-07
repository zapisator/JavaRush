package com.javarush.task.task26.task2603;

import java.util.Arrays;
import java.util.Comparator;

/* 
Убежденному убеждать других не трудно
*/

public class Solution {


    public static void main(String[] args) {

    }

    public static class CustomizedComparator<T> implements Comparator<T> {

        private Comparator<T>[] comparators;

        @SafeVarargs
        public CustomizedComparator(Comparator<T>... comparators) {
            this.comparators = comparators;
        }

        @Override
        public int compare(T object1, T object2) {
            Comparator<T> comparator;
            int result = 0;

            for (int i = 0; i < comparators.length && result == 0; i++) {
                comparator = comparators[i];
                result = comparator.compare(object1, object2);
            }
            return result;
        }

    }

}
