package com.javarush.task.task18.task1805;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javafx.util.Pair;

/* 
Сортировка байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        final String fileName = fileName();
        final int[] byteFrequencies = byteFrequencies(fileName);
        final List<Pair<Integer, Integer>> sortedBytes = sortedBytes(byteFrequencies);

        print(sortedBytes);
    }

    private static void print(List<Pair<Integer, Integer>> sortedBytes) {
        int index = 0;
        for (; index < sortedBytes.size() - 1; index++) {
            System.out.print(sortedBytes.get(index).getKey() + " ");
        }
        System.out.print(sortedBytes.get(index).getKey());
    }

    private static List<Pair<Integer, Integer>> sortedBytes(int[] byteFrequencies) {
        return IntStream.range(0, byteFrequencies.length)
                .filter(value -> byteFrequencies[value] > 0)
                .mapToObj(value -> new Pair<>(value, byteFrequencies[value]))
                .sorted(Comparator.comparingInt(Pair::getKey))
                .collect(Collectors.toList());
    }

    private static int[] byteFrequencies(String fileName) {

        try (final FileInputStream fileInputStream = new FileInputStream(fileName)) {
            int[] byteFrequency = new int[256];

            while (fileInputStream.available() > 0) {
                final int data = fileInputStream.read();
                byteFrequency[data]++;
            }
            return byteFrequency;
        } catch (IOException e) {
            e.printStackTrace();
            return new int[0];
        }
    }

    private static String fileName() {
        try (final Scanner scanner = new Scanner(System.in)) {
            return scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Failed to read a fileName:\n\n" + e);
            return "";
        }
    }
}
