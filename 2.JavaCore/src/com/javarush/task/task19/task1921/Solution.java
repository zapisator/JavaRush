package com.javarush.task.task19.task1921;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* 
Хуан Хуанович
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) {
//        final String fileName = args[0];
        final String fileName = "/home/sb_work/Загрузки/JavaRushTasks/2.JavaCore/src/com/javarush/task/task19/task1921/people.txt";
        lines(fileName).stream()
                .map(Person::parse)
                .forEach(PEOPLE::add);

        PEOPLE.forEach(System.out::println);
    }

    private static List<String> lines(final String fileName) {
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
}
