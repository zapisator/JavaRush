package com.javarush.task.task18.task1825;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Собираем файл
*/

public class Solution {
    public static void main(String[] args) {
        final TreeMap<Integer, String> partFileNames = partFileNames();
        final String outputFileName = outputFileName(partFileNames.get(1));
        combineAndWrite(partFileNames, outputFileName);
//        final long outputFileSize =
//        System.out.println(outputFileName);
//        for (String word : partFileNames.values()) {
//            System.out.println(word);
//        }


    }

    private static void combineAndWrite(TreeMap<Integer, String> partFileNames, String outputFileName) {
        try (final FileOutputStream fileOutputStream = new FileOutputStream(outputFileName, true)) {
            for (final String fileName : partFileNames.values()) {
                write(fileName, fileOutputStream);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void write(String fileName, FileOutputStream fileOutputStream) {
        try(final FileInputStream fileInputStream = new FileInputStream(fileName)) {
            final byte[] data = new byte[fileInputStream.available()];
            if (fileInputStream.read(data) != data.length) {
                throw new RuntimeException();
            }
            fileOutputStream.write(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String outputFileName(String s) {
        return s.substring(0, s.length() - fileNameEnding(s).length());
    }

    private static String fileNameEnding(String fileName) {
        final Pattern pattern = Pattern.compile(".part\\d+$");
        final Matcher matcher = pattern.matcher(fileName);
        String fileNameEnding = null;

        if (matcher.find()) {
            fileNameEnding = matcher.group();
        }
        return fileNameEnding;
    }

    private static TreeMap<Integer, String> partFileNames() {
        try (final Scanner scanner = new Scanner(System.in)){
//        try (final Scanner scanner = new Scanner(new FileReader("/home/sb_work/Загрузки/JavaRushTasks/2.JavaCore/src/com/javarush/task/task18/task1825/fileNames"))){
            String word = scanner.nextLine();
            final TreeMap<Integer, String> partFileNames = new TreeMap<>();

            while (!word.equals("end")) {
                partFileNames.put(number(word), word);
                word = scanner.nextLine();
            }
            return partFileNames;
        }
    }

    private static Integer number(String fileName) {
        return Integer.parseInt(fileNameEnding(fileName).substring(5));
    }
}
