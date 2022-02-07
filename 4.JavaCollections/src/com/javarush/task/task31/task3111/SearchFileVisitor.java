package com.javarush.task.task31.task3111;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {

    private String partOfName = "";
    private String partOfContent = "";
    private int minSize = 0;
    private int maxSize = Integer.MAX_VALUE;
    private final List<Path> foundFiles = new ArrayList<>();

    public List<Path> getFoundFiles() {
        return foundFiles;
    }

    public void setPartOfName(String partOfName) {
        this.partOfName = partOfName;
    }

    public void setPartOfContent(String partOfContent) {
        this.partOfContent = partOfContent;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {


        if (
                attrs.isRegularFile()
                        && file.getFileName().toString().contains(partOfName)
                        && partOfContent(file, partOfContent)
                        && attrs.size() >= minSize
                        && attrs.size() <= maxSize
        ) {
            foundFiles.add(file);
        }
        return super.visitFile(file, attrs);
    }

    private boolean partOfContent(Path file, String partOfContent) {
        if (!partOfContent.equals("")) {
            try {
                return Files.readAllLines(file).stream()
                        .anyMatch(line -> line.contains(partOfContent));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return true;
    }
}
