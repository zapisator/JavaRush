package com.javarush.task.task18.task1821;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javafx.util.Pair;

/* 
Встречаемость символов
*/

public class Solution {
    public static void main(String[] args) {
        try (final InputStreamReader reader = new InputStreamReader(new FileInputStream(args[0]))) {
            final StringBuilder builder = new StringBuilder();
            while (reader.ready()) {
                builder.append((char)reader.read());
            }
            final String string = builder.toString();
            final int[] symbols = new int[128];
            string.chars()
                    .filter(ch -> (0 < ch) && (ch < 128))
                    .forEach(ch -> symbols[ch]++);
            IntStream.range(0, symbols.length)
                    .mapToObj(num -> new Pair<>(num, symbols[num]))
//                    .sorted((pair1, pair2) -> pair2.getValue() - pair1.getValue())
                    .filter(pair -> pair.getValue() > 0)
                    .forEach(pair -> System.out.println((char) pair.getKey().intValue() + " " + pair.getValue()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
