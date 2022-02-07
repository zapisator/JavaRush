package com.javarush.task.task31.task3101;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;

/* 
Проход по дереву файлов
*/

public class Solution {
    public static void main(String[] args) throws IOException {
//        final String path = System.getProperty("user.dir") + "/" + "4.JavaCollections";
//        final String resultFileAbsolutePath =
//                path
//                + "/src/com/javarush/task/task31/task3101/"
//                + "resultFileAbsolutePath.txt";
        final String path = args[0];
        final String resultFileAbsolutePath = args[1];
        final String newFileName = Paths.get(resultFileAbsolutePath).getParent().toString() + "/" + "allFilesContent.txt";

        if (Files.notExists(Paths.get(resultFileAbsolutePath))) {
            Files.createFile(Paths.get(resultFileAbsolutePath));
        }

        rename(resultFileAbsolutePath, newFileName);

//        Files.delete(Paths.get(newFileName));
        try (final FileWriter writer = new FileWriter(newFileName, true)) {
            fileTree(path).stream()
                    .sorted(Comparator.naturalOrder())
                    .filter(fileName -> !fileName.equals(newFileName))
                    .forEach(fileName -> write(content(fileName), writer));
        }

    }

    private static void write(String content, FileWriter writer) {
        try {
            writer.write(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String content(String fileName) {
        try {
            final String content = String.join("",
                    Files.readAllLines(Paths.get(fileName))) + "\n";
            System.out.println(content);
            return content;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void rename(String resultFileAbsolutePath, String newFileName) {
        final File source = new File(resultFileAbsolutePath);
        final File target = new File(newFileName);

        FileUtils.renameFile(source, target);
    }

    private static List<String> fileTree(String path) throws IOException {
        final List<String> fileTree = new ArrayList<>();

        Files.walkFileTree(
                Paths.get(path),
                EnumSet.noneOf(FileVisitOption.class),
                Integer.MAX_VALUE,
                new SimpleFileVisitor<Path>(){
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                            throws IOException {
                        if (
                                attrs.isRegularFile()
                                        && file.getFileName().toString().endsWith(".txt")
                                        && attrs.size() <= 50
                        ) {
                            fileTree.add(file.toFile().getAbsolutePath());
                        }
                        return super.visitFile(file, attrs);
                    }
                }
        );
        return fileTree;
    }
}
