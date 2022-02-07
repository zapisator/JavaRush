package com.javarush.task.task22.task2211;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

/* 
Смена кодировки
*/

public class Solution {

    public static void main(String[] args) throws IOException {
        try (
                final BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream(args[0]),
                                Charset.forName("Windows-1251")
                        )
                );
                final BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(
                                new FileOutputStream(args[1]),
                                Charset.forName("UTF-8")
                        )
                );
        ) {
            reader.lines().forEach(line -> write(writer, line + System.lineSeparator()));
        }
    }

    private static void write(BufferedWriter writer, String line) {
        try {
            writer.write(line);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
