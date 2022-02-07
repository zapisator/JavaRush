package com.javarush.task.task19.task1919;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

/* 
Считаем зарплаты
*/

public class Solution {
    public static void main(String[] args) {
        final String fileName = args[0];
//        final String fileName =
//                "/home/sb_work/Загрузки/JavaRushTasks/2.JavaCore/src/com/javarush/task/task19/task1919/users.txt";
        final List<String> lines = lines(fileName);
        final TreeMap <String, Double> users = new TreeMap<>();

        for (final String line : lines) {
            final User user = User.parse(line);
            users.computeIfPresent(user.name, (key, value) -> value + user.value);
            users.putIfAbsent(user.name, user.value);
        }
        for (final String key : users.keySet()) {
            System.out.println(key + " " + users.get(key));
        }
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

    public static class User {
        private final String name;
        private final double value;

        private User(String name, double value) {
            this.name = name;
            this.value = value;
        }

        public static User parse(String line) {
            final String[] fields = line.split(" ");
            return new User(fields[0], Double.parseDouble(fields[1]));
        }
    }
}
