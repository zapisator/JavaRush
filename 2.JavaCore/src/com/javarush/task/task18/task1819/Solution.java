package com.javarush.task.task18.task1819;

import java.io.*;

/* 
Объединение файлов
*/

public class Solution {
    public static void main(String[] args) {
        final String[] fileNames = fileNames();
        appendTop(fileNames[0], fileNames[1]);
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

    private static void appendTop(String fileName, String fileName1) {
        try (
                final FileInputStream firstFileInput = new FileInputStream(fileName);
                final FileInputStream secondFileInput = new FileInputStream(fileName1);
                final FileOutputStream outputStream = new FileOutputStream(fileName)
        ) {
            final byte[] input = new byte[firstFileInput.available() + secondFileInput.available()];
            int count = secondFileInput.read(input);
            firstFileInput.read(input, count, firstFileInput.available());
            outputStream.write(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
