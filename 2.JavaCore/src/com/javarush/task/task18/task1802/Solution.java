package com.javarush.task.task18.task1802;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/* 
Минимальный байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        final String fileName = fileName();
        final int minimumByte = minimumByte(fileName);

        System.out.println(minimumByte);
    }

    private static int minimumByte(String fileName) {
        int minimumByte = Integer.MAX_VALUE;

        try (final FileInputStream fileInputStream = new FileInputStream(fileName)) {

            while (fileInputStream.available() > 0) {
                final int data = fileInputStream.read();
                minimumByte = Math.min(data, minimumByte);
            }
        } catch (IOException e) {
            System.out.println("Failed to read a file:\n\n" + e);
        }
        return minimumByte;
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
