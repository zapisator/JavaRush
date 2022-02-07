package com.javarush.task.task19.task1916;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* 
Отслеживаем изменения
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) {
        final String[] fileNames = new String[2];

        readFileNames(fileNames);
//        final String[] fileNames = new String[] {
//          "/home/sb_work/Загрузки/JavaRushTasks/2.JavaCore/src/com/javarush/task/task19/task1916/file1.txt",
//          "/home/sb_work/Загрузки/JavaRushTasks/2.JavaCore/src/com/javarush/task/task19/task1916/file2.txt"
//        };
        compareLines(lines(fileNames[0]), lines(fileNames[1]));
        lines.forEach(System.out::println);
    }

    private static void readFileNames(String[] fileNames) {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            for (int i = 0; i < fileNames.length; i++) {
                fileNames[i] = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void compareLines(final List<String> formerLines, final List<String> presentLines) {
        final Index index = new Index();

        while ((index.former <= formerLines.size() && index.present < presentLines.size())
                || (index.former < formerLines.size() && index.present <= presentLines.size())
        ) {
            Type type = type(formerLines, presentLines, index);

            switch (type) {
                case SAME:
                    lines.add(new LineItem(Type.SAME, presentLines.get(index.present)));
                    index.former++;
                    index.present++;
                    break;
                case REMOVED:
                    lines.add(new LineItem(Type.REMOVED, formerLines.get(index.former)));
                    index.former++;
                    break;
                case ADDED:
                    lines.add(new LineItem(Type.ADDED, presentLines.get(index.present)));
                    index.present++;
                    break;
            }
        }

    }

    private static Type type(List<String> formerLines, List<String> presentLines, Index index) {
        Type type = Type.ADDED;

        if (index.former < formerLines.size() && index.present < presentLines.size()) {
            type = typeIfIndicesAreInBounds(formerLines, presentLines, index);
        } else if (index.former < formerLines.size()) {
            type = Type.REMOVED;
        }
        return type;
    }

    private static Type typeIfIndicesAreInBounds(List<String> formerLines,
            List<String> presentLines, Index index) {
        Type type = Type.ADDED;

        if (formerLines.get(index.former).equals(presentLines.get(index.present))) {
            type = Type.SAME;
        } else if (index.former + 1 < formerLines.size()
                && formerLines.get(index.former + 1).equals(presentLines.get(index.present))) {
            type = Type.REMOVED;
        }
        return type;
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

    public enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }

        @Override
        public String toString() {
            return type + " " + line;
        }
    }

    public static class Index {
        public int former = 0;
        public int present = 0;
    }
}
