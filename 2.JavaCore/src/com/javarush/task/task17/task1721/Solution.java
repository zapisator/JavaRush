package com.javarush.task.task17.task1721;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* 
Транзакционность
*/

public class Solution {
    public static List<String> allLines = new ArrayList<String>();
    public static List<String> forRemoveLines = new ArrayList<String>();

    public static void main(String[] args) {
        final String[] fileNames = fileNames();
        readLines(fileNames);
        try {
            new Solution().joinData();
        } catch (CorruptedDataException e) {
            e.printStackTrace();
        }
    }

    private static void readLines(final String[] fileNames) {
        try {
            allLines.addAll(
                    Files.readAllLines(
                            Paths.get(fileNames[0])
                    )
            );
            forRemoveLines.addAll(
                    Files.readAllLines(
                            Paths.get(fileNames[1])
                    )
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String[] fileNames() {
        final String[] fileNames = new String[2];
        
        try (final Scanner scanner = new Scanner(System.in)) {
            fileNames[0] = scanner.nextLine();
            fileNames[1] = scanner.nextLine();
        }
        return fileNames;
    }

    public void joinData() throws CorruptedDataException {
        if (allLines.containsAll(forRemoveLines)) {
            allLines.removeAll(forRemoveLines);
        } else {
            allLines.clear();
            throw new CorruptedDataException();
        }

    }
}
