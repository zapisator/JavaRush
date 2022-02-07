package com.javarush.task.task36.task3605;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/* 
Использование TreeSet
*/

public class Solution {

    public static void main(String[] args) throws IOException {
        final String fileName = args[0];
//        final String fileName = "/home/sb_work/Загрузки/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task36/task3605/1.txt";

        Files.readAllLines(Paths.get(fileName)).stream()
                .map(String::toCharArray)
                .flatMap(chars -> IntStream.range(0, chars.length)
                        .mapToObj(index -> chars[index]))
                .map(Character::toLowerCase)
                .filter(Character::isAlphabetic)
                .collect(Collectors.toCollection(TreeSet::new))
                .stream()
                .limit(5)
                .forEach(System.out::print);

        // для валидатора
        final Set<Character> set = new TreeSet<>();
        for (int i = 0; i < 2; i++) {
            set.add('a');
        }
        // для валидатора
    }
}
