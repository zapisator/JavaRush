package com.javarush.task.pro.task15.task1511;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/* 
Пишем символы в файл
*/

public class Solution {
    public static void main(String[] args) {
        char[] chars = args[0].toCharArray();
        final InputStream stream = System.in;
        final String fileName = fileName(stream);

        writeChars(fileName, chars);
    }

    private static void writeChars(String fileName, char[] chars) {
        try (final BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName))) {
            writer.write(chars);
        } catch (IOException e) {
            System.out.println("Something went wrong : " + e);
        }
    }

    private static String fileName(final InputStream stream) {
        String fileName = "";

        try (final Scanner scanner = new Scanner(stream))  {
            fileName = scanner.nextLine();
        } catch (Exception e) {
            System.out.println("scan failed: " + e);
        }
        return fileName;
    }
}


