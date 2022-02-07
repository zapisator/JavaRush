package com.javarush.task.task18.task1816;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/* 
Английские буквы
*/

public class Solution {
    public static void main(String[] args) {
        try (final InputStreamReader reader = new InputStreamReader(new FileInputStream(args[0]))) {
            final StringBuilder builder = new StringBuilder();
            while (reader.ready()) {
                builder.append((char)reader.read());
            }
            final String string = builder.toString();
            final long alphaCount = string.chars()
                    .filter(ch -> Character.isAlphabetic(ch) && (0 < ch) && (ch < 128))
                    .count();
            System.out.println(alphaCount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
