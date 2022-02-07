package com.javarush.task.pro.task15.task1518;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/* 
А что же внутри папки?
*/

public class Solution {

    private static final String THIS_IS_FILE = " - это файл";
    private static final String THIS_IS_DIR = " - это директория";

    public static void main(String[] args) throws IOException {

        final Path directory = readDirectory();
        printContents(directory);
    }

    private static void printContents(Path directory) {
        try (final DirectoryStream<Path> stream = Files.newDirectoryStream(directory)) {
            printStream(stream);
        } catch (IOException e) {
            System.out.println("Could not read subdirectories and files: " + e);
        }
    }

    private static void printStream(DirectoryStream<Path> stream) {
        for (Path file : stream) {
            if (Files.isRegularFile(file)) {
                System.out.println(file + THIS_IS_FILE);
            } else if (Files.isDirectory(file)) {
                System.out.println(file + THIS_IS_DIR);
            }
        }
    }

    private static Path readDirectory() {
        String directoryName = "";

        try (final Scanner scanner = new Scanner(System.in)) {
            directoryName = scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Read operation failed: " + e);
        }
        return Paths.get(directoryName);
    }
}

