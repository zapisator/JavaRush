package com.javarush.task.task29.task2913;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/* 
Замена рекурсии
*/

public class Solution {
    private static int numberA;
    private static int numberB;

    public static String getAllNumbersBetween(int a, int b) {
        final LinkedList<String> sequence = new LinkedList<>();

        IntStream.range(Math.min(a, b), Math.max(a, b) + 1)
                .forEach(number -> append(sequence, number, a > b));
        return String.join("", sequence).trim();
    }

    private static void append(LinkedList<String> sequence, int number, boolean toFront) {
        if (toFront) {
            sequence.addFirst(number + " ");
        } else {
            sequence.addLast(" " + number);
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        numberA = random.nextInt(1000);
        numberB = random.nextInt(1000);
        System.out.println(getAllNumbersBetween(numberA, numberB));
        System.out.println(getAllNumbersBetween(numberB, numberA));
    }
}