package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;

/* 
Что внутри папки?
*/

public class Solution {

    public static void main(String[] args) throws IOException {
        System.out.print(properties(Paths.get(directoryPathName())));
    }

    private static String format(DirectoryProperties properties) {
        return String.format(
                "Всего папок - %d\n"
                        + "Всего файлов - %d\n"
                        + "Общий размер - %d\n",
                properties.folders - 1,
                properties.files,
                properties.size
        );
    }

    private static String properties(Path directory) {
        final BasicFileAttributes attributes = fileAttributes(directory);

        if (!attributes.isDirectory()) {
            return directory.toAbsolutePath() + " - не папка\n";
        } else {
            try {
                final DirectoryProperties properties = new DirectoryProperties();

                Files.walkFileTree(
                        directory,
                        new SimpleFileVisitor<Path>() {
                            @Override
                            public FileVisitResult preVisitDirectory(Path dir,
                                    BasicFileAttributes attrs) throws IOException {
                                properties.folders++;
                                return super.preVisitDirectory(dir, attrs);
                            }

                            @Override
                            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                                    throws IOException {
                                properties.files++;
                                properties.size += attrs.size();
                                return super.visitFile(file, attrs);
                            }
                        });
                return format(properties);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static BasicFileAttributes fileAttributes(Path directory) {
        try {
            return Files.readAttributes(directory, BasicFileAttributes.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String directoryPathName() {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
