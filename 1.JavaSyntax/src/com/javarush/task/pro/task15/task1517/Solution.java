package com.javarush.task.pro.task15.task1517;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/* 
Файловые операции
*/

public class Solution {
    static final Path[] PATHS = new Path[2];

    public static void main(String[] args) throws IOException {
        getPaths();
        processFiles();
    }

    private static void processFiles() {
        createIfNotExist();
        moveIfNoCopyExistElseDelete();
    }

    private static void moveIfNoCopyExistElseDelete() {
        if (copyNotExist()) {
            move();
        } else {
            delete();
        }
    }

    private static boolean copyNotExist() {
        return Files.notExists(PATHS[1]);
    }

    private static void delete() {
        try {
            Files.delete(PATHS[0]);
        } catch (IOException e) {
            System.out.println("File " + PATHS[0] + " could not be deleted");
        }
    }

    private static void move() {
        try {
            Files.move(PATHS[0], PATHS[1]);
        } catch (IOException e) {
            System.out.println("File " + PATHS[0] + " could not be moved");
        }
    }

    private static void createIfNotExist() {
        try {
            if (Files.notExists(PATHS[0])) {
                Files.createFile(PATHS[0]);
            }
        } catch (IOException e) {
            System.out.println("File " + PATHS[0] + " could not be created");
        }
    }

    private static void getPaths() {
        try (final Scanner scanner = new Scanner(System.in)){
            PATHS[0] = Paths.get(scanner.nextLine());
            PATHS[1] = Paths.get(scanner.nextLine());
        }
    }
}

