package com.javarush.task.task19.task1909;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/* 
Замена знаков
*/

public class Solution {
    public static void main(String[] args) {
        final String[] fileNames = fileNames();
        final List<String> content = content(fileNames[0]).stream()
                .map(line -> line.replaceAll("\\.", "!"))
                .collect(Collectors.toList());

        write(fileNames[1], content);
    }

    private static void write(String fileName, List<String> content) {
        try (final BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (final String line : content) {
                writer.write(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<String> content(String fileName) {
        try (final BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            final List<String> content = new LinkedList<>();

            while (reader.ready()) {
                content.add(reader.readLine());
            }
            return content;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String[] fileNames() {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final String[] fileNames = new String[2];

            for (int i = 0; i < 2; i++) {
                fileNames[i] = reader.readLine();
            }
            return fileNames;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
