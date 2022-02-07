package com.javarush.task.task22.task2209;

import static java.lang.Character.toLowerCase;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.stream.IntStream;

/* 
Составить цепочку слов
*/

public class Solution {

    public static void main(String[] args) {
        String[] words;

        try {
            words = wordLine(fileName()).split(" ");
        } catch (Exception ignored) {
            words = new String[]{""};
        }

        StringBuilder result = getLine(words);
        System.out.println(result);
    }

    private static String wordLine(String fileName) {
        try (final BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            return reader.readLine();
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
//        return "/home/sb_work/Загрузки/"
//                + "JavaRushTasks/3.JavaMultithreading/src/com/javarush/"
//                + "task/task22/task2209/1.txt";
    }

    public static StringBuilder getLine(String... words) {
        if (words.length == 0) {
            return new StringBuilder();
        }

        final LinkedList<int[]> pool = placements(words);
        final LinkedList<String> answer = new LinkedList<>();
        boolean isAnswer = false;

        while (pool.size() > 0 && !isAnswer) {
            adjustAnswerAndPool(words, pool, answer);
            if (answer.size() == words.length) {
                isAnswer = true;
            }
        }
        return new StringBuilder(String.join(" ", answer));
    }

    private static void adjustAnswerAndPool(String[] words, LinkedList<int[]> pool, LinkedList<String> answer) {
        final int[] placement = pool.pop();
        final String word = words[placement[0]];

        bringToTheAppropriateLength(answer, placement.length, words.length);
        if (canBePlaced(answer, word)) {
            final int[] smallerPlacement = Arrays.copyOfRange(placement, 1, placement.length);

            placements(smallerPlacement).forEach(pool::addFirst);
            answer.addLast(word);
        }
    }

    private static void bringToTheAppropriateLength(LinkedList<String> answer, int placementLength,
            int wordsLength) {
        while (answer.size() + placementLength > wordsLength) {
            answer.removeLast();
        }
    }

    private static LinkedList<int[]> placements(String[] words) {
        return placements(IntStream.range(0, words.length).toArray());
    }

    private static LinkedList<int[]> placements(int[] placement) {
        final LinkedList<int[]> placements = new LinkedList<>(
                Collections.singletonList(placement));

        for (int i = 1; i < placement.length; i++) {
            final int[] anotherPlacement = Arrays.copyOf(placement, placement.length);
            swap(anotherPlacement, i);
            placements.add(anotherPlacement);
        }
        return placements;
    }

    private static boolean canBePlaced(LinkedList<String> answer, String word) {
        return answer.size() == 0 || lastChar(answer) == firstChar(word);
    }

    private static char firstChar(String word) {
        return toLowerCase(word.charAt(0));
    }

    private static char lastChar(LinkedList<String> answer) {
        final String answerLastWord = answer.getLast();

        return toLowerCase(
                answerLastWord.charAt(answerLastWord.length() - 1)
        );
    }

    private static void swap(int[] anotherPlacement, int i) {
        int temp = anotherPlacement[0];
        anotherPlacement[0] = anotherPlacement[i];
        anotherPlacement[i] = temp;
    }

}
