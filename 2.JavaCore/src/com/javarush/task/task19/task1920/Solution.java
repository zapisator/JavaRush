package com.javarush.task.task19.task1920;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

/* 
Самый богатый
*/

public class Solution {
    public static void main(String[] args) {
//        args = new String[]{
//                "/home/sb_work/Загрузки/JavaRushTasks/2.JavaCore/src/com/javarush/task/task19/task1920/users.txt"
//        };
        final List<User> userValues = userValues(users(lines(args[0])));
        final double maxValue = maxValue(userValues);

        userValues.stream()
                .filter(user -> user.getValue() == maxValue)
                .forEach(user -> System.out.println(user.getName()));
    }

    private static double maxValue(List<User> userValues ) {
        return userValues.stream()
                .max(Comparator.comparingDouble(User::getValue))
                .orElseThrow(RuntimeException::new)
                .getValue();
    }

    private static List<User> userValues(TreeMap<String, Double> users) {
        final List<User> userValues = new LinkedList<>();

        for (final String key : users.keySet()) {
            userValues.add(new User(key, users.get(key)));
        }
        return userValues;
    }

    private static TreeMap<String, Double> users(List<String> lines) {
        final TreeMap<String, Double> users = new TreeMap<>();

        for (final String line : lines) {
            final User user = User.parse(line);
            users.computeIfPresent(user.getName(), (key, value) -> value + user.getValue());
            users.putIfAbsent(user.getName(), user.getValue());
        }
        return users;
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

        public User(String name, double value) {
            this.name = name;
            this.value = value;
        }

        public static User parse(final String line) {
            final String[] fields = line.split(" ");

            return new User(fields[0], Double.parseDouble(fields[1]));
        }

        public double getValue() {
            return value;
        }

        public String getName() {
            return name;
        }
    }
}
