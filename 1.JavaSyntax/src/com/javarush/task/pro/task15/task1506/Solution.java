package com.javarush.task.pro.task15.task1506;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

/* 
Фейсконтроль
*/

public class Solution {
    public static void main(String[] args) {
        final String filePath = getFilePath();
        List<String> contents = readTheFile(filePath);
        final String result = cleanLines(contents);

        System.out.println(result);
    }

    private static String cleanLines(List<String> contents) {
        final StringBuilder builder = new StringBuilder();

        for (String line : contents) {
            cleanLine(line, builder);
        }
        return builder.toString();
    }

    private static void cleanLine(String line, StringBuilder builder) {
        for (int index = 0; index < line.length(); index++) {
            final char at = line.charAt(index);

            if (at != ' ' && at != '.' && at != ',') {
                builder.append(at);
            }
        }
    }

    private static List<String> readTheFile(String filePath) {
        List<String> contents = null;
        try {
             contents = Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contents;
    }

    private static String getFilePath() {
        String filePath = null;

        try (final Scanner scanner = new Scanner(System.in)) {
            filePath = scanner.nextLine();
        } catch (Exception e) {
            System.out.println("exception: " + e);
        }
        return filePath;
    }
}

