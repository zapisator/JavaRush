package com.javarush.task.pro.task15.task1507;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/* 
Пропускаем не всех
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName = getFileName();
        List<String> lines = getLines(fileName);
        lines = oddLines(lines);
        lines.forEach(System.out::println);
    }

    private static List<String> oddLines(List<String> lines) {
        final List<String> oddLines = new LinkedList<>();

        int counter = 0;
        for (final String line : lines) {
            if (counter % 2 == 0) {
                oddLines.add(line);
            }
            counter++;
        }
        return oddLines;
    }

    private static List<String> getLines(String fileName) {
        List<String> lines = Collections.singletonList("");
        try {
            lines = Files.readAllLines(Paths.get(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;

    }

    private static String getFileName() {
        String fileName;

        try (final Scanner scanner = new Scanner(System.in)) {
            fileName = scanner.nextLine();
        }
        return fileName;
    }
}

