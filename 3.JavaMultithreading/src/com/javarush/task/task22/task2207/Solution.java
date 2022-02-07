package com.javarush.task.task22.task2207;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* 
Обращенные слова
*/

public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) {
        findPairs(content(fileName()));
        result.forEach(System.out::println);
        new StringBuilder();
    }

    private static void findPairs(List<String> content) {
        final Map<String, Integer> wordCounter = new HashMap<>();

        content.stream()
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .forEach(direct -> movePairIfHasOrCountWord(wordCounter, direct));
    }

    private static void movePairIfHasOrCountWord(Map<String, Integer> wordCounter, String direct) {
        final String reverse = new StringBuilder(direct).reverse().toString();

        if (!wordCounter.containsKey(reverse)) {
            wordCounter.compute(direct, (key, value) -> value == null ? 1 : value + 1);
        } else {
            result.add(new Pair(direct, reverse));
            reduceNumber(wordCounter, direct, reverse);
        }
    }

    private static void reduceNumber(Map<String, Integer> wordCounter, String... direct) {
        for (String s : direct) {
            wordCounter.computeIfPresent(s, (key, value) -> value == 1 ? null : value - 1);
        }
    }

    private static List<String> content(String fileName) {
        try (final BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            final List<String> content = new LinkedList<>();

            while (bufferedReader.ready()) {
                content.add(bufferedReader.readLine());
            }
            return content;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String fileName() {
        try {
            return new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        return "/home/sb_work/Загрузки/JavaRushTasks/3.JavaMultithreading/src/com/javarush/task/task22/task2207/1.txt";
    }

    public static class Pair {
        String first;
        String second;

        public Pair() {
        }

        public Pair(String first, String second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return first == null && second == null ? "" :
                    first == null ? second :
                            second == null ? first :
                                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
