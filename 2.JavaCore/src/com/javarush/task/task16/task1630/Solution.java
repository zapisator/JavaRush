package com.javarush.task.task16.task1630;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/* 
Последовательный вывод файлов
*/

public class Solution {
    public static String firstFileName;
    public static String secondFileName;

    static {
        String [] fileNames = fileNames();
        firstFileName = fileNames[0];
        secondFileName = fileNames[1];
    }

    private static String[] fileNames() {
        final String [] fileNames = new String[2];

        try (final Scanner scanner = new Scanner(System.in)) {
            fileNames[0] = scanner.nextLine();
            fileNames[1] = scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Fortune abandoned you: " + e);
        }
        return fileNames;
    }

    public static void main(String[] args) throws InterruptedException {
        systemOutPrintln(firstFileName);
        systemOutPrintln(secondFileName);
    }

    public static void systemOutPrintln(String fileName) throws InterruptedException {
        ReadFileInterface f = new ReadFileThread();
        f.setFileName(fileName);
        f.start();
        f.join();
        System.out.println(f.getFileContent());
    }

    public interface ReadFileInterface {

        void setFileName(String fullFileName);

        String getFileContent();

        void join() throws InterruptedException;

        void start();
    }

    public static class ReadFileThread extends Thread implements ReadFileInterface {

        private String fileName;
        private String fileContent = "";

        @Override
        public void run() {
            try {
                fileContent = String.join(" ", Files.readAllLines(Paths.get(fileName)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void setFileName(String fullFileName) {
            this.fileName = fullFileName;
        }

        @Override
        public String getFileContent() {
            return fileContent;
        }

    }
}
