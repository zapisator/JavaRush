package com.javarush.task.task20.task2027;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/* 
Кроссворд
*/

public class Solution {

    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'0', '1', '2', '3', '4', '5'},
                {'1', 'z', ' ', ' ', 'z', 'z'},
                {'2', 'z', 'z', 'z', 'z', 'z'},
                {'3', ' ', 'z', ' ', 'z', ' '},
                {'4', 'z', 'z', 'z', 'z', 'z'}
//                {'a', 'a'},
//                {'a', 'a'}

//                {'a', 'b', 'c'},
//                {'b', 'd', 'd'},
//                {'c', 'd', 'd'}

//                {'f', 'd'}

//                {'a', 'm', 'a', 'r', 'e', 'e'},
//                {'m', 's', 'a', 'm', 'e', 'm'},
//                {'e', 'a', 'a', 'r', 'o', 'o'},
//                {'m', 'm', 'p', 'h', 'o', 'h'},
//                {'h', 'r', 'e', 'm', 'o', 'h'}

//                {'f', 'd', 'e', 'r', 'l', 'k'},
//                {'u', 's', 'a', 'm', 'e', 'o'},
//                {'l', 'n', 'g', 'r', 'o', 'v'},
//                {'m', 'l', 'p', 'r', 'r', 'h'},
//                {'p', 'o', 'e', 'e', 'j', 'j'}
        };

        detectAllWords(crossword, "z",  "zz","zzz", "zzzz");
