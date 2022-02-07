package com.javarush.task.task18.task1817;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/* 
Пробелы
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        try (final InputStreamReader reader = new InputStreamReader(new FileInputStream(args[0]))) {
            final StringBuilder builder = new StringBuilder();
            while (reader.ready()) {
                builder.append((char)reader.read());
            }
            final String string = builder.toString();
            final long spaces = string.chars()
                    .filter(ch -> ch == ' ')
                    .count();
            final long len = string.length();
            System.out.printf("%.2f", (float)spaces / len * 100);
        }

    }
}
