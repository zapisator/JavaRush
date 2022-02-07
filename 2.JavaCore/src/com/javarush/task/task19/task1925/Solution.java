package com.javarush.task.task19.task1925;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/* 
Длинные слова
*/

public class Solution {
    public static void main(String[] args) {
        final String firstFileName = args[0];
        final String secondFileName = args[1];

//        final String firstFileName = "/home/sb_work/Загрузки/JavaRushTasks/2.JavaCore/src/com/javarush/task/task19/task1925/1.txt";
//        final String secondFileName = "/home/sb_work/Загрузки/JavaRushTasks/2.JavaCore/src/com/javarush/task/task19/task1925/2.txt";
        try (
                final BufferedReader reader = new BufferedReader(new FileReader(firstFileName));
                final FileWriter writer = new FileWriter(secondFileName)
        ) {
            final StringJoiner filteredLines = new StringJoiner(",");

            while (reader.ready()) {
                final String lineToWrite = filteredLine(reader.readLine());

                if (lineToWrite.length() > 0) {
                    filteredLines.add(lineToWrite);
                }
            }
            writer.write(filteredLines.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String filteredLine(String readLine) {
        return Arrays.stream(readLine.split(" "))
                .filter(term -> term.length() > 6)
                .collect(Collectors.joining(","));
    }
}
