package com.javarush.task.task18.task1822;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

/* 
Поиск данных внутри файла
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        final String fileName = fileName();
        final int id = Integer.parseInt(args[0]);
//        String fileName = "/home/sb_work/Загрузки/JavaRushTasks/2.JavaCore/src/com/javarush/task/task18/task1822/productList";
//        int id = 19846;
        final List<String> contents = contents(fileName);
        final String product = contents.stream()
                .filter(word -> curentId(word) == id)
                .findFirst()
                .orElse("");
        System.out.println(product);
    }

    private static List<String> contents(String fileName) {
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

    private static int curentId(String content) {
        int i = 0;
        char symbol = content.charAt(i);
        while (Character.isDigit(symbol)) {
            i++;
            symbol = content.charAt(i);
        }
        return Integer.parseInt(content.substring(0, i));
    }

    private static String fileName() {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
