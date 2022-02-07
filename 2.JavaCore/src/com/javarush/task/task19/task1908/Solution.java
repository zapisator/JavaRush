package com.javarush.task.task19.task1908;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Выделяем числа
*/

public class Solution {
    public static void main(String[] args) {
        final String[] fileNames = fileNames();
        final String content = content(fileNames[0]);
        final String numbers = numbers(content);

        write(fileNames[1], numbers);
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

    private static String content(String fileName) {
        try (final BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            final List<String> content = new LinkedList<>();

            while (reader.ready()) {
                content.add(reader.readLine());
            }
            return String.join(" ", content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String numbers(String content) {
        final Pattern pattern
                = Pattern.compile("^-*\\d+(?=\\s)|(?<=\\s)-*\\d+(?=\\s)|(?<=\\s)-*\\d+$");
        final Matcher matcher = pattern.matcher(content);
        final StringBuilder numbers = new StringBuilder();

        while( matcher.find() ) {
            final String matched = matcher.group();
            numbers.append(matched).append(' ');
        }
        return numbers.toString();
    }

    private static void write(String fileName, String numbers) {
        try (final BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(numbers);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
