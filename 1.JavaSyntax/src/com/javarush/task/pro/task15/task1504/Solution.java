package com.javarush.task.pro.task15.task1504;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

/* 
Перепутанные байты
*/

public class Solution {

    private static final String[] fileNames = new String[2];

    public static void main(String[] args) {

        getFileNames();
        System.out.println(Arrays.deepToString(fileNames));

        try (final InputStream readerOfFile1 = Files.newInputStream(Paths.get(fileNames[0]));
                final OutputStream writerToFile2 = Files
                        .newOutputStream(Paths.get(fileNames[1]));) {
            while (readerOfFile1.available() >= 2) {
                byte odd = (byte) readerOfFile1.read();
                byte even = (byte) readerOfFile1.read();
                writerToFile2.write(new byte[]{even, odd});
            }
            if (readerOfFile1.available() == 1) {
                writerToFile2.write(readerOfFile1.read());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getFileNames() {
        try (final Scanner scanner = new Scanner(System.in)) {
            int fileCount = 0;

            while (fileCount < 2) {
                System.out.println("Enter absolute path to the " + (fileCount + 1) + " file: ");
                fileNames[fileCount++] = scanner.nextLine();
            }
        }
    }
}

