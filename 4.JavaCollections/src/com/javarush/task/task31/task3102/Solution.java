package com.javarush.task.task31.task3102;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/* 
Находим все файлы
*/

public class Solution {

    private static final List<String> fileTree = new ArrayList<>();

    public static List<String> getFileTree(String root) throws IOException {
        Files.walkFileTree(
                Paths.get(root),
                EnumSet.noneOf(FileVisitOption.class),
                Integer.MAX_VALUE,
                new SimpleFileVisitor<Path>() {

                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                            throws IOException {
                        if (attrs.isRegularFile()) {
                            fileTree.add(file.toAbsolutePath().toString());
                        }
                        return super.visitFile(file, attrs);
                    }

                }
        );
        return fileTree;

    }

    public static void main(String[] args) throws IOException {
        getFileTree("/home/sb_work/Загрузки/JavaRushTasks/4.JavaCollections/src").forEach(
                System.out::println);
    }
}