//        detectAllWords(crossword, "aa");
//        detectAllWords(crossword, "fd", "de");
//        detectAllWords(crossword, "bb", "dd", "c");
//        detectAllWords(crossword, "home", "same");
//        detectAllWords(crossword, "fder", "redf", "fulm", "mluf", "fsgr", "rgsf", "mnar", "ranm");

        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        final Set<String> easyAccessWords = new HashSet<>(Arrays.asList(words));

        if (words.length == 0) {
            return Collections.emptyList();
        }

        return result(words, invertedIndex(crossword));
    }

    private static List<Word> result(
            String[] easyAccessWords, Map<String, Set<Line>> invertedIndex
    ) {
        final List<Word> result = new LinkedList<>();

        for (String word : easyAccessWords) {
            if (invertedIndex.containsKey(word)) {
                new ArrayList<>(invertedIndex.get(word)).forEach(
                        line -> result.add(new Word(word, line))
                );
            }
        }
        result.forEach(System.out::println);
//        System.out.println(result.size());
        return result;
    }

    private static Map<String, Set<Line>> invertedIndex(int[][] crossword) {
        final Map<String, Set<Line>> invertedIndex = new HashMap<>();

        fillWithOneCharWords(crossword, invertedIndex);
        fillWithLongerWords(crossword, invertedIndex);
        return invertedIndex;
    }

    private static void fillWithOneCharWords(
            int[][] crossword, Map<String, Set<Line>> invertedIndex
    ) {
        for (int y = 0; y < crossword.length; y++) {
            for (int x = 0; x < crossword[0].length; x++) {
                final String text = Character.toString((char) crossword[y][x]);
                final Line line = new Line(x, y, x, y);

                invertedIndex.computeIfPresent(
                        text, (k, v) -> {
                            v.add(line);
                            return v;
                        });
                invertedIndex.putIfAbsent(
                        text,
                        new HashSet<>(Collections.singletonList(line))
                );
            }
        }
//        print(invertedIndex);
    }

    private static void fillWithLongerWords(
            int[][] crossword, Map<String, Set<Line>> invertedIndex
    ) {
        final InitialParameters p = new InitialParameters(0, 0, 0);

        forEachRow(p, crossword, invertedIndex);
        forEachColumn(p, crossword, invertedIndex);
//        print(invertedIndex);
    }

    private static void forEachRow(
            InitialParameters p, int[][] crossword, Map<String, Set<Line>> invertedIndex
    ) {
        for (p.y = 0; p.y < crossword.length; p.y++) {
            forEachRowBasedSubstring(p, crossword, invertedIndex);
        }
    }

    private static void forEachColumn(
            InitialParameters p, int[][] crossword, Map<String, Set<Line>> invertedIndex
    ) {
        for (p.x = 0; p.x < crossword[0].length; p.x++) {
            forEachColumnBasedSubstring(p, crossword, invertedIndex);
        }
    }

    private static void forEachRowBasedSubstring(InitialParameters p, int[][] crossword,
            Map<String, Set<Line>> invertedIndex) {
        for (p.len = 2; p.len <= crossword[0].length; p.len++) {
            forEachRowWordStart(p, crossword, invertedIndex);
        }
    }

    private static void forEachColumnBasedSubstring(
            InitialParameters p, int[][] crossword, Map<String, Set<Line>> invertedIndex
    ) {
        for (p.len = 2; p.len <= crossword.length; p.len++) {
            forEachColumnWordStart(p, crossword, invertedIndex);
        }
    }

    private static void forEachRowWordStart(
            InitialParameters p, int[][] crossword, Map<String, Set<Line>> invertedIndex
    ) {
        for (p.x = 0; p.x + p.len - 1 < crossword[0].length; p.x++) {
            final Line subRow = new Line(p.x, p.y, p.x + p.len - 1, p.y);

            putWord(wordBuilder(crossword, p, "horizontally"), subRow, invertedIndex);

            if (p.y + (p.len - 1) < crossword.length) {
                final Line diagonal = new Line(p.x, p.y, p.x + p.len - 1, p.y + p.len - 1);

                putWord(wordBuilder(crossword, p, "diagonallyRight"), diagonal, invertedIndex);
            }
        }
    }

    private static void forEachColumnWordStart(
            InitialParameters p, int[][] crossword, Map<String, Set<Line>> invertedIndex
    ) {
        for (p.y = 0; p.y + p.len - 1 < crossword.length; p.y++) {
            final Line subColumn = new Line(p.x, p.y, p.x, p.y + p.len - 1);

            putWord(wordBuilder(crossword, p, "vertically"), subColumn, invertedIndex);

            if (p.x - (p.len - 1) >= 0) {
                final Line diagonal = new Line(p.x, p.y, p.x - (p.len - 1), p.y + p.len - 1);

                putWord(wordBuilder(crossword, p, "diagonallyLeft"), diagonal, invertedIndex);
            }

        }
    }

    private static StringBuilder wordBuilder(
            int[][] crossword, InitialParameters parameters, String mode
    ) {
        final StringBuilder builder = new StringBuilder();
        final int initial = mode.equals("horizontally") || mode.equals("diagonallyRight") ? parameters.x : parameters.y;

        for (int i = initial; i < initial + parameters.len; i++) {
            char ch;
            switch (mode) {
                case "horizontally":
                    ch = (char) crossword[parameters.y][i];
                    break;
                case "vertically":
                    ch = (char) crossword[i][parameters.x];
                    break;
                case "diagonallyRight":
                    ch = (char) crossword[parameters.y + (i - initial)][i];
                    break;
                default:
                    ch = (char) crossword[i][parameters.x - (i - initial)];
            }
            builder.append(ch);
        }
        return builder;
    }

    private static void putWord(
            StringBuilder builder, Line line, Map<String, Set<Line>> invertedIndex
    ) {
        putVersions(builder.toString(), line, invertedIndex);
        putVersions(builder.reverse().toString(), line.reverse(), invertedIndex);
    }

    private static void putVersions(String word, Line line, Map<String, Set<Line>> invertedIndex) {
        invertedIndex.computeIfPresent(word, (k, v) -> {
            v.add(line);
            return v;
        });
        invertedIndex.putIfAbsent(
                word,
                new HashSet<>(Collections.singletonList(line))
        );
    }

    private static void print(Map<String, Set<Line>> invertedIndex) {
        for (String text : invertedIndex.keySet()) {
            invertedIndex.get(text).forEach(line -> System.out.println(new Word(text, line)));
            System.out.println();
        }
    }

    public static class Word {

        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public Word(String text, Line line) {
            this.text = text;
            this.startX = line.startX;
            this.startY = line.startY;
            this.endX = line.endX;
            this.endY = line.endY;
        }

        public String getText() {
            return text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }

        @Override
        public int hashCode() {
            return Objects.hash(text);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Word)) {
                return false;
            }

            Word word = (Word) obj;

            return word.text.equals(text);
        }
    }
}
