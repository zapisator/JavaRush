package com.javarush.task.task13.task1319;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

/* 
Писатель в файл с консоли
*/

public class Solution {

    public static void main(String[] args) {
        try (final Scanner scanner = new Scanner(System.in)) {
            final String filePath = filePath(scanner);
            final String content = content(scanner);
            writeContentToFile(filePath, content);
        } catch (Exception e) {
            System.out.println("Something went wrong with scanner creating, see the details: " + e);
        }
    }

    private static void writeContentToFile(String filePath, String content) {
        try (final OutputStream stream = new FileOutputStream(filePath);
                final OutputStreamWriter writer = new OutputStreamWriter(stream);
                final BufferedWriter bufferedWriter = new BufferedWriter(writer);
        ){
            bufferedWriter.write(content);
        } catch (Exception e) {
            System.out.println("Something went wrong with reading the file, see the details: " + e);
        }
    }

    private static String content(final Scanner scanner) {
        final String stopReading = "exit";
        final StringBuilder builder = new StringBuilder();

        try {
            String contentLine = scanner.nextLine();

            while (!contentLine.equals(stopReading)) {
                builder.append(contentLine).append('\n');
                contentLine = scanner.nextLine();
            }
            builder.append(contentLine).append('\n');
        } catch (Exception e) {
            System.out.println("Something went wrong with reading the content, see the details: " + e);
        }
        return builder.toString();
    }

    private static String filePath(final Scanner scanner) {
        String filePath = "";

        try {
            filePath = scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Something went wrong with getting a file path from the console, see the details: " + e);
        }
        return filePath;
    }
}
