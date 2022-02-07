package com.javarush.task.task13.task1326;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/* 
Сортировка четных чисел из файла
*/

public class Solution {
    public static void main(String[] args) {
        final String fileName = fileName();
        final List<Integer> numbers = numbers(fileName);
        numbers.stream()
                .filter(number -> number % 2 == 0)
                .sorted(Comparator.comparingInt(number -> number))
                .forEach(System.out::println);
    }

    private static List<Integer> numbers(String fileName) {
        List<Integer> numbers = new LinkedList<>();

        try (final FileInputStream stream = new FileInputStream  (fileName);
                final InputStreamReader reader = new InputStreamReader(stream);
            final BufferedReader bufferedReader = new BufferedReader(reader)) {
            while (bufferedReader.ready()) {
                final String numberString = bufferedReader.readLine();
                final Integer number = Integer.valueOf(numberString);
                numbers.add(number);
            }
        } catch (IOException e) {
            System.out.println("Something went wrong with reading the file, see the details: " + e);
        }
        return numbers;
    }

    private static String fileName() {
        String fileName = "";

        try (final Scanner scanner = new Scanner(System.in)) {
            fileName = scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Something went wrong with getting number strings from the console, see the details: " + e);
        }
        return fileName;
    }
}
