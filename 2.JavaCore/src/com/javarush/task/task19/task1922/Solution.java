package com.javarush.task.task19.task1922;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* 
Ищем нужные строки
*/

public class Solution {
    public static List<String> words = new ArrayList<String>();

    static {
        words.add("файл");
        words.add("вид");
        words.add("В");
    }

    public static void main(String[] args) {
        final String fileName = fileName();
//        final String fileName = "/home/sb_work/Загрузки/JavaRushTasks/2.JavaCore/src/com/javarush/task/task19/task1922/lines.txt";
        final List<String> lines = lines(fileName);

        lines.stream()
                .filter(Solution::containsTwo)
                .forEach(System.out::println);
    }

    private static boolean containsTwo(String line) {
        final Map<String, Integer> countedOuterWords = countedOuterWords(line);

        int count = 0;
        for (int index = 0; count <= 2 && index < words.size(); index++) {
            final String term = words.get(index);

            if (countedOuterWords.containsKey(term)) {
                count += countedOuterWords.get(term);
            }
        }
        return count == 2;
    }

    private static Map<String, Integer> countedOuterWords(String line) {
        final Map<String, Integer> countedOuterWords = new HashMap<>();

        Arrays.stream(line.split(" "))
                .forEach(term -> {
                    countedOuterWords.computeIfPresent(term, (key, value) -> value + 1);
                    countedOuterWords.putIfAbsent(term, 1);
                });
        return countedOuterWords;
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

    private static String fileName() {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
