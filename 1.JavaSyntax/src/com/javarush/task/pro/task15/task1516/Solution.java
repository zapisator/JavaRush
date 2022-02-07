package com.javarush.task.pro.task15.task1516;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/* 
Файл или директория
*/

public class Solution {
    private static final String THIS_IS_FILE = " - это файл";
    private static final String THIS_IS_DIR = " - это директория";

    public static void main(String[] args) {
        try (final Scanner scanner = new Scanner(System.in)) {
            boolean isExistingFile = true;

            while (isExistingFile) {
                if (scanner.hasNext()) {
                    final String pathName = scanner.nextLine();
                    final Path path = Paths.get(pathName);
                    if (Files.exists(path)) {
                        if (Files.isRegularFile(path)) {
                            System.out.println(path + THIS_IS_FILE);
                        } else if (Files.isDirectory(path)) {
                            System.out.println(path + THIS_IS_DIR);
                        } else {
                            isExistingFile = false;
                        }
                    } else {
                        isExistingFile = false;
                    }
                }
            }
        }
    }
}

