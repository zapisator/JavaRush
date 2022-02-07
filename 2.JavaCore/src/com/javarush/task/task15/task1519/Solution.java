package com.javarush.task.task15.task1519;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/* 
Разные методы для разных типов
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        final List<String> strings = strings();
        process(strings);
    }

    private static void process(List<String> strings) {
        for (String word : strings) {
            if (isNumeric(word)) {
                printNumeric(word);
            } else {
                print(word);
            }
        }
    }

    private static void printNumeric(String word) {
        if (isDouble(word)) {
            print(Double.parseDouble(word));
        } else {
            printInteger(word);
        }
    }

    private static void printInteger(String word) {
        int number = Integer.parseInt(word);

        if (0 < number && number < 128) {
            print((short) number);
        } else {
            print(number);
        }
    }

    private static boolean isDouble(String word) {
        return word.matches("-?\\d+\\.\\d*?");
    }

    private static boolean isNumeric(String word) {
        return word.matches("-?\\d+(\\.\\d*)?");
    }

    private static List<String> strings() {
        final List<String> strings = new LinkedList<>();

        try (final Scanner scanner = new Scanner(System.in)){
            for (String entry = scanner.nextLine(); !entry.equals("exit"); entry = scanner.nextLine()) {
                strings.add(entry);
            }
        }
        return strings;
    }

    public static void print(Double value) {
        System.out.println("Это тип Double, значение " + value);
    }

    public static void print(String value) {
        System.out.println("Это тип String, значение " + value);
    }

    public static void print(short value) {
        System.out.println("Это тип short, значение " + value);
    }

    public static void print(Integer value) {
        System.out.println("Это тип Integer, значение " + value);
    }
}
