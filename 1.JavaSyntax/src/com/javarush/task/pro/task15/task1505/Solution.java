package com.javarush.task.pro.task15.task1505;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

/* 
Что-то не копируется...
*/

public class Solution {

    private final static String PATH_PREFIX = "/home/sb_work/Загрузки/JavaRushTasks/1.JavaSyntax/src/com/javarush/task/pro/task15/task1505/";
    private final static String FILE_1 = PATH_PREFIX + "1.txt";
    private final static String FILE_2 = PATH_PREFIX + "2.txt";
    private final static String THAT_TO_SCAN = FILE_1 + "\n" + FILE_2 + "\n";

    private static final String[] fileNames = new String[2];

    public static void main(String[] args) {

        getFileNames();
        try (
                InputStream inputStream = Files.newInputStream(Paths.get(fileNames[0]));
                OutputStream outputStream = Files.newOutputStream(Paths.get(fileNames[1]));
        ) {
            int size = 1024;
            byte[] buffer = new byte[size];

            while (inputStream.available() > 0) {
                int read = inputStream.read(buffer, 0, size);
                outputStream.write(buffer, 0, read);
            }
        } catch (IOException e) {
            System.out.println("Something went wrong : " + Arrays
                    .toString(Thread.currentThread().getStackTrace()));
        }
    }

    private static void getFileNames() {
        try (final Scanner scanner = new Scanner(System.in)) {
//            try (final Scanner scanner = new Scanner(THAT_TO_SCAN)) {
            int fileCount = 0;

            while (fileCount < 2) {
                System.out.println("Enter absolute path to the " + (fileCount + 1) + " file: ");
                fileNames[fileCount] = scanner.nextLine();
                System.out.println(fileNames[fileCount] + "\n");
                fileCount++;
            }
        }
    }
}