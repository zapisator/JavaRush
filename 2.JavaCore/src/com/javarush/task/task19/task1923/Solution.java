package com.javarush.task.task19.task1923;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/* 
Слова с цифрами
*/

public class Solution {
    public static void main(String[] args) {
        final String firstFileName = args[0];
        final String secondFileName = args[1];

//        final String firstFileName = "/home/sb_work/Загрузки/JavaRushTasks/2.JavaCore/src/com/javarush/task/task19/task1923/1.txt";
//        final String secondFileName = "/home/sb_work/Загрузки/JavaRushTasks/2.JavaCore/src/com/javarush/task/task19/task1923/2.txt";

        try (
                final BufferedReader reader = new BufferedReader(new FileReader(firstFileName));
                final FileWriter writer = new FileWriter(secondFileName)
        ) {
            final Pattern pattern = Pattern.compile("\\d");

            while (reader.ready()) {
                final String lineToWrite = filteredLine(reader.readLine(), pattern);
                writer.write(lineToWrite + (lineToWrite.length() > 0 ? " " : ""));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String filteredLine(String readLine, final Pattern pattern) {
        return Arrays.stream(readLine.split(" "))
                .filter(term -> pattern.matcher(term).find())
                .collect(Collectors.joining(" "));
    }
}
