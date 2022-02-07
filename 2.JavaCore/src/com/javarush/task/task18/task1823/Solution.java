package com.javarush.task.task18.task1823;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.IntStream;

/* 
Нити и байты
*/

public class Solution {
    public static volatile Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) {
        final List<String> fileNames = fileNames();
        final List<ReadThread> readThreads = new LinkedList<>();

        fileNames.forEach(name -> readThreads.add(new ReadThread(name)));
        readThreads.forEach(ReadThread::start);

    }

    private static List<String> fileNames() {
        try (final Scanner scanner = new Scanner(System.in)){
            final List<String> fileNames = new LinkedList<>();
            String current = scanner.nextLine();

            while (!current.equals("exit")) {
                fileNames.add(current);
                current = scanner.nextLine();
            }
            return fileNames;
        }
    }

    public static class ReadThread extends Thread {
        private final String fileName;

        public ReadThread(String fileName) {
            this.fileName = fileName;
        }

        @Override
        public void run() {
            try (final FileInputStream stream = new FileInputStream(fileName)) {
                final byte[] content = content(stream);
                final TreeMap<Byte, Integer> byteFrequency = new TreeMap<>();

                for (byte data : content) {
                        byteFrequency.computeIfPresent(data, (k, v) -> v + 1);
                        byteFrequency.putIfAbsent(data, 1);
                }
                final int byteValue = byteFrequency.entrySet().stream()
                        .max(Comparator.comparingInt(Entry::getValue))
                        .orElseThrow(RuntimeException::new)
                        .getKey();
                resultMap.put(fileName, byteValue);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private byte[] content(FileInputStream stream) throws IOException {
            final byte[] content = new byte[stream.available()];

            if (stream.read(content) != content.length) {
                throw new RuntimeException();
            }
            return content;
        }
    }
}
