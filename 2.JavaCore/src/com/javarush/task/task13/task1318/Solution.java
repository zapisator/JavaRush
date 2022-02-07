package com.javarush.task.task13.task1318;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

/* 
Чтение файла
*/

public class Solution {
    public static void main(String[] args) {
        final String filePath = filePath();
        final String fileContent = fileContent(filePath);

        System.out.println(fileContent);
    }

    private static String fileContent(String filePath) {
        final StringBuilder content = new StringBuilder();

        try (final InputStream stream = new FileInputStream(filePath);
                final InputStreamReader reader = new InputStreamReader(stream);
                final BufferedReader bufferedReader = new BufferedReader(reader);
        ){
            while (bufferedReader.ready()) {
                final char character = (char) bufferedReader.read();
                content.append(character);
            }
        } catch (Exception e) {
            System.out.println("Something went wrong with reading the file, see the details: " + e);
        }
        return content.toString();
    }

    private static String filePath() {
        String filePath = "";

        try (final Scanner scanner = new Scanner(System.in)) {
            filePath = scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Something went wrong with getting a file path from the console, see the details: " + e);
        }
        return filePath;
    }
}