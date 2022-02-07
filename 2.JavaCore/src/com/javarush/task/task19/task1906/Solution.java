package com.javarush.task.task19.task1906;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

/* 
Четные символы
*/

public class Solution {
    public static void main(String[] args) {
        final String[] fileNames = fileNames();
//        final String[] fileNames = new String[]{
//                "/home/sb_work/Загрузки/JavaRushTasks/2.JavaCore/src/com/javarush/task/task19/task1906/1.txt",
//                "/home/sb_work/Загрузки/JavaRushTasks/2.JavaCore/src/com/javarush/task/task19/task1906/2.txt"
//        };
        final String contents = contents(fileNames[0]);
        final String processedContents = peakEvenCharacters(contents);

        write(fileNames[1], processedContents);
    }

    private static void write(String fileName, String processedContents) {
        try (final FileWriter writer = new FileWriter(fileName)) {
            writer.write(processedContents);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String peakEvenCharacters(String contents) {
        return IntStream.range(0, contents.length())
                .filter(number -> number % 2 == 1)
                .map(contents::charAt)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    private static String contents(String fileName) {
        try (final FileReader reader = new FileReader(fileName)) {
            final StringBuilder contents = new StringBuilder();
            final char[] buffer = new char[256];

            while (reader.ready()) {
                int count = reader.read(buffer, 0, 256);
                contents.append(buffer, 0, count);
            }
            return contents.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String[] fileNames() {
        final String[] fileNames = new String[2];
        final InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        final BufferedReader reader = new BufferedReader(inputStreamReader);

        try {

            for (int i = 0; i < 2; i++) {
                fileNames[i] = reader.readLine();
            }
            reader.close();
            inputStreamReader.close();
            return fileNames;
        } catch (IOException e) {
                try {
                    reader.close();
                    inputStreamReader.close();
                } catch (IOException b) {
                    b.printStackTrace();
                }
            throw new RuntimeException(e);
        }
    }
}
