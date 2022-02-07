package com.javarush.task.task19.task1907;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/* 
Считаем слово
*/

public class Solution {
    public static void main(String[] args) {
        final String fileName = fileName();
        final List<String> lines = lines(fileName);
        final List<String> words = words(lines);
        final long worldCount = words.stream()
                .filter(word -> word.equalsIgnoreCase("world"))
                .count();

        System.out.println(worldCount);
    }

    private static String fileName() {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<String> lines(String fileName) {
        try (final BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            final List<String> lines = new LinkedList<>();

            while (reader.ready()) {
                lines.add(reader.readLine());
            }
            return lines;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<String> words(List<String> lines) {
        final List<String> words = new LinkedList<>();

        for (String line : lines) {
            List<String> lineSplitIntoWords = Arrays.asList(line.split("\\p{P}|\\p{Z}"));
            words.addAll(lineSplitIntoWords);
        }
        return words;
    }
}
