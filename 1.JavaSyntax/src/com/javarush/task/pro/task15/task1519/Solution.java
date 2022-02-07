package com.javarush.task.pro.task15.task1519;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/* 
Поверхностное копирование
*/

public class Solution {

    static final Path[] PATHS = new Path[2];

    public static void main(String[] args) throws IOException {
        readPaths();
        copyFiles();
    }

    private static void copyFiles() {
        try (final DirectoryStream<Path> stream = Files.newDirectoryStream(PATHS[0])) {
            copyIfRegularFiles(stream);
        } catch (IOException e) {
            System.out.println("File copy failed: " + e);;
        }
    }

    private static void copyIfRegularFiles(DirectoryStream<Path> stream) throws IOException {
        for (Path file : stream) {
            if (Files.isRegularFile(file)) {
                Files.copy(file, PATHS[1].resolve(file.getFileName()));
            }
        }
    }

    private static void readPaths() {
        try (final Scanner scanner = new Scanner(System.in)) {
            for (int i = 0; i < PATHS.length; i++) {
                PATHS[i] = Paths.get(scanner.nextLine());
            }
        }

    }
}

