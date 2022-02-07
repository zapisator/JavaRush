package com.javarush.task.task19.task1917;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

/* 
Свой FileWriter
*/

public class FileConsoleWriter {

    private final FileWriter fileWriter;

    public FileConsoleWriter(String fileName) throws IOException {
        this.fileWriter = new FileWriter(fileName);
    }

    public FileConsoleWriter(String fileName, boolean append) throws IOException {
        this.fileWriter = new FileWriter(fileName, append);
    }

    public FileConsoleWriter(File file) throws IOException {
        this.fileWriter = new FileWriter(file);
    }

    public FileConsoleWriter(File file, boolean append) throws IOException {
        this.fileWriter = new FileWriter(file, append);
    }

    public FileConsoleWriter(FileDescriptor fd) throws IOException {
        this.fileWriter = new FileWriter(fd);
    }

    public void write(char[] cbuf, int off, int len) throws IOException {
        System.out.println(String.valueOf(cbuf, off, len));
        fileWriter.write(cbuf, off, len);
    }

    public void write(int c) throws IOException {
        System.out.println((char)c);
        fileWriter.write(c);
    }

    public void write(String str) throws IOException {
        System.out.println(str);
        fileWriter.write(str);
    }

    public void write(String str, int off, int len) throws IOException {
        System.out.println(str.substring(off, off + len));
        fileWriter.write(str, off, len);
    }

    public void write(char[] cbuf) throws IOException {
        System.out.println(String.valueOf(cbuf));
        fileWriter.write(cbuf);
    }

    public void close() throws IOException {
        fileWriter.close();
    }

    public static void main(String[] args) {

    }

}
