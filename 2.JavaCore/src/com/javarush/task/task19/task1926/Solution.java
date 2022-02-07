package com.javarush.task.task19.task1926;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/* 
Перевертыши
*/

public class Solution {
    public static void main(String[] args) {
        contents(fileName()).forEach(line -> System.out.println(reverse(line)));

    }

    private static String reverse(String line) {
        return new StringBuilder(line).reverse().toString();
    }

    private static String fileName() {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<String> contents(final String fileName) {
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
