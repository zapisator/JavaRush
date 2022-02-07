package com.javarush.task.task19.task1924;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/* 
Замена чисел
*/

public class Solution {
    public static Map<Integer, String> map = new HashMap<Integer, String>();

    static {
        map.put(0, "ноль");
        map.put(1, "один");
        map.put(2, "два");
        map.put(3, "три");
        map.put(4, "четыре");
        map.put(5, "пять");
        map.put(6, "шесть");
        map.put(7, "семь");
        map.put(8, "восемь");
        map.put(9, "девять");
        map.put(10, "десять");
        map.put(11, "одиннадцать");
        map.put(12, "двенадцать");
    }

    public static void main(String[] args) {
        lines(fileName()).stream()
                .map(Solution::correctedLine)
                .forEach(System.out::println);
    }

    private static String correctedLine(String line) {
        final String[] words = line.split(" ");

        for (int i = 0; i < words.length; i++) {
            if (words[i].matches("\\d+")) {
                final int number =Integer.parseInt(words[i]);
                words[i] = map.getOrDefault(number, words[i]);
            } else if (words[i].matches("\\d+\\p{P}")) {
                final String punctuation = words[i].replaceAll("\\d+", "");
                final int number = Integer.parseInt(words[i].substring(0, words[i].length() - 1));
                if (0 <= number && number <= 12) {
                    words[i] = map.get((number)) + punctuation;
                }
            }
        }
        return String.join(" ", words);
    }

    private static String fileName() {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<String> lines(final String fileName) {
        try (final BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            final List<String> contents = new LinkedList<>();

            while (reader.ready()) {
                contents.add(reader.readLine());
            }
            return contents;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
