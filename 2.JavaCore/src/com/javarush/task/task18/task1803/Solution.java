package com.javarush.task.task18.task1803;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

/* 
Самые частые байты
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        final String fileName = fileName();
        final int[] byteFrequency = byteFrequency(fileName);
        final int mostCommonByteIndex = mostCommonByte(byteFrequency);

        IntStream.range(0, byteFrequency.length)
                .filter(index -> byteFrequency[index] == byteFrequency[mostCommonByteIndex])
                .forEach(byteValue -> System.out.print(byteValue + " "));
    }

    private static int mostCommonByte(int[] byteFrequency) {
        int mostCommonByteIndex = 0;

        for (int i = 0; i < byteFrequency.length; i++) {
            final int current = byteFrequency[i];

            if (current > byteFrequency[mostCommonByteIndex]) {
                mostCommonByteIndex = i;
            }
        }
        return mostCommonByteIndex;
    }

    private static int[] byteFrequency(String fileName) {

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
