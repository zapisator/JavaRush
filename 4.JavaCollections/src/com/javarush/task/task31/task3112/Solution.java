package com.javarush.task.task31.task3112;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/* 
Загрузчик файлов
*/

public class Solution {

    public static void main(String[] args) throws IOException {
        Path passwords = downloadFile(
                "https://javarush.ru/testdata/secretPasswords.txt",
                Paths.get(System.getProperty("user.dir")
                        + "/4.JavaCollections/src/com/javarush/task/task31/task3112")
        );

        for (String line : Files.readAllLines(passwords)) {
            System.out.println(line);
        }
    }

    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException {
        try(final InputStream inputStream = new URL(urlString).openStream()) {
            final Path tempFile = Files.createTempFile("temp-", ".tmp");
            final Path target = downloadDirectory.resolve(Paths.get(urlString).getFileName());
            System.out.println(target);

            Files.copy(inputStream, tempFile, StandardCopyOption.REPLACE_EXISTING);

            return target;
        }
    }
}
