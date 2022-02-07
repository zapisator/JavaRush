package com.javarush.task.task18.task1807;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Подсчет запятых
*/

public class Solution {
    public static void main(String[] args) {
        final long commasCount = fileContent(fileName())
                .chars()
                .filter(symbol -> symbol == ',')
                .count();

        System.out.println(commasCount);
    }

    private static String fileContent(String fileName) {
        try (final BufferedReader reader =
                new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))) {
            final StringBuilder fileContent = new StringBuilder();
            while (reader.ready()) {
                fileContent.append(reader.readLine());
            }
            return fileContent.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String fileName() {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
