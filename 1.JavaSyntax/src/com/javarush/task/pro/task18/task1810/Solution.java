package com.javarush.task.pro.task18.task1810;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* 
Преобразование списка в массив
*/

public class Solution {

    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<String>();
        Collections.addAll(strings, "Ты", "ж", "программист");

        ArrayList<Integer> integers = new ArrayList<Integer>();
        Collections.addAll(integers, 1000, 2000, 3000);


        String[] stringArray = toStringArray(strings);
        for (String string : stringArray) {
            System.out.println(string);
        }

        Integer[] integerArray = toIntegerArray(integers);
        for (Integer integer : integerArray) {
            System.out.println(integer);
        }
    }

    public static String[] toStringArray(ArrayList<String> strings) {
        return strings.toArray(strings.toArray(new String[]{}));
    }

    public static Integer[] toIntegerArray(ArrayList<Integer> integers) {
        return integers.toArray(new Integer[]{});
    }
}
