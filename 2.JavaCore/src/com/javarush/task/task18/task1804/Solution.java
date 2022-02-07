package com.javarush.task.task18.task1804;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

/* 
Самые редкие байты
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        final String fileName = fileName();
        final int[] byteFrequencies = byteFrequencies(fileName);
        final int leastCommonByteFrequency = leastCommonByteFrequency(byteFrequencies);

        printAll(byteFrequencies, leastCommonByteFrequency);
    }

    private static void printAll(int[] byteFrequencies, int leastCommonByteFrequency) {
        IntStream.range(0, byteFrequencies.length)
                .filter(byteValue -> 0 < byteFrequencies[byteValue] && byteFrequencies[byteValue] == leastCommonByteFrequency)
                .forEach(byteValue -> System.out.print(byteValue + " "));
    }

    private static int leastCommonByteFrequency(int[] byteFrequencies) {
        return Arrays.stream(byteFrequencies)
                .filter(value -> value > 0)
                .min()
                .orElse(-1);
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
