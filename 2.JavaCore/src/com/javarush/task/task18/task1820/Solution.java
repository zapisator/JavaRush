package com.javarush.task.task18.task1820;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/* 
Округление чисел
*/

public class Solution {
    public static void main(String[] args) {
        final String[] fileNames = fileNames();
//        final String[] fileNames = new String[]{
//                "/home/sb_work/Загрузки/JavaRushTasks/2.JavaCore/src/com/javarush/task/task18/task1820/doubles",
//                "/home/sb_work/Загрузки/JavaRushTasks/2.JavaCore/src/com/javarush/task/task18/task1820/integers"
//        };
        final List<Double> doubles = new LinkedList<>();

        read(fileNames[0], doubles);
        write(fileNames[1], doubles);
    }

    private static void write(String fileName, List<Double> doubles) {
        try (final PrintWriter writer = new PrintWriter(fileName)){
            for (double number : doubles) {
                final RoundingMode roundingMode = number < 0
                        ? RoundingMode.HALF_DOWN : RoundingMode.HALF_UP;
                final int roundedNumber =
                        BigDecimal.valueOf(number).setScale(0, roundingMode).intValue();
//                System.out.println(number + " -> " + roundedNumber);
                writer.print(roundedNumber + " ");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private static void read(final String fileName, final List<Double> doubles) {
        try (final Scanner scanner = new Scanner(new FileReader(fileName))) {
            while (scanner.hasNext()) {
                final double number = Double.parseDouble(scanner.next());
                doubles.add(number);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String[] fileNames() {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final String[] fileNames = new String[2];
            fileNames[0] = reader.readLine();
            fileNames[1] = reader.readLine();
            return fileNames;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
