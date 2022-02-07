package com.javarush.task.pro.task13.task1305;

import java.util.ArrayList;
import java.util.Iterator;

/* 
Найти и обезвредить
*/

public class Solution {
    private static String BUG = "bug";


    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<>();
        words.add("Hello world!");
        words.add("Amigo");
        words.add("Elly");
        words.add("Kerry");
        words.add("Bug");
        words.add("bug");
        words.add("Easy ug");
        words.add("Risha");

        ArrayList<String> copyWordsFirst = new ArrayList<>(words);
        ArrayList<String> copyWordsSecond = new ArrayList<>(words);
        ArrayList<String> copyWordsThird = new ArrayList<>(words);

        removeBugWithFor(copyWordsFirst);
        removeBugWithWhile(copyWordsSecond);
        removeBugWithCopy(copyWordsThird);

        copyWordsFirst.forEach(System.out::println);
        String line = "_________________________";
        System.out.println(line);
        copyWordsSecond.forEach(System.out::println);
        System.out.println(line);
        copyWordsThird.forEach(System.out::println);
        System.out.println(line);
    }

    public static void removeBugWithFor(ArrayList<String> list) {
        ArrayList<Integer> indices = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            final String current = list.get(i);

            if (current.equalsIgnoreCase(BUG)) {
                list.remove(i);
                i--;
            }

        }
    }

    public static void removeBugWithWhile(ArrayList<String> list) {
        final Iterator<String> iterator = list.iterator();

        while (iterator.hasNext()) {
            final String current = iterator.next();

            if (current.equalsIgnoreCase(BUG)) {
                iterator.remove();
            }
        }
    }

    public static void removeBugWithCopy(ArrayList<String> list) {
        final ArrayList<String> copy = new ArrayList<>(list);

        for(String current : copy) {
            if (current.equalsIgnoreCase(BUG)) {
                list.remove(current);
            }
        }
    }
}