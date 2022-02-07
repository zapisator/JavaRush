package com.javarush.task.pro.task15.task1521;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.NoSuchElementException;
import java.util.Scanner;

/* 
Временное сохранение файла
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        String line = getUrl();
        final URL url = new URL(line);
        final Path tempFile = Files.createTempFile(null, null);
//        final Path tempFile = Path.of("/home/sb_work/Загрузки/JavaRushTasks/1.JavaSyntax/src/com/javarush/task/pro/task15/task1521/Meme.jpg");

        download(url, tempFile);
    }

    private static void download(URL url, Path tempFile) {
        try (final BufferedInputStream stream = new BufferedInputStream(url.openStream())){
            final byte[] data = new byte[stream.available()];
            stream.read(data);
            Files.write(tempFile, data);
        } catch (IOException e) {
            System.out.println("A connection by the URL or writing failed. See the details: " + e);
        }
    }

    private static String getUrl() {
        String line = "";
        try (final Scanner scanner = new Scanner(System.in)) {
            line = scanner.nextLine();
        } catch (NoSuchElementException | IllegalArgumentException e) {
            System.out.println("Can`t read URL: " + e);
        }
        return line;
    }
}