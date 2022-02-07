package com.javarush.task.pro.task15.task1510;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

/* 
Пишем байты в файл
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        byte[] bytes = args[0].getBytes();
        final InputStream stream = System.in;
        final String fileName = fileName(stream);

        writeBytes(fileName, bytes);
    }

    private static void writeBytes(String fileName, byte[] information) {
        try {
            Files.write(Paths.get(fileName), information);
        } catch (IOException e) {
            System.out.println("Something went wrong : " + e);
        }
    }

    private static String fileName(InputStream stream) {
        String fileName = "";
        try (final Scanner scanner = new Scanner(stream)) {
            System.out.println("Give me the file name to write into:");
            fileName = scanner.nextLine();
        }  catch (Exception e) {
            System.out.println("Something went wrong : " + e);
        }
        return fileName;
    }
}

