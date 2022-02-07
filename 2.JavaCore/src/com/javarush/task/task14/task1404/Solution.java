package com.javarush.task.task14.task1404;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/* 
Коты
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        final List<String> cats = cats();
        cats.stream()
                .map(CatFactory::getCatByKey)
                .forEach(System.out::println);
    }


    private static List<String> cats() {
        final List<String> cats = new LinkedList<>();

        try (final Scanner scanner = new Scanner(System.in)) {
            for (String cat = scanner.nextLine(); !cat.equals(""); cat = scanner.nextLine()) {
                cats.add(cat);
            }
        }
        return cats;
    }

    static class CatFactory {
        static Cat getCatByKey(String key) {
            Cat cat;
            switch (key) {
                case "vaska":
                    cat = new MaleCat("Василий");
                    break;
                case "murka":
                    cat = new FemaleCat("Мурочка");
                    break;
                case "kiska":
                    cat = new FemaleCat("Кисюлька");
                    break;
                default:
                    cat = new Cat(key);
                    break;
            }
            return cat;
        }
    }

    static class Cat {
        private String name;

        protected Cat(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public String toString() {
            return "Я уличный кот " + getName();
        }
    }

    static class MaleCat extends Cat {
        MaleCat(String name) {
            super(name);
        }

        public String toString() {
            return "Я - солидный кошак по имени " + getName();
        }
    }

    static class FemaleCat extends Cat {
        FemaleCat(String name) {
            super(name);
        }

        public String toString() {
            return "Я - милая кошечка по имени " + getName();
        }
    }
}
