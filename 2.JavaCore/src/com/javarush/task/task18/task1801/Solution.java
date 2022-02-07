package com.javarush.task.task18.task1801;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/* 
Максимальный байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        final String fileName = fileName();
        final int maximumByte = maximumByte(fileName);

        System.out.println(maximumByte);
    }

    private static int maximumByte(String fileName) {
        try (final FileInputStream fileInputStream = new FileInputStream(fileName)) {
            int maximumByte = Integer.MIN_VALUE;

            while (fileInputStream.available() > 0) {
                maximumByte = Math.max(fileInputStream.read(), maximumByte);
            }
            return maximumByte;
        } catch (IOException e) {
            e.printStackTrace();
            return Integer.MIN_VALUE;
        }
    }

    private static String fileName() {
        try (final Scanner scanner = new Scanner(System.in)){
            return scanner.nextLine();
        } catch (Exception e) {
            throw new RuntimeException("Failed to read a fileName: " + e);
        }
    }
}
